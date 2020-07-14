package com.livedatademo.with_room_database.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.livedatademo.utils.AppExecutors
import com.livedatademo.with_room_database.database.WordRoomDatabase
import com.livedatademo.with_room_database.entities.Word

/*This repository is responsible for handling word data operations.
* It contains all the business logic. */

class WordRepository(val context: Context) {

    private val appExecutors = AppExecutors()
    private var roomDatabase: WordRoomDatabase = WordRoomDatabase.getInstance(context)

    fun getAllWords(): LiveData<List<Word>> {
        return roomDatabase.wordDao().getAllWords()
    }

    fun insertWord(word: Word) {
        appExecutors.diskIO().execute {
            roomDatabase.wordDao().insertWord(word)

        }
    }

    fun getCommentForWord(word: String): LiveData<Word> {
        return roomDatabase.wordDao().getCommentForWord(word)
    }
}