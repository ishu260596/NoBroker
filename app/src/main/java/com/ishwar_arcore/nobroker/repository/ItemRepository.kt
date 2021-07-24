package com.ishwar_arcore.nobroker.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ishwar_arcore.nobroker.data.model.response.ResponseItem
import com.ishwar_arcore.nobroker.data.remote.ApiClient
import com.ishwar_arcore.nobroker.data.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class ItemRepository {
    private var itemList: MutableLiveData<List<ResponseItem>> = MutableLiveData()

    fun getItemList(): MutableLiveData<List<ResponseItem>> {
        return itemList
    }

    /**
     * Calling the API response and enqueuing the process
     * **/
    suspend fun newsByRegion(region: String) {
        val apiClient = RetrofitClient.getRetrofitInstance().create(ApiClient::class.java)
        apiClient.getItemList().enqueue(object :
            retrofit2.Callback<com.ishwar_arcore.nobroker.data.model.response.Response> {
            override fun onResponse(
                call: Call<com.ishwar_arcore.nobroker.data.model.response.Response>,
                response: Response<com.ishwar_arcore.nobroker.data.model.response.Response>
            ) {
                if (response.isSuccessful) {
                    itemList.postValue(response.body()?.response)
                }
            }

            override fun onFailure(
                call: Call<com.ishwar_arcore.nobroker.data.model.response.Response>,
                t: Throwable
            ) {
                itemList.postValue(null)
            }

        })
    }
}