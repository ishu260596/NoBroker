package com.ishwar_arcore.nobroker.utils

import android.app.Application
import com.droidnet.DroidNet
import com.ishwar_arcore.nobroker.data.local.ItemDatabase
import com.ishwar_arcore.nobroker.repository.ItemRepository

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DroidNet.init(this)
    }

    val daoObj by lazy {
        val myRoomObj = ItemDatabase.getInstance(this)
        myRoomObj.getItemDAO()
    }

    val itemRepository by lazy {
        ItemRepository(daoObj)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        DroidNet.getInstance().removeAllInternetConnectivityChangeListeners()
    }
}