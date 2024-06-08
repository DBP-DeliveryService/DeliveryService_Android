package com.mju.deliveryservice.presentation.view.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mju.deliveryservice.databinding.MenuItemBinding
import com.mju.deliveryservice.domain.model.store.MenuDetail

class MenuAdapter(menuList: List<MenuDetail>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private var menuList: List<MenuDetail> = menuList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menuList[position]
        holder.bind(menu)

    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    inner class MenuViewHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuDetail) {
            Glide.with(binding.root).load(item.menuPictureUrl).into(binding.menuImage)
            binding.menuTitle.text = item.menuName
            binding.menuDescription.text = item.menuContent
            binding.menuPriceSmall.text = "${item.price}원"

            itemView.setOnClickListener {
                menuClickListener.onClick(adapterPosition)
            }
        }
    }

    interface OnMenuClickListener{
        fun onClick(pos: Int)
    }

    private lateinit var menuClickListener: OnMenuClickListener

    fun setMenuClickListener(onMenuClickListener: OnMenuClickListener){
        this.menuClickListener = onMenuClickListener
    }
}