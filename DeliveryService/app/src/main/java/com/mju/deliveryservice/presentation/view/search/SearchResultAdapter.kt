package com.mju.deliveryservice.presentation.view.search

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mju.deliveryservice.R
import com.mju.deliveryservice.data.utils.CustomLogger
import com.mju.deliveryservice.databinding.ItemCategoryBinding
import com.mju.deliveryservice.databinding.ItemSearchStoreBinding
import com.mju.deliveryservice.domain.model.category.Category
import com.mju.deliveryservice.domain.model.category.StoresByCategory

class SearchResultAdapter(private var items: List<StoresByCategory>) :
    RecyclerView.Adapter<SearchResultAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemSearchStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CategoryViewHolder(private val binding: ItemSearchStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StoresByCategory) {
            with(binding){
                tvStoreName.text = item.storeName
                tvOperateStatus.text = item.detailOpreateStatus
                tvRating.text = item.rating.toString()
                tvDetailCategory.text = item.detailCategoryName
                tvDeliveryTime.text = itemView.context.getString(R.string.store_item_time, item.minDeliveryTime.toString())
                tvMinPrice.text = itemView.context.getString(R.string.store_item_price, item.minPrice.toString())
                tvDeliveryTip.text = itemView.context.getString(R.string.store_item_tip, item.deliveryTip.toString())
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<StoresByCategory>){
        items = newList
        notifyDataSetChanged()
    }
}