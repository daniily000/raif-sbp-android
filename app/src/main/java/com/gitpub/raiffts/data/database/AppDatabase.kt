package com.gitpub.raiffts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gitpub.raiffts.data.dao.OrderDao
import com.gitpub.raiffts.data.dao.PaymentDao
import com.gitpub.raiffts.data.entities.Order
import com.gitpub.raiffts.data.entities.Payment


@Database(
    entities = [Order::class, Payment::class], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun paymentDao(): PaymentDao
    abstract fun orderDao(): OrderDao
}
