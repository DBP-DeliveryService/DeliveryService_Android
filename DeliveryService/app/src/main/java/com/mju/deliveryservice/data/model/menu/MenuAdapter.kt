package com.mju.deliveryservice.data.model.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mju.deliveryservice.R


data class Menu(val title: String, val description: String, val price: String, val imageResId: Int)

class MenuAdapter(private val menuList: List<Menu>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menuList[position]
        holder.titleTextView.text = menu.title
        holder.descriptionTextView.text = menu.description
        holder.priceTextView.text = menu.price
        holder.imageView.setImageResource(menu.imageResId)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.menu_image)
        val titleTextView: TextView = itemView.findViewById(R.id.menu_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.menu_description)
        val priceTextView: TextView = itemView.findViewById(R.id.menu_price_small)
    }
}