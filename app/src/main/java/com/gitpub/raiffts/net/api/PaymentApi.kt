package com.gitpub.raiffts.net.api

import com.gitpub.raiffts.data.model.PaymentCredsDto
import com.gitpub.raiffts.data.model.PaymentRequestDto
import com.gitpub.raiffts.data.model.PaymentStatus
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PaymentApi {

    @POST("qr/register")
    fun registerPayment(@Body dto: PaymentRequestDto): Single<PaymentCredsDto>

    @GET("qr/{id}/payment-status")
    fun getPaymentStatus(@Path("id") id: String): Single<PaymentStatus>
}