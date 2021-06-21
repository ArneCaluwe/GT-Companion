package com.example.gt_companion_app.data.local.bouts

import androidx.room.*
import com.example.gt_companion_app.data.local.sets.Set
import com.example.gt_companion_app.data.local.sets.SetWithAthleteScores

data class BoutWithSets (
    @Embedded
    var bout: Bout,
    @Relation(entity = Set::class, parentColumn = "id", entityColumn = "bout_id")
    val sets : List<SetWithAthleteScores>

)