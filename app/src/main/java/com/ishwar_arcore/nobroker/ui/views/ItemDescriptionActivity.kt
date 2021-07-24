package com.ishwar_arcore.nobroker.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ishwar_arcore.nobroker.R
import com.ishwar_arcore.nobroker.data.model.response.ResponseItem
import com.ishwar_arcore.nobroker.databinding.ActivityItemDescriptionBinding
import com.ishwar_arcore.nobroker.databinding.ActivityItemListBinding

class ItemDescriptionActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityItemDescriptionBinding

    private var model: ResponseItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityItemDescriptionBinding.inflate(layoutInflater)
        setContentView(mBinding.root!!)
        initViews()
    }

    private fun initViews() {
        if (intent != null && intent.extras != null) {
            model = intent.getSerializableExtra("model") as ResponseItem?
        }
        model?.let {
            mBinding.tvTitle.text = it.title
            mBinding.tvSubTitle.text = it.subTitle

            Glide.with(mBinding.ivImage).load(it.image)
                .placeholder(R.drawable.ic_image)
                .into(mBinding.ivImage)
        }

    }
}