package com.dumdumbich.interview.webapiclient.data.source

import com.dumdumbich.interview.webapiclient.BuildConfig
import com.dumdumbich.interview.webapiclient.data.net.githab.Api
import com.dumdumbich.interview.webapiclient.data.net.githab.Api.Companion.OKHTTP_CONNECT_TIMEOUT
import com.dumdumbich.interview.webapiclient.data.net.githab.Api.Companion.OKHTTP_READ_TIMEOUT
import com.dumdumbich.interview.webapiclient.data.net.githab.Api.Companion.OKHTTP_WRITE_TIMEOUT
import com.dumdumbich.interview.webapiclient.data.net.githab.OwnerDto
import com.dumdumbich.interview.webapiclient.data.net.githab.RepositoryDto
import com.dumdumbich.interview.webapiclient.data.net.githab.UserDto
import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.GithubUsecase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class GithubWebDataSource : GithubUsecase {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createHttpClient())
        .build()

    private val api = retrofit.create(Api::class.java)

    private val users: MutableList<User> = emptyList<User>().toMutableList()
    private val repositories: MutableList<Repository> = emptyList<Repository>().toMutableList()


    override fun getUser(
        login: String,
        onSuccess: (User) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        api.getUser(login)
            .enqueue(object : Callback<UserDto> {

                override fun onResponse(
                    call: Call<UserDto>,
                    response: Response<UserDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { dto ->
                            onSuccess(getUserFromDto(dto))
                        }
                    } else {
                        onError(Throwable("GithubWebDataSource : getUser - unknown error"))
                    }
                }

                override fun onFailure(call: Call<UserDto>, t: Throwable) {
                    onError(Throwable("GithubWebDataSource : getUser - Failure"))
                }

            })
    }

    override fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        api.getUsers()
            .enqueue(object : Callback<List<OwnerDto>> {

                override fun onResponse(
                    call: Call<List<OwnerDto>>,
                    response: Response<List<OwnerDto>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { dtoList ->
                            users.clear()
                            users.addAll(getUsersListFromDto(dtoList))
                        }?.let {
                            onSuccess(users)
                        }
                    } else {
                        onError(Throwable("GithubWebDataSource : getUsers - unknown error"))
                    }
                }

                override fun onFailure(call: Call<List<OwnerDto>>, t: Throwable) {
                    onError(Throwable("GithubWebDataSource : getUsers - Failure"))
                }

            })
    }

    override fun getUserRepository(
        userLogin: String,
        repositoryName: String,
        onSuccess: (Repository) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        api.getUserRepository(userLogin, repositoryName)
            .enqueue(object : Callback<RepositoryDto> {

                override fun onResponse(
                    call: Call<RepositoryDto>,
                    response: Response<RepositoryDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { dto ->
                            onSuccess(getRepositoryFromDto(dto))
                        }
                    } else {
                        onError(Throwable("GithubWebDataSource : getUserRepository - unknown error"))
                    }
                }

                override fun onFailure(call: Call<RepositoryDto>, t: Throwable) {
                    onError(Throwable("GithubWebDataSource : getUserRepository - Failure"))
                }

            })
    }

    override fun getUserRepositories(
        login: String,
        onSuccess: (List<Repository>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        api.getUserRepositories(login)
            .enqueue(object : Callback<List<RepositoryDto>> {
                override fun onResponse(
                    call: Call<List<RepositoryDto>>,
                    response: Response<List<RepositoryDto>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { dtoList ->
                            repositories.clear()
                            repositories.addAll(getRepositoriesListFromDto(dtoList))
                        }?.let {
                            onSuccess(repositories)
                        }
                    } else {
                        onError(Throwable("GithubWebDataSource : getUserRepositories - unknown error"))
                    }
                }

                override fun onFailure(call: Call<List<RepositoryDto>>, t: Throwable) {
                    onError(Throwable("GithubWebDataSource : getUserRepositories - Failure"))
                }

            })
    }

    private fun getUserFromDto(dto: OwnerDto): User {
        return User(
            dto.avatarUrl,
            dto.login,
            dto.reposUrl,
            dto.id
        )
    }

    private fun getUserFromDto(dto: UserDto): User {
        return User(
            dto.avatarUrl,
            dto.login,
            dto.reposUrl,
            dto.id
        )
    }

    private fun getUsersListFromDto(dtoList: List<OwnerDto>): List<User> {
        val users: MutableList<User> = emptyList<User>().toMutableList()
        for (dto in dtoList) {
            users.add(getUserFromDto(dto))
        }
        return users
    }

    private fun getRepositoryFromDto(dto: RepositoryDto): Repository {
        return Repository(
            dto.name,
            dto.owner.login,
            dto.pushedAt,
            dto.forks,
            dto.id
        )
    }

    private fun getRepositoriesListFromDto(dtoList: List<RepositoryDto>): List<Repository> {
        for (dto in dtoList) {
            repositories.add(getRepositoryFromDto(dto))
        }
        return repositories
    }

    private fun createHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain
                    .request().run {
                        val url = url
                            .newBuilder()
                            .build()

                        newBuilder()
                            .url(url)
                            .build()
                    }
                )
            }
            .connectTimeout(OKHTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(OKHTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OKHTTP_READ_TIMEOUT, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                        addInterceptor(this)
                    }
                }
            }
            .build()
    }

}