package com.example.gt_companion_app.data

import androidx.room.TypeConverter
import java.time.LocalDate
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

class Converters{
    @TypeConverter
    fun convertToDatabaseColumn(locDate: LocalDate?): Long? {
        return locDate?.let { locDate.toEpochDay() }
    }

    @TypeConverter
    fun convertToEntityAttribute(sqlDate: Long?): LocalDate? {
        if(sqlDate != null)
            return LocalDate.ofEpochDay(sqlDate)
        return null
    }

    @ExperimentalTime
    @TypeConverter
    fun convertToEntityAttribute(sqlDate: Double?): Duration? {
        if(sqlDate != null)
            return sqlDate.toDuration(TimeUnit.MILLISECONDS)
        return null
    }
    @ExperimentalTime
    @TypeConverter
    fun convertToEntityAttribute(sqlDate: Duration?): Double? {
        if(sqlDate != null)
            return sqlDate.toDouble (TimeUnit.MILLISECONDS)
        return null
    }
}