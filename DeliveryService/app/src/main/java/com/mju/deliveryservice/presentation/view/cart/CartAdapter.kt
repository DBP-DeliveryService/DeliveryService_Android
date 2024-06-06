package com.mju.deliveryservice.presentation.view.cart


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mju.deliveryservice.databinding.ItemCartBinding
import com.mju.deliveryservice.domain.model.CartItem

class CartAdapter(
    private val onQuantityChange: (Int, Int) -> Unit,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var items: List<CartItem> = listOf()

    // ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    // ViewHolder 바인딩
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    // 데이터 설정
    fun setData(newList: List<CartItem>) {
        items = newList
        notifyDataSetChanged()
    }

    inner class CartViewHolder(private val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {

        // 각 항목 데이터 바인딩
        fun bind(item: CartItem) {
            binding.tvMenuName.text = item.menuName
            binding.tvMenuContent.text = item.menuContent
            binding.tvPrice.text = "${item.price}원"
            binding.tvQuantity.text = item.quantity.toString()

            // 삭제 버튼 클릭 시
            binding.ibDelete.setOnClickListener {
                onDelete(item.menuId)
            }

            // 수량 변경 시
            binding.btnDecrease.setOnClickListener {
                val newQuantity = (binding.tvQuantity.text.toString().toInt() - 1).coerceAtLeast(1)
                binding.tvQuantity.text = newQuantity.toString()
                onQuantityChange(item.menuId, newQuantity)
            }
            binding.btnIncrease.setOnClickListener {
                val newQuantity = binding.tvQuantity.text.toString().toInt() + 1
                binding.tvQuantity.text = newQuantity.toString()
                onQuantityChange(item.menuId, newQuantity)
            }
        }
    }
}
