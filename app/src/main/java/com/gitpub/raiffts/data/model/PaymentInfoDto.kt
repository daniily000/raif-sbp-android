package com.gitpub.raiffts.data.model

data class PaymentInfoDto(
    val code: String,
    val qrId: String,
    val payload: String,
    val qrUrl: String
)