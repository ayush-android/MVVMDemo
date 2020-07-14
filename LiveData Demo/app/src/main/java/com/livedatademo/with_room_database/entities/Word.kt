package com.livedatademo.with_room_database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/*If you want to use the class name as table name then no need to use annotation "entity"*/
@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey var id: String = UUID.randomUUID().toString(), var word: String,
    @ColumnInfo(name = "comment") var anyComment: String = "No Comments") {

//Note: If you want to use field name as column name then no need to use "@ColumnInfo"
}