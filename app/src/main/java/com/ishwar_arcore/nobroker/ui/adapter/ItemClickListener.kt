package com.ishwar_arcore.nobroker.ui.adapter

import com.ishwar_arcore.nobroker.data.model.response.ResponseItem

interface ItemClickListener {
    fun onItemClick(model: ResponseItem)
}