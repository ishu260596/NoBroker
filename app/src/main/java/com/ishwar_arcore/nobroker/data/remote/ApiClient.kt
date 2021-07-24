package com.ishwar_arcore.nobroker.data.remote

import com.ishwar_arcore.nobroker.data.model.ResponseItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {
    @GET("/b/60fc4870a263d14a297b960b")
    suspend fun getItemList(): List<ResponseItem>
}