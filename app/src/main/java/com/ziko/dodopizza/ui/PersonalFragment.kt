package com.ziko.dodopizza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ziko.dodopizza.R
import com.ziko.dodopizza.databinding.FragmentPersonalBinding

class PersonalFragment : Fragment() {

    private lateinit var binding: FragmentPersonalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalBinding.inflate(layoutInflater)



        return binding.root

    }


}