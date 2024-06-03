package com.mju.deliveryservice.presentation.view.home

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.mju.deliveryservice.data.utils.CustomLogger
import com.mju.deliveryservice.databinding.ItemUpCategoryBinding
import com.mju.deliveryservice.domain.model.category.Category
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class UpCategoryAdapter(private var items: List<Category>) :
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

        fun bind(item: Category) {
            binding.tvUpCategoryName.text = item.categoryName

            Glide.with(binding.ivUpCategory)
                .load(item.categoryImgUrl)
                .transform(CenterCrop(), RoundedCornersTransformation(32, 0))
                .into(binding.ivUpCategory)

            itemView.setOnClickListener {
                categoryClickListener.onClick(item)
            }
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

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<Category>){
        items = newList
        notifyDataSetChanged()
    }

    interface OnCategoryClickListener{
        fun onClick(categoryItem: Category)
    }

    private lateinit var categoryClickListener: OnCategoryClickListener

    fun setCategoryClickListener(onCategoryClickListener: OnCategoryClickListener){
        this.categoryClickListener = onCategoryClickListener
    }
}