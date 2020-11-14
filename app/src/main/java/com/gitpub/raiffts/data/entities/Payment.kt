package com.gitpub.raiffts.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.joda.time.LocalDate
import java.util.*

@Entity
@Parcelize
data class Payment(
    @PrimaryKey
    @ColumnInfo(name = "payment_id") val id: UUID,
    @ColumnInfo(name = "payer_name") val payerName: String,
    @ColumnInfo(name = "payer_number") val payerNumber: String,
    @ColumnInfo(name = "payment_date") val paymentDate: LocalDate,
    @Embedded
    val order: Order
) : Parcelable {
    companion object {
        fun create(payerName: String, payerNumber: String, paymentDate: LocalDate, order: Order) =
            Payment(
                id = UUID.randomUUID(),
                payerName, payerNumber, paymentDate, order
            )
    }
}