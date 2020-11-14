package com.gitpub.raiffts.net.infrastructure

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken.NULL
import com.google.gson.stream.JsonWriter
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import java.io.IOException

class LocalDateAdapter : TypeAdapter<LocalDate>() {

    @Throws(IOException::class)
    override fun write(out: JsonWriter?, value: LocalDate?) {
        if (value == null) {
            out?.nullValue()
        } else {
            out?.value(value.toDateTimeAtStartOfDay().toString())
        }
    }

    @Throws(IOException::class)
    override fun read(out: JsonReader?): LocalDate? {
        out ?: return null

        return when (out.peek()) {
            NULL -> {
                out.nextNull()
                null
            }
            else -> {
                LocalDateTime.parse(out.nextString()).toLocalDate()
            }
        }
    }
}
