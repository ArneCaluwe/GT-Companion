package com.example.gt_companion_app.model.scores

import android.os.Parcelable
import com.example.gt_companion_app.data.local.scores.AthleteScore
import com.example.gt_companion_app.model.athletes.AthleteModel
import com.example.gt_companion_app.model.sets.SetModel
import kotlinx.android.parcel.Parcelize

@Parcelize
class AthleteScoreModel (
    val athlete: AthleteModel,
    val set: SetModel?,
    val score: Int
) : Parcelable{
    fun toDatabaseModel(): AthleteScore {
        TODO()//return AthleteScore(setId = set?.id, athleteId = athlete.id, score = score)
    }
}