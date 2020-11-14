package com.gitpub.raiffts.net.auth

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import java.io.IOException

class HttpBearerAuth(
    private var schema: String = "Bearer",
    var bearerToken: String = ""
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        var request = chain.request()

        // If the request already have an authorization (eg. Basic auth), do nothing
        if (request.header("Authorization") == null && bearerToken.isNotBlank()) {
            request = request.newBuilder()
                .addHeader("Authorization", headerValue())
                .build()
        }
        return chain.proceed(request)
    }

    private fun headerValue(): String {
        return if (schema.isNotBlank()) {
            "${upperCaseBearer()} $bearerToken"
        } else {
            bearerToken
        }
    }

    private fun upperCaseBearer(): String {
        return if (schema.toLowerCase().equals("bearer")) "Bearer" else schema
    }

}