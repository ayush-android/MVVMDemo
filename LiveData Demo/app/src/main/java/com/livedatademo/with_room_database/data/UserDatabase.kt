package com.livedatademo.with_room_database.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.livedatademo.utils.AppExecutors
import com.livedatademo.with_room_database.data.model.LoggedInUser


@Database(entities = arrayOf(LoggedInUser::class), version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract val userDao: LoggedInUserDao

    companion object {
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java, "Users.db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            AppExecutors().diskIO().execute {
                                val user = LoggedInUser(userName = "ayush", pass = "123456")
                                INSTANCE!!.userDao.insertUser(user)
                            }
                        }
                    }).build()

            }

            return INSTANCE!!
        }
    }
}