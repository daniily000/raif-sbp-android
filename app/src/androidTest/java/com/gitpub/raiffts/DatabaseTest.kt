package com.gitpub.raiffts

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.gitpub.raiffts.data.database.AppDatabase
import com.gitpub.raiffts.data.entities.Order
import com.gitpub.raiffts.data.entities.Payment
import org.joda.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.gitpub.raiffts", appContext.packageName)
    }

    @Test
    fun checkDatabase() {
        val db = Room
            .databaseBuilder(
                InstrumentationRegistry.getInstrumentation().targetContext,
                AppDatabase::class.java,
                "app-db"
            )
            .build()

        println(db.orderEntityDao().getAll())
        println(db.paymentEntityDao().getAll())

        val order = Order.create("Khabensky Foundation", 5000)
        db.orderEntityDao().insertAll(order)
        val retrievedOrder = db.orderEntityDao().getById(order.id)
        assertEquals(order, retrievedOrder)

        val payment = Payment.create("Lexa s zavoda", "8 800 255 35 35", LocalDate.now(), order)
        db.paymentEntityDao().insertAll(payment)
        val retrievedPayment = db.paymentEntityDao().getById(payment.id)

        println("Order: $retrievedOrder")
        println("Payment: $retrievedPayment")

    }
}