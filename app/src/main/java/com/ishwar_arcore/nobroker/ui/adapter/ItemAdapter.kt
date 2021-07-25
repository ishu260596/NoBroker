package com.ishwar_arcore.nobroker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ishwar_arcore.nobroker.R
import com.ishwar_arcore.nobroker.data.local.ItemEntity
import java.util.*
import kotlin.collections.ArrayList


class ItemAdapter(
    private val itemList: ArrayList<ItemEntity>,
    private val listener: ItemClickListener
) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(), Filterable {

    var backupList = ArrayList<ItemEntity>()

    init {
        backupList = itemList
    }

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

    /**
     * This method will search the item according to the query entered
     * in the search view in ListItemActivity
     * **/
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(keyword: CharSequence?): FilterResults {
                val filterData = ArrayList<ItemEntity>()
                if (keyword.toString().isEmpty()) filterData.addAll(backupList) else {
                    for (obj in backupList) {
                        if (obj.title.lowercase(Locale.getDefault())
                                .contains(keyword.toString().lowercase(Locale.getDefault()))
                        ) filterData.add(obj)
                    }
                }
                val results = FilterResults()
                results.values = filterData
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemList.clear()
                itemList.addAll(
                    results!!.values as ArrayList<ItemEntity>
                )
                notifyDataSetChanged()
            }

        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val mTvSubTitle: TextView = itemView.findViewById(R.id.tvSubTitle)
        private val mIvImageView: ImageView = itemView.findViewById(R.id.ivImage)
        private val mCardView: CardView = itemView.findViewById(R.id.cardView)

        /**
         * set data method set the data according to the views
         *
         * **/
        fun setData(model: ItemEntity) {
            mTvTitle.text = model.title
            mTvSubTitle.text = model.subtitle
            /**Loading image with Glide**/
            Glide.with(mIvImageView).load(model.image)
                .placeholder(R.drawable.ic_image)
                .into(mIvImageView)

            mCardView.setOnClickListener {
                listener.onItemClick(model)
            }
        }
    }

}