package com.gitpub.raiffts.service

import com.gitpub.raiffts.data.entities.Order

class MockDataService : DataService {

    override fun getOrders(): List<Order> = listOf(
        Order.create(
            "Фонд Хабенского",
            1
        )
    )
}