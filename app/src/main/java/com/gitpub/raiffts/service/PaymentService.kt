package com.gitpub.raiffts.service

import com.gitpub.raiffts.Environment
import com.gitpub.raiffts.net.api.PaymentApi
import com.gitpub.raiffts.net.auth.HttpBearerAuth
import com.gitpub.raiffts.net.infrastructure.ApiClient

class PaymentService(environment: Environment) {

    private val apiClient = ApiClient(environment.apiUrl)
        .addAuthorization("applicationAuthorization", HttpBearerAuth())
        .setBearerToken(environment.secretKey)

    private val paymentApi = apiClient.createService(PaymentApi::class.java)

    fun createPayment() {

    }
}
