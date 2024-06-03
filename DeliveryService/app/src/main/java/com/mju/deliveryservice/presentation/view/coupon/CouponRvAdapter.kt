package com.mju.deliveryservice.presentation.view.coupon

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mju.deliveryservice.R
import com.mju.deliveryservice.databinding.ItemCouponBinding
import com.mju.deliveryservice.domain.model.coupon.CouponEntity

class CouponRvAdapter(private var items: List<CouponEntity>) :
    RecyclerView.Adapter<CouponRvAdapter.CategoryViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCouponBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    inner class CategoryViewHolder(private val binding: ItemCouponBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CouponEntity, position: Int) {
            with(binding) {
                tbCoupon.apply {
                    text = null
                    textOn = null
                    textOff = null
                    isChecked = position == selectedPosition

                    setOnClickListener {
                        couponClickListener.onClick(item)

                        if (selectedPosition != position) {
                            notifyItemChanged(selectedPosition)
                            selectedPosition = position
                            notifyItemChanged(selectedPosition)
                        }
                    }
                }

                tvPrice.text = "${item.discountAmount}원"
                tvCouponName.text = item.couponName
                tvExpirationDate.text = "3일 후 만료 ${item.expiredDate}"
                tvMinPrice.text = itemView.context.getString(R.string.store_item_price, item.minPrice.toString())
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<CouponEntity>) {
        items = newList
        notifyDataSetChanged()
    }

    interface OnCouponClickListener{
        fun onClick(item: CouponEntity)
    }

    private lateinit var couponClickListener: OnCouponClickListener

    fun setCouponClickListener(onCouponClickListener: OnCouponClickListener){
        this.couponClickListener = onCouponClickListener
    }
}
