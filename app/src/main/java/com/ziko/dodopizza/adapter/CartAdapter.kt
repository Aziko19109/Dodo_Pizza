package com.ziko.dodopizza.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ziko.dodopizza.databinding.CartAddItemBinding
import com.ziko.dodopizza.model.CartItem

class CartAdapter(val list: ArrayList<CartItem>,    private val updateCartItem: (CartItem) -> Unit, private val deleteFromCart: (CartItem) -> Unit,) : RecyclerView.Adapter<CartAdapter.Vh>() {

    inner class Vh(var cartItemBinding: CartAddItemBinding) : RecyclerView.ViewHolder(cartItemBinding.root) {

        fun onBind(cartItem: CartItem) {
            Picasso.get()
                .load(cartItem.foodImage)
                .into(cartItemBinding.homeFoodImage)
            cartItemBinding.cartBtn.text = cartItem.foodName
            cartItemBinding.priceBtn.text = cartItem.foodPrice
            cartItemBinding.countBnt.text = cartItem.quantity.toString()

            cartItemBinding.deleteBtn.setOnClickListener {
                deleteFromCart(cartItem)
            }

            cartItemBinding.plusBtn.setOnClickListener {
                cartItem.quantity++
                cartItemBinding.countBnt.text = cartItem.quantity.toString()
                updateCartItem(cartItem)
            }

            cartItemBinding.minusBtn.setOnClickListener {
                if (cartItem.quantity > 1) {
                    cartItem.quantity--
                    cartItemBinding.countBnt.text = cartItem.quantity.toString()
                    updateCartItem(cartItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(CartAddItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    fun removeItem(cartItem: CartItem) {
        val position = list.indexOf(cartItem)
        if (position != -1) {
            list.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
