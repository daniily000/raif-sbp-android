package com.gitpub.raiffts.net.infrastructure

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.joda.time.LocalDate

object Serializer {
    @JvmStatic
    val gsonBuilder: GsonBuilder = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())

    @JvmStatic
    val gson: Gson by lazy {
        gsonBuilder.create()
    }
}
