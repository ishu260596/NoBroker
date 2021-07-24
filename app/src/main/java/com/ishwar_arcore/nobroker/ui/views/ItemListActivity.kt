package com.ishwar_arcore.nobroker.ui.views

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.droidnet.DroidListener
import com.droidnet.DroidNet
import com.ishwar_arcore.nobroker.R
import com.ishwar_arcore.nobroker.data.local.ItemDAO
import com.ishwar_arcore.nobroker.data.local.ItemEntity
import com.ishwar_arcore.nobroker.databinding.ActivityItemListBinding
import com.ishwar_arcore.nobroker.repository.ItemRepository
import com.ishwar_arcore.nobroker.ui.adapter.ItemAdapter
import com.ishwar_arcore.nobroker.ui.adapter.ItemClickListener
import com.ishwar_arcore.nobroker.utils.LIST_SIZE
import com.ishwar_arcore.nobroker.utils.MyApplication
import com.ishwar_arcore.nobroker.utils.NETWORK_CALL
import com.ishwar_arcore.nobroker.utils.PreferenceHelper
import com.ishwar_arcore.nobroker.viewmodel.ItemViewModel
import com.ishwar_arcore.nobroker.viewmodel.ViewModelFactory


class ItemListActivity : AppCompatActivity(), ItemClickListener, DroidListener {
    private lateinit var mBinding: ActivityItemListBinding

    private val itemList = arrayListOf<ItemEntity>()
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var itemDAO: ItemDAO
    private lateinit var itemRepo: ItemRepository
    private lateinit var mDroidNet: DroidNet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =
            ActivityItemListBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initViews()

        /**
         * Search view implemented
         * **/
        mBinding.svItemList.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                itemViewModel.getItemBySearch(newText).observe(this@ItemListActivity,
                    Observer {
                        resetRecyclerView(it)
                    })
                return false
            }
        })

        itemViewModel.getItemListFromLocal().observe(this, Observer {
            if (it != null) {
                if (it.size == LIST_SIZE)
                    resetRecyclerView(it)
            }
        })
    }

    private fun resetRecyclerView(list: List<ItemEntity>) {
        itemList.clear()
        itemList.addAll(list)
        shimmerDisplay()
        itemAdapter.notifyDataSetChanged()
    }

    private fun initViews() {
        mBinding.shimmerFrameLayout.startShimmerAnimation()

        PreferenceHelper.getSharedPreferences(this)
        val searchIcon = mBinding.svItemList.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK)
        val cancelIcon = mBinding.svItemList.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.BLACK)
        val textView = mBinding.svItemList.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.BLACK)

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

        mDroidNet = DroidNet.getInstance()
        mDroidNet.addInternetConnectivityListener(this)
    }

    private fun shimmerDisplay() {
        mBinding.apply {
            shimmerFrameLayout.stopShimmerAnimation()
            shimmerFrameLayout.visibility = View.GONE
            rvItemList.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDroidNet.removeInternetConnectivityChangeListener(this)
    }

    override fun onItemClick(model: ItemEntity) {
        val intent = Intent(this, ItemDescriptionActivity::class.java)
        intent.putExtra("model", model)
        startActivity(intent)
    }

    override fun onInternetConnectivityChanged(isConnected: Boolean) {
        if (isConnected) {
            if (PreferenceHelper.getBooleanFromPreference(NETWORK_CALL)) {
                itemViewModel.fetchItemFromServer()
                PreferenceHelper.writeBooleanToPreference(NETWORK_CALL, false)
            }
        }
    }
}

