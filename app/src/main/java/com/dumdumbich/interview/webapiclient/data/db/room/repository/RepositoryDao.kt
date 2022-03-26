package com.dumdumbich.interview.webapiclient.data.db.room.repository

import androidx.room.*


@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RepositoryEntity)

    @Query("SELECT * FROM table_repository WHERE repository_name == :repositoryName AND repository_owner_login == :ownerLogin")
    fun get(repositoryName: String, ownerLogin: String): RepositoryEntity

    @Delete
    fun delete(repository: RepositoryEntity)

    @Query("SELECT * FROM table_repository")
    fun getAll(): List<RepositoryEntity>

    @Query("DELETE FROM table_repository")
    fun deleteAll()

}