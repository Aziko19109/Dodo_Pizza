package com.ziko.dodopizza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ziko.dodopizza.databinding.ItemRvBinding
import com.ziko.dodopizza.model.Pizza
import com.ziko.dodopizza.model.PopularModel

class NewsAdapter(val list: ArrayList<Pizza>,  private val addToCart: (Pizza) -> Unit) : RecyclerView.Adapter<NewsAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(pizza: Pizza, position: Int) {
            Picasso.get()
                .load(pizza.image)
                .into(itemRvBinding.imagePizza)
            itemRvBinding.namePizza.text = pizza.name
            itemRvBinding.pricePizza.text = pizza.price.toString()

            itemRvBinding.addBtn.setOnClickListener {
                addToCart(pizza)
            }

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