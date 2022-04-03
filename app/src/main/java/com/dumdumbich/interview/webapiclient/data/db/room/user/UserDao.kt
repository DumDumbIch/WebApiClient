package com.dumdumbich.interview.webapiclient.data.db.room.user

import androidx.room.*


@Dao
interface UserDao {

    @Update
    fun update(user: UserEntity)

    @Update
    fun update(vararg users: UserEntity)

    @Update
    fun update(users: List<UserEntity>): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<UserEntity>)

    @Query("SELECT * FROM table_user WHERE user_login LIKE :login")
    fun get(login: String): UserEntity

    @Query("SELECT * FROM table_user")
    fun getAll(): List<UserEntity>

    @Delete
    fun delete(user: UserEntity)

    @Delete
    fun delete(vararg users: UserEntity)

    @Delete
    fun delete(users: List<UserEntity>): Int

    @Query("DELETE FROM table_user")
    fun deleteAll(): Int

}