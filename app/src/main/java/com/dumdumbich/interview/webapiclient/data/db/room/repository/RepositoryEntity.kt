package com.dumdumbich.interview.webapiclient.data.db.room.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_repository")
data class RepositoryEntity(

    @ColumnInfo(name = "repository_name")
    val name: String,

    @ColumnInfo(name = "repository_pushed_at")
    val pushedAt: String,

    @ColumnInfo(name = "repository_forks")
    val forks: Int,

    @PrimaryKey
    @ColumnInfo
    val id: String

)