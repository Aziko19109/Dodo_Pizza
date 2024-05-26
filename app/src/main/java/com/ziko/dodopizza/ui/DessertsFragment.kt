package com.ziko.dodopizza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ziko.dodopizza.R
import com.ziko.dodopizza.adapter.NewsAdapter
import com.ziko.dodopizza.databinding.FragmentDessertsBinding
import com.ziko.dodopizza.model.Pizza


class DessertsFragment : Fragment() {

    private lateinit var binding: FragmentDessertsBinding
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var list: ArrayList<Pizza>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDessertsBinding.inflate(layoutInflater)

        newsAdapter = NewsAdapter(list)
        binding.rv.adapter = newsAdapter

        binding.backFromPizzaFragment.setOnClickListener(){
            findNavController().popBackStack()
        }


        return binding.root
    }





}