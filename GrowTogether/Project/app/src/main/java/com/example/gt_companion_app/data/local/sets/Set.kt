package com.example.gt_companion_app.data.local.sets

import androidx.room.*
import com.example.gt_companion_app.data.Converters
import com.example.gt_companion_app.data.local.bouts.Bout
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@Entity(
    tableName = "sets",
    foreignKeys = [
        ForeignKey(entity = Bout::class, parentColumns = ["id"], childColumns = ["bout_id"])
    ],
    indices = [Index("id"), Index("bout_id")]
    )
data class Set constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val setId: String,
    @ColumnInfo(name = "bout_id")
    val boutId: String,
    @ColumnInfo(name = "time_remaining")
    @TypeConverters(Converters::class)
    var timeRemaining: Double?,
    val completed: Boolean,
    val order: Int = 1
)