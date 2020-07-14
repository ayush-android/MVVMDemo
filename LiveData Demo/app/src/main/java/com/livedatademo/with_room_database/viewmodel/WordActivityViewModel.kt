package com.livedatademo.with_room_database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.livedatademo.with_room_database.entities.Word
import com.livedatademo.with_room_database.repository.WordRepository

/*If ViewModel class required some context then you should extend AndroidViewModel class
* with only application context. No Activity or other class context should be pass here*/

class WordActivityViewModel(application: Application) : AndroidViewModel(application) {
    val context = application
    var wordList: LiveData<List<Word>> =
        WordRepository(application.applicationContext).getAllWords()

    fun insertWord() {
        val word = Word(word = "Aman", anyComment = "Nice Person")
        WordRepository(context.applicationContext).insertWord(word)
    }

    fun getCommentForWord(word: String): LiveData<Word> {
        return WordRepository(context.applicationContext).getCommentForWord(word)
    }
}