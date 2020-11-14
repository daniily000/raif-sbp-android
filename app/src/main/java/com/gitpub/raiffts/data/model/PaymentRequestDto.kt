package com.gitpub.raiffts.data.model

import org.joda.time.LocalDate
import java.math.BigDecimal

data class PaymentRequestDto(
    val account: String,
    val createDate: LocalDate,
    val order: String,
    val qrType: QrType,
    val sbpMerchantId: String,
    val qrExpirationDate: LocalDate? = null,
    val paymentDetails: String? = null,
    val currency: String = "RUB",
    val additionalInfo: String? = null,
    val amount: BigDecimal? = null,
)