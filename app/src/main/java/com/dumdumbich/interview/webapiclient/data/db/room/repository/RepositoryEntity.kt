package com.dumdumbich.interview.webapiclient.data.db.room.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_repository")
data class RepositoryEntity(

    @ColumnInfo(name = "repository_name")
    val name: String,

    @ColumnInfo(name = "repository_owner_login")
    val ownerLogin: String,

    @ColumnInfo(name = "repository_pushed_at")
    val pushedAt: String,

    @ColumnInfo(name = "repository_forks")
    val forks: Int,

    @ColumnInfo(name = "repository_language")
    val language: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_primary_key")
    val primaryKey: Int

    )