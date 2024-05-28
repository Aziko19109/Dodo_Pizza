package com.ziko.dodopizza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.ziko.dodopizza.R
import com.ziko.dodopizza.adapter.CartAdapter
import com.ziko.dodopizza.databinding.FragmentCartBinding
import com.ziko.dodopizza.db.MyPrice
import com.ziko.dodopizza.model.CartItem
import com.ziko.dodopizza.model.Pizza
import com.ziko.dodopizza.model.PopularModel


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var list: ArrayList<CartItem>
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater)

        firebaseDatabase = FirebaseDatabase.getInstance()
        list = ArrayList()
        adapter = CartAdapter(list, { cartItem ->
            updateCartItem(cartItem)
        }, { cartItem ->
            deleteFromCart(cartItem)
        })
        binding.cartRv.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRv.adapter = adapter
        loadCartData()

        binding.liner.setOnClickListener {
            findNavController().navigate(R.id.orderFragment)
        }

        return binding.root
    }

    private fun loadCartData() {
        reference = firebaseDatabase.getReference("cart/user1")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                list.clear()
                for (cartSnapshot in snapshot.children) {
                    val cartItem = cartSnapshot.getValue<CartItem>()
                    cartItem?.let {
                        list.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
                calculateTotalPrice()
                if (binding.totalPrice.text == "0,00"){
                    binding.totalPrice.visibility = View.GONE
                }else{
                    binding.totalPrice.visibility = View.VISIBLE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Обработка ошибок загрузки
            }
        })
    }

    private fun updateCartItem(cartItem: CartItem) {
        val cartRef = firebaseDatabase.getReference("cart/user1")
        cartRef.child(cartItem.foodName).setValue(cartItem)
            .addOnSuccessListener {
                calculateTotalPrice()
                if (binding.totalPrice.text == "0,00"){
                    binding.totalPrice.visibility = View.GONE
                }else{
                    binding.totalPrice.visibility = View.VISIBLE

                }
            }
            .addOnFailureListener {
                // Обработка ошибок обновления
            }
    }
    private fun deleteFromCart(cartItem: CartItem) {
        val cartRef = firebaseDatabase.getReference("cart/user1")
        cartRef.child(cartItem.foodName).removeValue()
            .addOnSuccessListener {
                adapter.removeItem(cartItem)
                calculateTotalPrice()
                if (binding.totalPrice.text == "0,00"){
                    binding.totalPrice.visibility = View.GONE
                }else{
                    binding.totalPrice.visibility = View.VISIBLE

                }

            }
            .addOnFailureListener {
                // Обработка ошибок удаления
            }
    }

    private fun calculateTotalPrice() {

        var totalPrice = 0.0
        for (cartItem in list) {
            totalPrice += (cartItem.foodPrice.toInt() * cartItem.quantity)
        }

        binding.totalPrice.text = "${formatNumber(totalPrice)}"
        MyPrice.price = formatNumber(totalPrice)
    }

    fun formatNumber(number: Double): String {
        val formattedNumber = if (number >= 100000) {
            // Форматирование для шестизначных чисел: добавление запятой после третьей цифры
            String.format("%,.0f", number)
        } else if (number >= 10000) {
            // Форматирование для пятизначных чисел: добавление пробела после второй цифры
            String.format("%,d", number.toInt()).replace(',', ' ')
        } else {
            // Стандартное форматирование
            "%.2f".format(number)
        }
        return formattedNumber
    }


}