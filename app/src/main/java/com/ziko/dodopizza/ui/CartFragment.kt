package com.ziko.dodopizza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziko.dodopizza.R
import com.ziko.dodopizza.adapter.CartAdapter
import com.ziko.dodopizza.databinding.FragmentCartBinding
import com.ziko.dodopizza.model.PopularModel


class CartFragment : Fragment() {


    private lateinit var binding: FragmentCartBinding

    private lateinit var list: ArrayList<PopularModel>
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater)

        list = ArrayList()
        for (i in 0..5) {
            list.add(PopularModel(R.drawable.img_for_pizzabutton, "Sandwich", "$7"))
            list.add(PopularModel(R.drawable.img_for_pizzabutton, "Momo", "$9"))
            list.add(PopularModel(R.drawable.img_for_pizzabutton, "Burger", "$5"))
        }

        adapter = CartAdapter(list)
        binding.cartRv.layoutManager  = LinearLayoutManager(requireContext())
        binding.cartRv.adapter = adapter

        return binding.root
    }


}