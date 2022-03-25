package com.dumdumbich.interview.webapiclient.data.db.room.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface RepositoryDao {

    @Insert
    fun create(repository: RepositoryEntity)

    @Delete
    fun delete(repository: RepositoryEntity)

    @Query("SELECT * FROM table_repository")
    fun readAll(): List<RepositoryEntity>

    @Query("DELETE FROM table_repository")
    fun deleteAll()

}