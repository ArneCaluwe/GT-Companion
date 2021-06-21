package com.example.gt_companion_app.data.remote

import com.example.gt_companion_app.model.athletes.AthleteModel
import com.example.gt_companion_app.model.bouts.BoutModel
import com.example.gt_companion_app.model.sets.GetSetsApiModel
import com.example.gt_companion_app.model.sets.SetModel
import retrofit2.Response
import retrofit2.http.GET

interface GT_ApiService {
    @GET("api/bout")
    suspend fun getBouts(): Response<List<BoutModel>>
    @GET("api/set")
    suspend fun getSets(): Response<List<SetModel>>
    @GET("api/athlete")
    suspend fun getAthletes(): Response<List<AthleteModel>>


}