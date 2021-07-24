package com.ishwar_arcore.nobroker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ishwar_arcore.nobroker.data.model.response.ResponseItem
import com.ishwar_arcore.nobroker.repository.ItemRepository

class ItemViewModel() : ViewModel() {
    val itemRepo = ItemRepository()

     fun getItemList(): MutableLiveData<List<ResponseItem>> {
        return itemRepo.getItemList()
    }

    fun fetchNewsFromServer() {
        itemRepo.fetchListFromServer()
    }

}