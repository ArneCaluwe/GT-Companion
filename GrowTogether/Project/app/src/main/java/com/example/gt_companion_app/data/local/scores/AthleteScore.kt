package com.example.gt_companion_app.data.local.scores

import androidx.room.*
import com.example.gt_companion_app.data.local.athletes.Athlete
import com.example.gt_companion_app.data.local.sets.Set

@Entity(
    tableName = "athlete_scores",
    primaryKeys = [ "set_id", "athlete_id" ],
    foreignKeys = [
        ForeignKey(entity = Athlete::class, parentColumns = ["id"], childColumns = ["athlete_id"]),
        ForeignKey(entity = Set::class, parentColumns = ["id"], childColumns = ["set_id"])
    ],
    indices = [Index("set_id" ), Index("athlete_id")]
)
/**
 * [AthleteScore] represents when a user adds a [Athlete] to an Set, with useful metadata.
 * The Score property shows the points scored by that athlete in that set
 *
 * Declaring the column info allows for the renaming of variables without implementing a
 * database migration, as the column name would not change.
 */
data class AthleteScore (
    @ColumnInfo(name = "set_id")
    val setId : String,
    @ColumnInfo(name = "athlete_id")
    val athleteId: String,
    var score: Int

)