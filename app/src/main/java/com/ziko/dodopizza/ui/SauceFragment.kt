package com.ziko.dodopizza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ziko.dodopizza.R
import com.ziko.dodopizza.adapter.NewsAdapter
import com.ziko.dodopizza.databinding.FragmentDrinksBinding
import com.ziko.dodopizza.databinding.FragmentSauceBinding
import com.ziko.dodopizza.model.Pizza


class SauceFragment : Fragment() {

    private lateinit var binding: FragmentSauceBinding
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var list: ArrayList<Pizza>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSauceBinding.inflate(layoutInflater)

        loadData()
        newsAdapter = NewsAdapter(list)
        binding.rv.adapter = newsAdapter

        binding.backFromPizzaFragment.setOnClickListener(){
            findNavController().popBackStack()
        }


        return binding.root
    }

    private fun loadData() {
        list = ArrayList()
        list.add(Pizza(R.drawable.arriva,"Pizza", "25.000"))
        list.add(Pizza(R.drawable.arriva,"Pizza2", "26.000"))
        list.add(Pizza(R.drawable.arriva,"Pizza3", "78.000"))
        list.add(Pizza(R.drawable.arriva,"Pizza4", "55.225"))
        list.add(Pizza(R.drawable.arriva,"Pizza5", "$5.096"))
    }


}