package com.gitpub.raiffts.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class Order(
    @PrimaryKey
    @ColumnInfo(name = "order_id") val id: UUID,
    @ColumnInfo(name = "order_name") val orderName: String,
    @ColumnInfo(name = "order_cost") val orderCost: Int
) : Parcelable {
    companion object {
        fun create(orderName: String, orderCost: Int) = Order(
            id = UUID.randomUUID(),
            orderName, orderCost
        )
    }
}