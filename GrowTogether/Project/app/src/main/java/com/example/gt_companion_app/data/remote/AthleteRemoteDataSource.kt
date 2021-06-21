package com.example.gt_companion_app.data.remote

import com.example.gt_companion_app.data.BaseDataSource

class AthleteRemoteDataSource(private val apiService: GT_ApiService) : BaseDataSource(){
    suspend fun getAthletes() = getResult { apiService.getAthletes() }

}