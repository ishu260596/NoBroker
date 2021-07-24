package com.ishwar_arcore.nobroker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ishwar_arcore.nobroker.data.local.ItemEntity
import com.ishwar_arcore.nobroker.data.model.ResponseItem
import com.ishwar_arcore.nobroker.repository.ItemRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    private var itemList: MutableLiveData<List<ItemEntity>> = MutableLiveData()

    fun getItemList(): MutableLiveData<List<ItemEntity>> {
        return itemList
    }

    fun getItemListFromLocal() {
        CoroutineScope(Dispatchers.IO).launch {
            val list = itemRepository.getItemListFromLocal()

            withContext(Dispatchers.Main) {
                itemList.postValue(list)
            }
        }
    }

    fun fetchItemFromServer() {
        itemRepository.fetchListFromServer()
    }

}