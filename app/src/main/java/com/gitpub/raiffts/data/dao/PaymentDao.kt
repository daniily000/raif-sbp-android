package com.gitpub.raiffts.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gitpub.raiffts.data.entities.Payment
import java.util.*

@Dao
interface PaymentDao {

    @Query("SELECT * FROM Payment")
    fun getAll(): List<Payment>

    @Query("SELECT * FROM Payment where :id = payment_id")
    fun getById(id: UUID): Payment

    @Insert
    fun insertAll(vararg users: Payment)

    @Delete
    fun delete(user: Payment)
}
