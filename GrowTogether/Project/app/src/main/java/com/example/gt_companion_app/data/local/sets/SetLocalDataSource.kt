package com.example.gt_companion_app.data.local.sets

import com.example.gt_companion_app.data.local.scores.daos.AthleteScoreDao
import com.example.gt_companion_app.data.local.sets.daos.SetDao
import com.example.gt_companion_app.model.sets.SetModel
import kotlin.random.Random

class SetLocalDataSource (val setDao : SetDao, val athleteScoreDao: AthleteScoreDao) {
    fun getSets() = setDao.getAllSets()

    fun getSet(id: String) = setDao.getSet(id)

    fun getActiveSetForBout(id: String) = setDao.getActiveSetForBout(id)


    suspend fun createSet(set : SetModel){
        val newSet = set.toDatabaseModel()

        setDao.insert(newSet)
    }

    suspend fun update(set: SetWithAthleteScores){
        setDao.updateSet(set.set)
    }

    suspend fun saveSets(sets: List<SetModel>) {
        for (setModel in sets) {
            setDao.insert(setModel.toDatabaseModel())
            
        }
    }
}