package com.example.gt_companion_app.repos

import com.example.gt_companion_app.data.local.sets.SetLocalDataSource
import com.example.gt_companion_app.data.local.sets.SetWithAthleteScores
import com.example.gt_companion_app.data.remote.SetRemoteDataSource
import com.example.gt_companion_app.model.sets.SetModel
import com.example.gt_companion_app.util.performGetOperation

class SetRepository (
    val setLocalDataSource : SetLocalDataSource,
    val setRemoteDataSource: SetRemoteDataSource
) {
    fun getSet(id: String) = setLocalDataSource.getSet(id)
    fun getActiveSetForBout(id:String) = setLocalDataSource.getActiveSetForBout(id)

    fun getSets() = performGetOperation(
        databaseQuery = { setLocalDataSource.getSets() },
        networkCall = { setRemoteDataSource.getSets()},
        saveCallResult = { setLocalDataSource.saveSets(it) }
    )

    suspend fun createSet(set : SetModel){
        setLocalDataSource.createSet(set)
    }

    suspend fun updateSet(set: SetWithAthleteScores) {
        setLocalDataSource.update(set)
    }
}