package com.gitpub.raiffts.net.infrastructure

import com.gitpub.raiffts.net.auth.HttpBearerAuth
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient(
    private var baseUrl: String = defaultBasePath,
    private val okHttpClientBuilder: OkHttpClient.Builder? = null,
    private val serializerBuilder: GsonBuilder = Serializer.gsonBuilder
) {
    private val apiAuthorizations = mutableMapOf<String, Interceptor>()
    var logger: ((String) -> Unit)? = null

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(serializerBuilder.create()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }

    private val clientBuilder: OkHttpClient.Builder by lazy {
        okHttpClientBuilder ?: defaultClientBuilder
    }

    private val defaultClientBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient().newBuilder()
    }

    init {
        normalizeBaseUrl()
    }

    constructor(
        baseUrl: String = defaultBasePath,
        okHttpClientBuilder: OkHttpClient.Builder? = null,
        serializerBuilder: GsonBuilder = Serializer.gsonBuilder,
        authNames: Array<String>
    ) : this(baseUrl, okHttpClientBuilder, serializerBuilder) {
        authNames.forEach { authName ->
            val auth = when (authName) {
                "bearer" -> HttpBearerAuth("bearer")
                else -> throw RuntimeException("auth name $authName not found in available auth names")
            }
            addAuthorization(authName, auth);
        }
    }

    constructor(
        baseUrl: String = defaultBasePath,
        okHttpClientBuilder: OkHttpClient.Builder? = null,
        serializerBuilder: GsonBuilder = Serializer.gsonBuilder,
        authName: String,
        bearerToken: String
    ) : this(baseUrl, okHttpClientBuilder, serializerBuilder, arrayOf(authName)) {
        setBearerToken(bearerToken)
    }

    fun setBearerToken(bearerToken: String): ApiClient {
        apiAuthorizations.values.runOnFirst<Interceptor, HttpBearerAuth> {
            this.bearerToken = bearerToken
        }
        return this
    }

    /**
     * Adds an authorization to be used by the client
     * @param authName Authentication name
     * @param authorization Authorization interceptor
     * @return ApiClient
     */
    fun addAuthorization(authName: String, authorization: Interceptor): ApiClient {
        if (apiAuthorizations.containsKey(authName)) {
            throw RuntimeException("auth name $authName already in api authorizations")
        }
        apiAuthorizations[authName] = authorization
        clientBuilder.addInterceptor(authorization)
        return this
    }

    fun setLogger(logger: (String) -> Unit): ApiClient {
        this.logger = logger
        return this
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofitBuilder.client(clientBuilder.build()).build().create(serviceClass)
    }

    private fun normalizeBaseUrl() {
        if (!baseUrl.endsWith("/")) {
            baseUrl += "/"
        }
    }

    private inline fun <T, reified U> Iterable<T>.runOnFirst(callback: U.() -> Unit) {
        for (element in this) {
            if (element is U) {
                callback.invoke(element)
                break
            }
        }
    }

    companion object {
        @JvmStatic
        val defaultBasePath: String by lazy {
            "https://ghp-staging.azurewebsites.net/"
        }
    }
}