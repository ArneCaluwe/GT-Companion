package com.example.gt_companion_app.repos

import com.example.gt_companion_app.data.local.bouts.Bout
import com.example.gt_companion_app.data.local.bouts.BoutLocalDataSource
import com.example.gt_companion_app.data.remote.BoutRemoteDataSource
import com.example.gt_companion_app.model.bouts.BoutModel
import com.example.gt_companion_app.util.performGetOperation

class BoutRepository(
    val boutLocalDataSource: BoutLocalDataSource,
    val boutRemoteDataSource: BoutRemoteDataSource
) {
    fun getBout(id: String) = boutLocalDataSource.getBout(id)

    fun getBouts() = performGetOperation(
        databaseQuery = { boutLocalDataSource.getBouts() },
        networkCall = { boutRemoteDataSource.getBouts()},
        saveCallResult = { boutLocalDataSource.saveBouts(it) }
    )

    suspend fun createBout(bout : BoutModel){
        boutLocalDataSource.createBout(bout)
    }

    suspend fun update(bout: Bout) {
        boutLocalDataSource.updateBout(bout)
    }
}
