package com.ishwar_arcore.nobroker.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ishwar_arcore.nobroker.data.local.ItemEntity
import com.ishwar_arcore.nobroker.repository.ItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    fun getItemListFromLocal(): LiveData<List<ItemEntity>> {
        return itemRepository.getItemListFromLocal()
    }

    fun fetchItemFromServer() {
        CoroutineScope(Dispatchers.IO).launch {
            itemRepository.fetchListFromServer()
        }

    }

}