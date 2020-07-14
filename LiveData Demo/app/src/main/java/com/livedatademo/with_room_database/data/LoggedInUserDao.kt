package com.livedatademo.with_room_database.data

import android.database.sqlite.SQLiteDatabase
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.livedatademo.with_room_database.data.model.LoggedInUser

@Dao
interface LoggedInUserDao {

    @Insert(onConflict = SQLiteDatabase.CONFLICT_IGNORE)
    fun insertUser(user: LoggedInUser)

    @Query("SELECT * FROM users_table WHERE userName = :userName")
    fun getPassword(userName: String): LoggedInUser?
}