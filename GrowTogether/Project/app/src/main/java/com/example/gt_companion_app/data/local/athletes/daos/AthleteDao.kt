package com.example.gt_companion_app.data.local.athletes.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gt_companion_app.data.local.athletes.Athlete

@Dao
interface AthleteDao {
    @Transaction
    @Query("select * from athletes")
    fun getAllAthletes(): LiveData<List<Athlete>>

    @Transaction
    @Query("select * from athletes where id=:athleteId")
    fun geAthlete(athleteId: String): LiveData<Athlete>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Athlete>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(athlete: Athlete)

}