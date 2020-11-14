package com.gitpub.raiffts.ui.new_order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gitpub.raiffts.data.entities.Order
import org.joda.time.LocalDate

class NewOrderViewModel : ViewModel() {

    val payerName = MutableLiveData<String>()
    val payerNumber = MutableLiveData<String>()
    val paymentDate = MutableLiveData<LocalDate>()
    val chosenOrders = MutableLiveData<List<Order>>()
}