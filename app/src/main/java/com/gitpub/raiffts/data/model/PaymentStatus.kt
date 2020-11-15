package com.gitpub.raiffts.data.model

import com.google.gson.annotations.SerializedName

enum class PaymentStatus {
    @SerializedName("NTST")
    NotPaidYet,

    @SerializedName("ACWP")
    PaymentComplete
}
