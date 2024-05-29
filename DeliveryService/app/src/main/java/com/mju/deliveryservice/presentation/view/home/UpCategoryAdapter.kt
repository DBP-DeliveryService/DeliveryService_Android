package com.mju.deliveryservice.presentation.view.home

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mju.deliveryservice.databinding.ItemUpCategoryBinding
import com.mju.deliveryservice.domain.model.UpCategoryItem

class UpCategoryAdapter(private val items: List<UpCategoryItem>) :
    RecyclerView.Adapter<UpCategoryAdapter.UpCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpCategoryViewHolder {
        val binding = ItemUpCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpCategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class UpCategoryViewHolder(private val binding: ItemUpCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UpCategoryItem) {
            binding.tvUpCategoryName.text = item.title

            Glide.with(binding.ivUpCategory)
                .load(item.imageRes)
                .transform(RoundedCorners(16*4))
                .into(binding.ivUpCategory)
        }
    }

    inner class HorizontalSpaceItemDecoration(private val horizontalSpaceWidth: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.right = horizontalSpaceWidth
            if (parent.getChildAdapterPosition(view) != 0) {
                outRect.left = horizontalSpaceWidth
            }
        }
    }
}