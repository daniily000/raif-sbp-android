package com.gitpub.raiffts.service

import com.gitpub.raiffts.data.entities.Order

interface DataService {

    fun getOrders(): List<Order>
}
