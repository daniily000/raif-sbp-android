package com.gitpub.raiffts.data.model

data class PaymentLinks(val webLink: String, val qrLink: String) {
    fun createMessage() =
        """
            Используйте этот QR для оплаты услуги:
            $qrLink
            
            Или же воспользуйтесь ссылкой Системы Быстрых Платежей для оплаты через Ваше банковское приложение:
            $webLink
        """.trimIndent()
}