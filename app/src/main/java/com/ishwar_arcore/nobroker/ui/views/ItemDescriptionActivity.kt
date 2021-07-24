package com.ishwar_arcore.nobroker.ui.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ishwar_arcore.nobroker.R
import com.ishwar_arcore.nobroker.data.local.ItemEntity
import com.ishwar_arcore.nobroker.data.model.ResponseItem
import com.ishwar_arcore.nobroker.databinding.ActivityItemDescriptionBinding

class ItemDescriptionActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityItemDescriptionBinding

    private var model: ItemEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityItemDescriptionBinding.inflate(layoutInflater)
        setContentView(mBinding.root!!)
        initViews()
    }

    private fun initViews() {
        if (intent != null && intent.extras != null) {
            model = intent.getSerializableExtra("model") as ItemEntity?
        }
        model?.let {
            mBinding.tvTitle.text = it.title
            mBinding.tvSubTitle.text = it.subtitle

            Glide.with(mBinding.ivImage).load(it.image)
                .placeholder(R.drawable.ic_image)
                .into(mBinding.ivImage)
        }

        mBinding.TextView.setOnClickListener {
            startActivity(Intent(this, ItemListActivity::class.java))
        }

    }
}