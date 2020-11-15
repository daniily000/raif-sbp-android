package com.gitpub.raiffts.service

import com.gitpub.raiffts.data.entities.Order
import com.gitpub.raiffts.data.model.SavedPayment

class MockDataService : DataService {

    private val paymentsStorage: MutableList<SavedPayment> = mutableListOf()

    override fun getOrders(): List<Order> = listOf(
        Order.create(
            "Фонд Хабенского",
            1
        ),
        Order.create(
            "Фонд Хабенского",
            5
        ),
        Order.create(
            "Фонд Хабенского",
            10
        ),
    )

    override fun getPaymentsHistory(): List<SavedPayment> = paymentsStorage
    override fun savePayment(payment: SavedPayment) {
        paymentsStorage.add(payment)
    }
}