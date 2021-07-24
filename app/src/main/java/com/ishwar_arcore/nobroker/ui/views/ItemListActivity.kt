package com.ishwar_arcore.nobroker.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishwar_arcore.nobroker.data.model.response.ResponseItem
import com.ishwar_arcore.nobroker.databinding.ActivityItemListBinding
import com.ishwar_arcore.nobroker.ui.adapter.ItemAdapter
import com.ishwar_arcore.nobroker.ui.adapter.ItemClickListener
import com.ishwar_arcore.nobroker.viewmodel.ItemViewModel

class ItemListActivity : AppCompatActivity(), ItemClickListener {
    private lateinit var mBinding: ActivityItemListBinding

    private val itemList = mutableListOf<ResponseItem>()
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityItemListBinding.inflate(layoutInflater)
        setContentView(mBinding.root!!)
        initViews()
        setRecyclerView()
    }

    private fun initViews() {
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        itemAdapter = ItemAdapter(itemList, this)
        mBinding.rvItemList.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(this@ItemListActivity)
        }
    }

    private fun setRecyclerView() {
        itemViewModel.fetchNewsFromServer()
        itemViewModel.getItemList().observe(this, Observer {
            itemList.addAll(it)
            itemAdapter.notifyDataSetChanged()
        })
    }

    override fun onItemClick(model: ResponseItem) {
        val intent = Intent(this, ItemDescriptionActivity::class.java)
        intent.putExtra("model", model)
        startActivity(intent)
    }
}