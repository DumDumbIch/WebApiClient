package com.dumdumbich.interview.webapiclient.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dumdumbich.interview.webapiclient.data.db.room.repository.RepositoryDao
import com.dumdumbich.interview.webapiclient.data.db.room.repository.RepositoryEntity
import com.dumdumbich.interview.webapiclient.data.db.room.user.UserDao
import com.dumdumbich.interview.webapiclient.data.db.room.user.UserEntity


@Database(
    entities = [UserEntity::class, RepositoryEntity::class],
    version = 1
)
abstract class GithubDb : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun repositoryDao(): RepositoryDao
}