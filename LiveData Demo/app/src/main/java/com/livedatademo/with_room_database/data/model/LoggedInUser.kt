package com.livedatademo.with_room_database.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */

@Entity(tableName = "users_table")
data class LoggedInUser(
    @PrimaryKey
    val userId: String = UUID.randomUUID().toString(),
    val userName: String,
    @ColumnInfo(name = "password")
    val pass: String
)