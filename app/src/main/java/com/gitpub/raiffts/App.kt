package com.gitpub.raiffts

import android.app.Application
import com.gitpub.raiffts.service.DataService
import com.gitpub.raiffts.service.MockDataService
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.singleton

class App : Application(), DIAware {

    private val dataService = MockDataService()

    override val di by DI.lazy {
//        val db = Room
//            .databaseBuilder(applicationContext, AppDatabase::class.java, "app-db")
//            .build().apply(::addOrdersIfEmpty)

        bind<DataService>() with singleton { dataService }
//        bind<AppDatabase>() with singleton { db }
    }

//    private fun addOrdersIfEmpty(database: AppDatabase) {
//        val orderList = database.orderDao().getAll()
//        if (orderList.isEmpty()) {
//            val order = Order.create(
//                "Фонд Хабенского", 1
//            )
//            database.orderDao().insertAll(order)
//        }
//    }
}