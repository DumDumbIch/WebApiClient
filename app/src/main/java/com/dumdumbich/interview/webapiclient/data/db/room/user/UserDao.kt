package com.dumdumbich.interview.webapiclient.data.db.room.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @Insert
    fun create(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)

    @Query("SELECT * FROM table_user")
    fun readAll(): List<UserEntity>

    @Query("DELETE FROM table_user")
    fun deleteAll()

}