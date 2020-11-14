package com.gitpub.raiffts.data.model

import com.google.gson.annotations.SerializedName

enum class QrType {

    @SerializedName("QRStatic")
    QRStatic,
    @SerializedName("QRDynamic")
    QRDynamic
}