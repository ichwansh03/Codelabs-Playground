package com.ichwan.gigihmodule.roomdatabase.di

import android.app.Application
import com.ichwan.gigihmodule.roomdatabase.data.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
}