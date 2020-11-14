package com.gitpub.raiffts.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Order(
    @PrimaryKey
    @ColumnInfo(name = "order_id") val id: UUID,
    @ColumnInfo(name = "order_name") val orderName: String,
    @ColumnInfo(name = "order_cost") val orderCost: Int
) {
    companion object {
        fun create(orderName: String, orderCost: Int) = Order(
            id = UUID.randomUUID(),
            orderName, orderCost
        )
    }
}