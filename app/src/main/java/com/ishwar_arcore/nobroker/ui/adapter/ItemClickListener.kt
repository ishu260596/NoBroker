package com.ishwar_arcore.nobroker.ui.adapter

import com.ishwar_arcore.nobroker.data.local.ItemEntity

interface ItemClickListener {
    fun onItemClick(model: ItemEntity)
}