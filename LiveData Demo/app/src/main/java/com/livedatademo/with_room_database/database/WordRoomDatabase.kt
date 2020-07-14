package com.livedatademo.with_room_database.database

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.livedatademo.utils.AppExecutors
import com.livedatademo.with_room_database.dao.WordDao
import com.livedatademo.with_room_database.entities.Word

/* The database class should be annotated with "@Database".
* Also it should be abstract or interface.*/

@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        private var INSTANCE: WordRoomDatabase? = null

        fun getInstance(context: Context): WordRoomDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java, "word_database.db"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            val word = Word(word = "Ayush")
                            AppExecutors().diskIO().execute {
                                Log.e("Tag","insert word")
                                INSTANCE?.wordDao()?.insertWord(word)
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE!!
        }
    }
}