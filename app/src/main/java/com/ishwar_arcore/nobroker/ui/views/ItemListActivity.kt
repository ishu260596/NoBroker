package com.ishwar_arcore.nobroker.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishwar_arcore.nobroker.data.local.ItemDAO
import com.ishwar_arcore.nobroker.data.local.ItemEntity
import com.ishwar_arcore.nobroker.databinding.ActivityItemListBinding
import com.ishwar_arcore.nobroker.repository.ItemRepository
import com.ishwar_arcore.nobroker.ui.adapter.ItemAdapter
import com.ishwar_arcore.nobroker.ui.adapter.ItemClickListener
import com.ishwar_arcore.nobroker.utils.MyApplication
import com.ishwar_arcore.nobroker.viewmodel.ItemViewModel
import com.ishwar_arcore.nobroker.viewmodel.ViewModelFactory

class ItemListActivity : AppCompatActivity(), ItemClickListener {
    private lateinit var mBinding: ActivityItemListBinding

    private val itemList = mutableListOf<ItemEntity>()
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var itemDAO: ItemDAO
    private lateinit var itemRepo: ItemRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(mBinding.root!!)
        initViews()
        setRecyclerView()
    }

    private fun initViews() {
        val myApp = application as MyApplication
        itemDAO = myApp.daoObj
        itemRepo = myApp.itemRepository
        val viewModelFactory = ViewModelFactory(itemRepo)
        itemViewModel = ViewModelProvider(this, viewModelFactory)
            .get(ItemViewModel::class.java)

        itemAdapter = ItemAdapter(itemList, this)
        mBinding.rvItemList.apply {
            hasFixedSize()
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@ItemListActivity)
        }
        itemViewModel.fetchItemFromServer()

    }

    private fun setRecyclerView() {
        itemViewModel.getItemListFromLocal()
        itemViewModel.getItemList().observe(this, Observer {
            itemList.addAll(it)
            itemAdapter.notifyDataSetChanged()
        })
    }

    override fun onItemClick(model: ItemEntity) {
        val intent = Intent(this, ItemDescriptionActivity::class.java)
        intent.putExtra("model", model)
        startActivity(intent)
    }
}