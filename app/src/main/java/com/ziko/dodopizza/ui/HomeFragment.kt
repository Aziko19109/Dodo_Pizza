package com.ziko.dodopizza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ziko.dodopizza.R
import com.ziko.dodopizza.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.pizzaButton.setOnClickListener {
            findNavController().navigate(R.id.pizzaFragment)
        }
        binding.drinkButton.setOnClickListener {
            findNavController().navigate(R.id.drinksFragment)
        }
        binding.snacksButton.setOnClickListener {
            findNavController().navigate(R.id.snacksFragment)
        }
        binding.dessertsButton.setOnClickListener {
            findNavController().navigate(R.id.dessertsFragment)
        }
        binding.sauceButton.setOnClickListener {
            findNavController().navigate(R.id.sauceFragment)
        }
        binding.discountButton.setOnClickListener {
            findNavController().navigate(R.id.discountFragment)
        }

        return binding.root
    }


}