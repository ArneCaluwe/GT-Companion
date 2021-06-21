package com.example.gt_companion_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gt_companion_app.data.local.athletes.Athlete
import com.example.gt_companion_app.data.local.athletes.daos.AthleteDao
import com.example.gt_companion_app.data.local.bouts.Bout
import com.example.gt_companion_app.data.local.bouts.daos.BoutDao
import com.example.gt_companion_app.data.local.scores.AthleteScore
import com.example.gt_companion_app.data.local.scores.daos.AthleteScoreDao
import com.example.gt_companion_app.data.local.sets.Set
import com.example.gt_companion_app.data.local.sets.daos.SetDao

@Database(
    entities = [
        Set::class,
        Bout::class,
        Athlete::class,
        AthleteScore::class
    ],
    version = 4,
    exportSchema = false
)
abstract  class AppDatabase : RoomDatabase() {
    abstract fun setDao(): SetDao
    abstract fun boutDao(): BoutDao
    abstract fun athleteScoreDao(): AthleteScoreDao
    abstract fun athleteDao(): AthleteDao
    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it}}

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "GT_CompanionDB")
                .fallbackToDestructiveMigration()
                .build()
    }
}