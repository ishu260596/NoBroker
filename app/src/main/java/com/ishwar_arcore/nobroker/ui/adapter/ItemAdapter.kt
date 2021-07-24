package com.ishwar_arcore.nobroker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ishwar_arcore.nobroker.R
import com.ishwar_arcore.nobroker.data.model.response.ResponseItem

class ItemAdapter(private val itemList: List<ResponseItem>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = itemList[position]
        holder.setData(model)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val mTvSubTitle: TextView = itemView.findViewById(R.id.tvSubTitle)
        private val mIvImageView: ImageView = itemView.findViewById(R.id.ivImage)

        /**
         * set data method set the data according to the views
         *
         * **/
        fun setData(model: ResponseItem) {
            mTvTitle.text = model.title
            mTvSubTitle.text = model.subTitle
            /**Loading image with Glide**/
            Glide.with(mIvImageView).load(model.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(mIvImageView)
        }
    }
}