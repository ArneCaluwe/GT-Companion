package com.example.gt_companion_app.data.local.bouts

import com.example.gt_companion_app.data.local.athletes.Athlete
import com.example.gt_companion_app.data.local.athletes.daos.AthleteDao
import com.example.gt_companion_app.data.local.bouts.daos.BoutDao
import com.example.gt_companion_app.data.local.scores.AthleteScore
import com.example.gt_companion_app.data.local.scores.daos.AthleteScoreDao
import com.example.gt_companion_app.data.local.sets.daos.SetDao
import com.example.gt_companion_app.model.bouts.BoutModel
import com.example.gt_companion_app.model.sets.SetModel
import kotlin.random.Random

class BoutLocalDataSource (private val boutDao: BoutDao,private val setDao: SetDao,private val athleteScoreDao: AthleteScoreDao,private val athleteDao: AthleteDao) {
    fun getBouts() = boutDao.getAllBouts()

    fun getBout(id: String) = boutDao.getBout(id)


    suspend fun createBout(bout : BoutModel){
        val newBout = bout.toDatabaseModel()

        boutDao.insert(newBout)
    }

    suspend fun saveBouts(bouts: List<BoutModel>) {
        for (boutModel in bouts) {
            boutDao.insert(boutModel.toDatabaseModel())
            for(setModel in boutModel.sets){
                setDao.insert(setModel.toDatabaseModel())
                for(athleteScoreModel in setModel.athleteScores) {
                    athleteDao.insert(athleteScoreModel.athlete.toDataBaseModel())
                    athleteScoreDao.insert(AthleteScore(setModel.id, athleteScoreModel.athlete.id, athleteScoreModel.score))
                }
            }
        }
    }

    suspend fun updateBout(bout: Bout) {
        boutDao.update(bout)
    }
}