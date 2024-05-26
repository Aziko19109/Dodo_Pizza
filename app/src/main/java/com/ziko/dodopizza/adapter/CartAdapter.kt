package com.ziko.dodopizza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ziko.dodopizza.databinding.CartAddItemBinding
import com.ziko.dodopizza.model.PopularModel


class CartAdapter(val list: ArrayList<PopularModel>) : RecyclerView.Adapter<CartAdapter.Vh>() {

    inner class Vh(var cartAddItemBinding: CartAddItemBinding) : RecyclerView.ViewHolder(cartAddItemBinding.root) {

        fun onBind(popularModel: PopularModel, position: Int) {
            cartAddItemBinding.homeFoodImage.setImageResource(popularModel.foodImage!!)
            cartAddItemBinding.cartBtn.text = popularModel.foodName
            cartAddItemBinding.countBnt.text = popularModel.foodCount.toString()
            cartAddItemBinding.priceBtn.text = popularModel.foodPrice
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(CartAddItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)

    }

}