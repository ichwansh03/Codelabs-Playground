package com.ichwan.gigihmodule.roomdatabase.di

import androidx.annotation.WorkerThread
import com.ichwan.gigihmodule.roomdatabase.data.Word
import com.ichwan.gigihmodule.roomdatabase.data.WordDao
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    @WorkerThread
    fun insert(word: Word) {
        wordDao.insert(word)
    }
}