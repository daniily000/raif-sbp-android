package com.gitpub.raiffts.service

import com.gitpub.raiffts.data.entities.Order
import com.gitpub.raiffts.data.model.SavedPayment

interface DataService {

    fun getOrders(): List<Order>
    fun getPaymentsHistory(): List<SavedPayment>
    fun savePayment(payment: SavedPayment)
}
