package com.gitpub.raiffts.service

import com.gitpub.raiffts.Environment
import com.gitpub.raiffts.data.entities.Payment
import com.gitpub.raiffts.data.model.*
import com.gitpub.raiffts.net.api.PaymentApi
import com.gitpub.raiffts.net.auth.HttpBearerAuth
import com.gitpub.raiffts.net.infrastructure.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.joda.time.LocalDate
import retrofit2.HttpException
import java.util.*

class PaymentService(environment: Environment, private val dataService: DataService) {

    private val apiClient = ApiClient(environment.apiUrl)
        .addAuthorization("applicationAuthorization", HttpBearerAuth())
        .setBearerToken(environment.secretKey)

    private val mockMerchantId = environment.khabenskyId
    private val paymentApi = apiClient.createService(PaymentApi::class.java)

    fun createPayment(
        payments: List<Payment>,
        callback: (SavedPayment?) -> Unit
    ): @NonNull Disposable {
        val createDate: LocalDate = payments.first().paymentDate
        val order: String = UUID.randomUUID().toString()
        val qrType: QrType = QrType.QRDynamic
        val sbpMerchantId: String = mockMerchantId
        val price = payments.map { it.order.orderCost }.reduce { acc, price -> acc + price }
        val paymentDetails = payments.joinToString(separator = "\n") { it.order.orderName }

        return paymentApi.registerPayment(
            PaymentRequestDto(
                createDate = createDate,
                order = order,
                qrType = qrType,
                sbpMerchantId = sbpMerchantId,
                amount = price.toBigDecimal(),
                paymentDetails = paymentDetails
            )
        ).doOnSuccess {
            if (it.code != "SUCCESS") {
                throw RuntimeException("paymentApi.registerPayment failed with code ${it.code}")
            }
        }.doOnError {
            it.describeThrowable()
            callback.invoke(null)
        }.onErrorComplete()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { info ->
                val savedPayment = SavedPayment(
                    info.qrId,
                    payments,
                    PaymentLinks(
                        info.payload, info.qrUrl
                    )
                )
                dataService.savePayment(savedPayment)
                callback.invoke(savedPayment)
            }
    }

    fun getPaymentStatus(id: String, callback: (PaymentStatus?) -> Unit) =
        paymentApi.getPaymentStatus(id)
            .doOnSuccess {
                if (it !in PaymentStatus.values()) {
                    throw RuntimeException("paymentApi.getPaymentStatus failed with response \"$it\"")
                }
            }.doOnError {
                it.describeThrowable()
                callback.invoke(null)
            }.onErrorComplete()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { callback.invoke(it) }
}

private fun Throwable.describeThrowable() {
    val descriptionBuilder = StringBuilder()
    descriptionBuilder.appendLine("Request failed: ${this.javaClass.name} - ${this.message}")
    if (this is HttpException) {
        descriptionBuilder.appendLine("HttpException: ${this.code()} ${this.message()}")
        descriptionBuilder.appendLine(
            "${this.response()?.raw()?.request()?.method()} ${
                this.response()?.raw()?.request()?.url()
            }"
        )
    }
    RuntimeException(descriptionBuilder.toString(), this).printStackTrace()
}
