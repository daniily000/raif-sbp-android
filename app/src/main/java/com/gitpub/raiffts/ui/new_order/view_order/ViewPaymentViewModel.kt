package com.gitpub.raiffts.ui.new_order.view_order

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gitpub.raiffts.data.entities.Payment
import com.gitpub.raiffts.data.model.SavedPayment
import com.gitpub.raiffts.data.model.SnackbarInfo
import com.gitpub.raiffts.service.PaymentService
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.instance

class ViewPaymentViewModel(context: Context) : ViewModel(), DIAware {

    override val di by di(context)

    private val paymentService: PaymentService by instance()
    private val _links = MutableLiveData<SavedPayment>()

    val links: LiveData<SavedPayment> = _links
    val snackbarChannel: Subject<SnackbarInfo> = PublishSubject.create()

    fun sendRequest(payments: List<Payment>) {
        paymentService.createPayment(payments) {
            if (it != null) {
                _links.postValue(it)
            } else {
                snackbarChannel.onNext(
                    SnackbarInfo
                        .Builder()
                        .withAction {
                            sendRequest(payments)
                        }
                        .build()
                )
            }
        }
    }
}