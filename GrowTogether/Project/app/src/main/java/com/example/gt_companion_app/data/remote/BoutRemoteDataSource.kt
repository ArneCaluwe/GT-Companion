package com.example.gt_companion_app.data.remote

import com.example.gt_companion_app.data.BaseDataSource

class BoutRemoteDataSource(private val apiService: GT_ApiService) : BaseDataSource(){
    suspend fun getBouts() = getResult { apiService.getBouts() }
}