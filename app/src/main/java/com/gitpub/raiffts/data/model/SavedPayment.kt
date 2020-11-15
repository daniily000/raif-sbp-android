package com.gitpub.raiffts.data.model

import android.os.Parcelable
import com.gitpub.raiffts.data.entities.Payment
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SavedPayment(
    val id: String,
    val payments: List<Payment>,
    val links: PaymentLinks
) : Parcelable
