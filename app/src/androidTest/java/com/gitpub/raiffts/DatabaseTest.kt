package com.gitpub.raiffts

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.gitpub.raiffts.data.database.AppDatabase
import com.gitpub.raiffts.data.entities.Order
import com.gitpub.raiffts.data.entities.Payment
import org.joda.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {


    companion object {

        lateinit var db: AppDatabase

        @BeforeClass
        @JvmStatic
        fun initDb() {
            db = Room
                .databaseBuilder(
                    InstrumentationRegistry.getInstrumentation().targetContext,
                    AppDatabase::class.java,
                    "app-db"
                )
                .build()
        }
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.gitpub.raiffts", appContext.packageName)
    }

    @Test
    fun checkDatabase() {

        println("All orders: ${db.orderDao().getAll()}")
        println("All payments: ${db.paymentDao().getAll()}")

        val order = Order.create(
            "Khabensky Foundation",
            5000
        )
        val payment = Payment.create(
            "Lexa s zavoda",
            "8 800 255 35 35",
            LocalDate.now(),
            order
        )

        db.orderDao().insertAll(order)
        val retrievedOrder = db.orderDao().getById(order.id)
        assertEquals(order, retrievedOrder)

        db.paymentDao().insertAll(payment)
        val retrievedPayment = db.paymentDao().getById(payment.id)

        println("Order: $retrievedOrder")
        println("Payment: $retrievedPayment")
    }

    @Test
    fun clearDatabase() {
        println("All orders: ${db.orderDao().getAll()}")
        println("All payments: ${db.paymentDao().getAll()}")
        db.clearAllTables()
        println("All orders: ${db.orderDao().getAll()}")
        println("All payments: ${db.paymentDao().getAll()}")
    }

}