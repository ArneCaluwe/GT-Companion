package com.example.gt_companion_app.data.local.bouts

import androidx.databinding.adapters.Converters
import androidx.room.*
import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Entity(
        tableName = "bouts",
        indices = [Index("id")]
)
class Bout(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val boutId: String,
        @ColumnInfo(name="active_set")
        var activeSet: Int,
        val name: String,
        @TypeConverters(Converters::class)
        val date: Long?,
) {
        fun parseDate() : String?{
                return date?.let { LocalDate.ofEpochDay(it/86400).toString() }
        }
}