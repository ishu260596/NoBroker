package com.ishwar_arcore.nobroker.data.remote

import com.ishwar_arcore.nobroker.data.model.response.Response
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {
    @GET("b/60fa8fefa917050205ce5470")
    suspend fun getItemList(): Call<Response>
}