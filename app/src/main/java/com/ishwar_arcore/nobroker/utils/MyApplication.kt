package com.ishwar_arcore.nobroker.utils

import android.app.Application
import com.ishwar_arcore.nobroker.data.local.ItemDatabase
import com.ishwar_arcore.nobroker.repository.ItemRepository

class MyApplication : Application() {

    val daoObj by lazy {
        val myRoomObj = ItemDatabase.getInstance(this)
        myRoomObj.getItemDAO()
    }

    val itemRepository by lazy {
        ItemRepository(daoObj)
    }
}