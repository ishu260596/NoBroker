package com.ishwar_arcore.nobroker.repository


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import com.ishwar_arcore.nobroker.data.local.ItemDAO
import com.ishwar_arcore.nobroker.data.local.ItemEntity
import com.ishwar_arcore.nobroker.data.remote.ApiClient
import com.ishwar_arcore.nobroker.data.remote.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class ItemRepository(private val itemDAO: ItemDAO) {

    fun getItemListFromLocal(): LiveData<List<ItemEntity>> {
        return itemDAO.getItemFromLocal()
    }

    fun getItemBySearch(newText: String?): LiveData<List<ItemEntity>> {
        return itemDAO.getItemBySearch(newText!!)
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


