package com.ishwar_arcore.nobroker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ishwar_arcore.nobroker.repository.ItemRepository

class ViewModelFactory(private val itemRepository: ItemRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ItemViewModel(itemRepository) as T
    }
}