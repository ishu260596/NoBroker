package com.ishwar_arcore.nobroker.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.ishwar_arcore.nobroker.data.local.ItemDAO
import com.ishwar_arcore.nobroker.data.local.ItemEntity
import com.ishwar_arcore.nobroker.data.model.ResponseItem
import com.ishwar_arcore.nobroker.data.remote.ApiClient
import com.ishwar_arcore.nobroker.data.remote.RetrofitClient
import com.ishwar_arcore.nobroker.utils.NETWORK_CALL
import com.ishwar_arcore.nobroker.utils.PreferenceHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class ItemRepository(private val itemDAO: ItemDAO) {

    fun getItemListFromLocal(): LiveData<List<ItemEntity>> {
        return itemDAO.getItemFromLocal()
    }

    /**
     * Calling the API response and enqueuing the process
     * **/
    suspend fun fetchListFromServer() {
        val apiClient = RetrofitClient.getRetrofitInstance()?.create(ApiClient::class.java)
        val result = apiClient?.getItemList()

        for (i in 0 until result?.size!!) {
            val responseItem = result[i]
            val itemEntity = ItemEntity(
                i + 1,
                responseItem.title,
                responseItem.image,
                responseItem.subTitle
            )
            /**
             * saving the item list in roomDatabase
             *
             * **/
            itemDAO.insertItem(itemEntity)

        }
    }
}


/** val apiClient = RetrofitClient.getRetrofitInstance()?.create(ApiClient::class.java)
apiClient?.getItemList()?.enqueue(object :
retrofit2.Callback<List<ResponseItem>> {
override fun onResponse(
call: Call<List<ResponseItem>>,
apiResponse: Response<List<ResponseItem>>
) {

if (apiResponse.isSuccessful) {
val list: List<ResponseItem>? =
apiResponse.body()

for (i in 0 until list?.size!!) {
val responseItem = list[i]
val itemEntity = ItemEntity(
i + 1,
responseItem.title,
responseItem.image,
responseItem.subTitle
)
/**
 * saving the item list in roomDatabase
 *
 * **/
CoroutineScope(Dispatchers.IO).launch {
itemDAO.insertItem(itemEntity)
}
}

PreferenceHelper.writeBooleanToPreference(NETWORK_CALL, true)

}

}

override fun onFailure(
call: Call<List<ResponseItem>>,
t: Throwable
) {
Log.d("tag", "onFailure")
}

})**/
