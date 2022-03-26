package com.dumdumbich.interview.webapiclient.data.db.room.user

import androidx.room.*


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Query("SELECT * FROM table_user WHERE user_login == :userLogin")
    fun get(userLogin: String):UserEntity

    @Delete
    fun delete(user: UserEntity)

    @Query("SELECT * FROM table_user")
    fun getAll(): List<UserEntity>

    @Query("DELETE FROM table_user")
    fun deleteAll()

}