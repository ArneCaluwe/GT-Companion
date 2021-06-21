package com.example.gt_companion_app.data.remote

import com.example.gt_companion_app.data.BaseDataSource

class SetRemoteDataSource (private val apiService: GT_ApiService) : BaseDataSource(){
    suspend fun getSets() = getResult { apiService.getSets() }

}