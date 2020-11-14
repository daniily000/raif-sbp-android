package com.gitpub.raiffts.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gitpub.raiffts.data.entities.Order
import java.util.*

@Dao
interface OrderDao {

    @Query("SELECT * FROM `Order`")
    fun getAll(): List<Order>

    @Query("SELECT * FROM `Order` where :id = order_id")
    fun getById(id: UUID): Order

    @Insert
    fun insertAll(vararg users: Order)

    @Delete
    fun delete(user: Order)
}
