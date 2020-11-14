package com.gitpub.raiffts

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gitpub.raiffts.data.database.AppDatabase
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.singleton

class App : Application(), DIAware {

    override val di by DI.lazy {
        val db = Room
            .databaseBuilder(applicationContext, AppDatabase::class.java, "app-db")
            .build()

        bind<RoomDatabase>() with singleton { db }
    }
}