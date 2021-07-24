package com.ishwar_arcore.nobroker.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishwar_arcore.nobroker.data.model.response.ResponseItem
import com.ishwar_arcore.nobroker.databinding.ActivityItemListBinding
import com.ishwar_arcore.nobroker.ui.adapter.ItemAdapter
import com.ishwar_arcore.nobroker.viewmodel.ItemViewModel

class ItemListActivity : AppCompatActivity() {
    private val itemList = mutableListOf<ResponseItem>()

    private lateinit var mBinding: ActivityItemListBinding
    private lateinit var itemViewModel: ItemViewModel

    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(mBinding.root!!)
        initViews()
    }

    private fun initViews() {
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        itemAdapter = ItemAdapter(itemList as ArrayList<ResponseItem>)
        mBinding.rvItemList.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@ItemListActivity)
        }
    }
}