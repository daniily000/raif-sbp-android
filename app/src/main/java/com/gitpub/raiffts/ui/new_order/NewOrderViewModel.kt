package com.gitpub.raiffts.ui.new_order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gitpub.raiffts.data.entities.Order
import com.gitpub.raiffts.data.entities.Payment
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import org.joda.time.LocalDate

class NewOrderViewModel : ViewModel() {

    val payerName = MutableLiveData<String>()
    val payerNumber = MutableLiveData<String>()
    val paymentDate = MutableLiveData<LocalDate>()
    val chosenOrders = MutableLiveData<List<Order>>()

    val submitted: Subject<Array<Payment>> = PublishSubject.create()

    fun submit() {
        val payerName = this.payerName.value ?: return
        val payerNumber = this.payerNumber.value ?: return
        val paymentDate = this.paymentDate.value ?: return
        val chosenOrders = this.chosenOrders.value ?: return

        submitted.onNext(
            chosenOrders
                .map { Payment.create(payerName, payerNumber, paymentDate, it) }
                .toTypedArray()
        )

    }
}