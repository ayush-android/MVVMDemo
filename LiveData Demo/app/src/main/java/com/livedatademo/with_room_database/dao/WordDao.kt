package com.livedatademo.with_room_database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.livedatademo.with_room_database.entities.Word

/*Dao class should annotated with "@Dao" annotation and
* should be abstract or it should be an interface.
* This class is use to access the database.
* */

@Dao
interface WordDao {

    @Query("SELECT * FROM word_table ORDER BY word")
            /*If the return type of below method is only List<Word> then it can not be called
            * on main thread but if the return type is of LiveData i.e
            * LiveData<List<Word>> then it can be called on Main Thread.*/
    fun getAllWords(): LiveData<List<Word>>

    @Query("SELECT * FROM word_table WHERE word = :word")
    fun getCommentForWord(word: String): LiveData<Word>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWord(word: Word)

    @Delete
    fun deleteWord(word: Word)
}