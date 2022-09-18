package com.ishwar_arcore.nobroker.repository


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import com.ishwar_arcore.nobroker.data.local.ItemDAO
import com.ishwar_arcore.nobroker.data.local.ItemEntity
import com.ishwar_arcore.nobroker.data.remote.ApiClient
import com.ishwar_arcore.nobroker.data.remote.RetrofitClient
import com.ishwar_arcore.nobroker.utils.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class ItemRepository(private val itemDAO: ItemDAO) {

    fun getItemListFromLocal(): LiveData<List<ItemEntity>> {
        return itemDAO.getItemFromLocal()
    }

    /**
     * Calling the API response and enqueuing the process
     * **/
    suspend fun fetchListFromServer(context: Context) {
        val apiClient = RetrofitClient.getRetrofitInstance()?.create(ApiClient::class.java)
        val result = apiClient?.getItemList()

        for (i in 0 until result?.size!!) {
            val responseItem = result[i]
            val bitmap = Converters.getBitmap(responseItem.image.toString(), context)
            val itemEntity = ItemEntity(
                i + 1,
                responseItem.title,
                responseItem.image,
                bitmap,
                responseItem.subTitle)
            /**
             * saving the item list in roomDatabase
             *
             * **/
            itemDAO.insertItem(itemEntity)
        }
    }

}


