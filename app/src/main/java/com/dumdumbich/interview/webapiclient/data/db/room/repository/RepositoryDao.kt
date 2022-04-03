package com.dumdumbich.interview.webapiclient.data.db.room.repository

import androidx.room.*


@Dao
interface RepositoryDao {

    @Update
    fun update(repository: RepositoryEntity)

    @Update
    fun update(vararg repositories: RepositoryEntity)

    @Update
    fun update(repositories: List<RepositoryEntity>): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RepositoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repositories: RepositoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RepositoryEntity>)

    @Query("SELECT * FROM table_repository WHERE repository_name LIKE :name AND repository_owner_login LIKE :ownerLogin")
    fun get(name: String, ownerLogin: String): RepositoryEntity

    @Query("SELECT * FROM table_repository WHERE repository_owner_login LIKE :ownerLogin")
    fun get(ownerLogin: String): List<RepositoryEntity>

    @Query("SELECT * FROM table_repository")
    fun getAll(): List<RepositoryEntity>

    @Delete
    fun delete(repository: RepositoryEntity)

    @Delete
    fun delete(vararg repositories: RepositoryEntity)

    @Delete
    fun delete(repositories: List<RepositoryEntity>): Int

    @Query("DELETE FROM table_repository")
    fun deleteAll(): Int

}