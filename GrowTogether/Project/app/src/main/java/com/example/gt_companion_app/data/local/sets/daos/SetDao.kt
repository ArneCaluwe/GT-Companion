package com.example.gt_companion_app.data.local.sets.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gt_companion_app.data.local.sets.Set
import com.example.gt_companion_app.data.local.sets.SetWithAthleteScores

@Dao
interface SetDao {
    @Transaction
    @Query("select * from sets order by sets.`order`")
    fun getAllSets(): LiveData<List<SetWithAthleteScores>>

    @Transaction
    @Query("select * from sets where id=:setId order by sets.`order`")
    fun getSet(setId: String): LiveData<SetWithAthleteScores>

    @Transaction
    @Query("SELECT * FROM Sets WHERE Bout_id=:boutId AND  [order] IN (  SELECT active_set from bouts where [id] =:boutId)")
    fun getActiveSetForBout(boutId: String) : LiveData<SetWithAthleteScores>

    @Update(entity = Set::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSet(set: Set)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Set>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(set: Set)
}