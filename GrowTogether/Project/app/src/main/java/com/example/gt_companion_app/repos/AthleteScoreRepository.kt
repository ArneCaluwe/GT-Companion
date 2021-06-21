package com.example.gt_companion_app.repos

import com.example.gt_companion_app.data.local.scores.AthleteScore
import com.example.gt_companion_app.data.local.scores.AthleteScoreLocalDataSource

class AthleteScoreRepository(
    val dataSource: AthleteScoreLocalDataSource
) {
    fun getScoresForActiveSetInBout(id: String) = dataSource.getScoresForActiveSetInBout(id)

    suspend fun updateScores(scores: List<AthleteScore>) = dataSource.updateScores(scores)
}