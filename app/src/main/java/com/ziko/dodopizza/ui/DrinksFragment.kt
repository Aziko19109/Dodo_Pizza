package com.ziko.dodopizza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.ziko.dodopizza.R
import com.ziko.dodopizza.adapter.NewsAdapter
import com.ziko.dodopizza.databinding.FragmentDrinksBinding
import com.ziko.dodopizza.model.CartItem
import com.ziko.dodopizza.model.Pizza


class DrinksFragment : Fragment() {

        private lateinit var binding: FragmentDrinksBinding
        private lateinit var firebaseDatabase: FirebaseDatabase
        private lateinit var reference: DatabaseReference
        private lateinit var newsAdapter: NewsAdapter
        private lateinit var list: ArrayList<Pizza>
        private var lastId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDrinksBinding.inflate(layoutInflater)
        firebaseDatabase = FirebaseDatabase.getInstance()
        list = ArrayList()
        takeLastId()
        newsAdapter = NewsAdapter(list) { pizza ->
            addToCart(pizza)
        }
        binding.rv.adapter = newsAdapter

        binding.backFromPizzaFragment.setOnClickListener(){
            findNavController().popBackStack()
        }

        return binding.root
    }

    private fun addToCart(pizza: Pizza) {
        val cartRef = firebaseDatabase.getReference("cart/user1")
        val cartItem = CartItem(pizza.image!!, pizza.name!!, pizza.price.toString(), 1)
        cartRef.child(pizza.name!!).setValue(cartItem)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Добавлено в корзину", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                // Обработка ошибок добавления
            }
    }

    fun takeLastId() {

        reference = firebaseDatabase.getReference("Drinks")

        reference.orderByKey().limitToLast(1)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val lastKey = snapshot.children.last().key
                        lastKey?.let {
                            lastId = lastKey.toInt()
                            loadData()

                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Обработка ошибок загрузки
                }
            })
    }

    fun loadData() {

        for (i in 1..lastId) {
            reference = firebaseDatabase.getReference("Drinks/$i")
            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val value = dataSnapshot.getValue<Pizza>()
                    value?.let {
                        if (!list.contains(it)) {
                            list.add(it)
                            newsAdapter.notifyDataSetChanged()
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Обработка ошибок загрузки
                }
            })
        }
    }



}