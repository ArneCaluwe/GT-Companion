package com.example.gt_companion_app.model.sets

import android.os.Parcelable
import com.example.gt_companion_app.data.local.scores.AthleteScore
import com.example.gt_companion_app.data.local.sets.Set
import com.example.gt_companion_app.model.scores.AthleteScoreModel
import kotlinx.android.parcel.Parcelize
import java.time.Duration
import java.util.stream.Collectors

@Parcelize
data class SetModel (
    val id: String,
    val boutId: String,
    val timeRemaining: Long,
    val completed: Boolean,
    val order: Int,
    val athleteScores: List<AthleteScoreModel>
) : Parcelable{
    fun toDatabaseModel(): Set {
        return Set(id, boutId, timeRemaining.toDouble(), completed, order)
    }

//    fun athleteScoresToDatabaseModel(): List<AthleteScore> {
//        return this.athleteScores.toList().stream().map { ats -> ats.toDatabaseModel() }
//            .collect(Collectors.toList())
//    }
}