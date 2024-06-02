package com.mju.deliveryservice.presentation.view.home

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.mju.deliveryservice.data.utils.CustomLogger
import com.mju.deliveryservice.databinding.ItemCategoryBinding
import com.mju.deliveryservice.domain.model.category.Category

class CategoryAdapter(private var items: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Category) {
            binding.tvCategoryName.text = item.categoryName

            itemView.setOnClickListener {
                categoryClickListener.onClick(item)
            }
        }
    }

    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount

                if (position < spanCount) {
                    outRect.top = spacing
                }
                outRect.bottom = spacing
            } else {
                outRect.left = column * spacing / spanCount
                outRect.right = spacing - (column + 1) * spacing / spanCount
                if (position >= spanCount) {
                    outRect.top = spacing
                }
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