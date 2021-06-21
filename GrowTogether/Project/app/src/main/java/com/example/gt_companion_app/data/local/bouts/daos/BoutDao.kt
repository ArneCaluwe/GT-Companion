package com.example.gt_companion_app.data.local.bouts.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gt_companion_app.data.local.bouts.Bout
import com.example.gt_companion_app.data.local.bouts.BoutWithSets

@Dao
interface BoutDao {
    @Transaction
    @Query("select * from bouts")
    fun getAllBouts(): LiveData<List<BoutWithSets>>

    @Transaction
    @Query("select * from bouts where id=:boutId")
    fun getBout(boutId: String): LiveData<BoutWithSets>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Bout>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(athlete: Bout)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(bout: Bout)
}