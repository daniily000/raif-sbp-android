package com.gitpub.raiffts.ui.new_order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.joda.time.LocalDate

class NewOrderViewModel : ViewModel() {

    val payerName = MutableLiveData<String>()
    val payerNumber = MutableLiveData<String>()
    val paymentDate = MutableLiveData<LocalDate>()

}