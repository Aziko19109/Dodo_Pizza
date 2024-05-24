package com.ziko.dodopizza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziko.dodopizza.databinding.ItemRvBinding
import com.ziko.dodopizza.model.Pizza

class NewsAdapter(val list: ArrayList<Pizza>) : RecyclerView.Adapter<NewsAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(pizza: Pizza, position: Int) {
            itemRvBinding.imagePizza.setImageResource(pizza.image)
            itemRvBinding.namePizza.text = pizza.name
            itemRvBinding.pricePizza.text = pizza.price

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)

    }

}