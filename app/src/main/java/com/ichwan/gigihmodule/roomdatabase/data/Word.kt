package com.ichwan.gigihmodule.roomdatabase.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word (
    @PrimaryKey
    @ColumnInfo("word") var word: String
)