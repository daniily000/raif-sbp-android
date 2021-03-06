package com.gitpub.raiffts.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentLinks(val webLink: String, val qrLink: String) : Parcelable {
    fun createMessage() =
        """
            Используйте этот QR для оплаты услуги:
            $qrLink
            
            Или же воспользуйтесь ссылкой Системы Быстрых Платежей для оплаты через Ваше банковское приложение:
            $webLink
        """.trimIndent()
}