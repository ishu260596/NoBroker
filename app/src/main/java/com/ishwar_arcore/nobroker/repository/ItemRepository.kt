package com.ishwar_arcore.nobroker.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ishwar_arcore.nobroker.data.model.response.ApiResponse
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
    fun fetchListFromServer() {
        val apiClient = RetrofitClient.getRetrofitInstance()?.create(ApiClient::class.java)
        apiClient?.getItemList()?.enqueue(object :
            retrofit2.Callback<List<ResponseItem>> {
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                apiResponse: Response<List<ResponseItem>>
            ) {
                if (apiResponse.isSuccessful) {
                    itemList.postValue(apiResponse.body())
                }
            }

            override fun onFailure(
                call: Call<List<ResponseItem>>,
                t: Throwable
            ) {
                Log.d("tag", "onFailure")
//                itemList.postValue(null)
            }

        })
    }
}