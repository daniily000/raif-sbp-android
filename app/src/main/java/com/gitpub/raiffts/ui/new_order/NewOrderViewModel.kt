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

    var payerNameCached: String? = null
    var payerNumberCached: String? = null
    var paymentDateCached: LocalDate? = null
    var chosenOrdersCached: List<Order>? = null

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

    fun consumeForm(payments: List<Payment>?) {
        payments?.apply {
            payerName.postValue(first().payerName)
            payerNumber.postValue(first().payerNumber)
            paymentDate.postValue(first().paymentDate)
            chosenOrders.postValue(map { it.order })

            payerNameCached = first().payerName
            payerNumberCached = first().payerNumber
            paymentDateCached = first().paymentDate
            chosenOrdersCached = map { it.order }
        }
        ChosenOrderForm.payments = null
    }

    fun cacheData() {
        payerNameCached = this.payerName.value
        payerNumberCached = this.payerNumber.value
        paymentDateCached = this.paymentDate.value
        chosenOrdersCached = this.chosenOrders.value
    }
}