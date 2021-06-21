package com.example.gt_companion_app.data.local.scores

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gt_companion_app.data.local.athletes.Athlete
import com.example.gt_companion_app.data.local.bouts.Bout
import com.example.gt_companion_app.data.local.sets.Set
import com.example.gt_companion_app.data.local.sets.SetWithAthleteScores

data class AthleteScoreWithAthletes (
    @Embedded
    var athleteScore: AthleteScore,
    @Relation(parentColumn = "athlete_id", entityColumn = "id")
    var athlete: Athlete,
    @Relation(parentColumn = "set_id", entityColumn = "id")
    var set: Set,
)
