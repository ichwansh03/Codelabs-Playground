package com.ichwan.gigihmodule.roomdatabase.di

import android.app.Application
import com.ichwan.gigihmodule.roomdatabase.data.WordRoomDatabase

class WordsApplication : Application() {

    val database by lazy { WordRoomDatabase.getDatabase(this) }
    val repository by lazy { WordRepository(database.wordDao()) }
}