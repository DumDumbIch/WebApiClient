package com.dumdumbich.interview.webapiclient.data.db.room.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_user")
data class UserEntity(

    @ColumnInfo(name = "user_avatar_url")
    val avatarUrl: String,

    @ColumnInfo(name = "user_login")
    val login: String,

    @ColumnInfo(name = "user_repository_url")
    val reposUrl: String,

    @ColumnInfo(name = "user_public_repos")
    val publicRepos: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_primary_key")
    val primaryKey: Int

    )