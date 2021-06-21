package com.example.gt_companion_app.data.local.scores.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gt_companion_app.data.local.scores.AthleteScore
import com.example.gt_companion_app.data.local.scores.AthleteScoreWithAthletes

@Dao
interface AthleteScoreDao {
    @Transaction
    @Query("select * from athlete_scores")
    fun getAllAthletes(): LiveData<List<AthleteScore>>

    @Transaction
    @Query("select * from athlete_scores where athlete_id=:athleteId")
    fun getAllForAthlete(athleteId: String): LiveData<List<AthleteScore>>

    @Transaction
    @Query("select * from athlete_scores where set_id=:setId")
    fun getAllForSet(setId: String): LiveData<List<AthleteScore>>

    @Transaction
    @Query("select * from athlete_scores where athlete_id=:athleteId and set_id=:setId")
    fun get(athleteId: String, setId: String): LiveData<AthleteScore>

    @Transaction
    @Query( "SELECT * FROM athlete_scores WHERE set_id = ( SELECT id FROM Sets WHERE bout_id=:id AND  [Order] IN ( SELECT active_set from Bouts where [Id] =:id ))")
    fun getScoresForActiveSetInBout(id: String): LiveData<List<AthleteScoreWithAthletes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(athleteScore: AthleteScore)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<AthleteScore>)

    @Update( onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateScore(score: AthleteScore)


}