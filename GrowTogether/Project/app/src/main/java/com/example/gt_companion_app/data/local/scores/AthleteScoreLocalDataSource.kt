package com.example.gt_companion_app.data.local.scores

import com.example.gt_companion_app.data.local.scores.daos.AthleteScoreDao
import com.example.gt_companion_app.data.local.sets.SetWithAthleteScores
import com.example.gt_companion_app.data.local.sets.daos.SetDao
import com.example.gt_companion_app.model.sets.SetModel

class AthleteScoreLocalDataSource (val athleteScoreDao: AthleteScoreDao) {

    fun getScoresForActiveSetInBout(id: String) = athleteScoreDao.getScoresForActiveSetInBout(id)
    suspend fun updateScores(scores: List<AthleteScore>) {
        scores.forEach{
            athleteScoreDao.updateScore(it)
        }
    }

}