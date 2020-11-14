package com.gitpub.raiffts.data.database

import androidx.room.TypeConverter
import org.joda.time.LocalDate
import java.util.*

class Converters {

    @TypeConverter
    fun uuidToString(uuid: UUID) = uuid.toString()

    @TypeConverter
    fun stringToUuid(uuid: String): UUID = UUID.fromString(uuid)

    @TypeConverter
    fun localDateToString(date: LocalDate): String = date.toString()

    @TypeConverter
    fun stringToLocalDate(date: String): LocalDate = LocalDate.parse(date)
}