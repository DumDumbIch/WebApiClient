package com.dumdumbich.interview.webapiclient.data.source

import com.dumdumbich.interview.webapiclient.BuildConfig
import com.dumdumbich.interview.webapiclient.data.net.githab.Api
import com.dumdumbich.interview.webapiclient.data.net.githab.Api.Companion.OKHTTP_CONNECT_TIMEOUT
import com.dumdumbich.interview.webapiclient.data.net.githab.Api.Companion.OKHTTP_READ_TIMEOUT
import com.dumdumbich.interview.webapiclient.data.net.githab.Api.Companion.OKHTTP_WRITE_TIMEOUT
import com.dumdumbich.interview.webapiclient.data.net.githab.OwnerDto
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
                        val users = ArrayList<User>()
                        response.body()?.let { getUsersListFromDto(it) }?.let { onSuccess(it) }
                    } else {
                        onError(Throwable("GithubWebDataSource : unknown error"))
                    }
                }

                override fun onFailure(call: Call<List<OwnerDto>>, t: Throwable) {
                    onError(t)
                }

            })
    }

    override fun getUserRepositories(
        login: String,
        onSuccess: (List<Repository>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    private fun getUserFromDto(dto: OwnerDto): User {
        return dto.let { owner ->
            User(
                owner.avatarUrl,
                owner.login,
                owner.reposUrl
            )
        }
    }

    private fun getUsersListFromDto(dtoList: List<OwnerDto>): List<User> {
        val users = ArrayList<User>()
        for (dto in dtoList) {
            users.add(getUserFromDto(dto))
        }
        return users
    }

    private fun createHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain
                    .request().run {
                        val url = url
                            .newBuilder()
//                            .addQueryParameter("appid", BuildConfig.OPEN_WEATHER_MAP_API_KEY)
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