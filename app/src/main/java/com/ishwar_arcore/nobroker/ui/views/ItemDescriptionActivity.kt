package com.ishwar_arcore.nobroker.ui.views

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.ishwar_arcore.nobroker.databinding.ActivityItemDescriptionBinding
import com.ishwar_arcore.nobroker.utils.BITMAP_IMG
import com.ishwar_arcore.nobroker.utils.SUBTITLE
import com.ishwar_arcore.nobroker.utils.TITLE

class ItemDescriptionActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityItemDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityItemDescriptionBinding.inflate(layoutInflater)
        setContentView(mBinding.root!!)
        initViews()
    }

    private fun initViews() {
        if (intent != null && intent.extras != null) {
            val title: String? = intent.getStringExtra(TITLE)
            val subtitle: String? = intent.getStringExtra(SUBTITLE)
            val byteArray = intent.getByteArrayExtra(BITMAP_IMG)
            /**
             * Converting the received byte array into bitmap
             * **/
            val image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)

            mBinding.tvTitle.text = title
            mBinding.tvSubTitle.text = subtitle
            Glide.with(mBinding.ivImage).load(image).into(mBinding.ivImage)

            mBinding.TextView.setOnClickListener {
                startActivity(Intent(this, ItemListActivity::class.java))
                finish()
            }
        }
    }
}