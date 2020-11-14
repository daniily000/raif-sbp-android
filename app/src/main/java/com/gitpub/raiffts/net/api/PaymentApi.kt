package com.gitpub.raiffts.net.api

import com.gitpub.raiffts.data.model.PaymentInfoDto
import com.gitpub.raiffts.data.model.PaymentRequestDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentApi {

    @POST("qr/register")
    fun registerPayment(@Body dto: PaymentRequestDto): Single<PaymentInfoDto>
}