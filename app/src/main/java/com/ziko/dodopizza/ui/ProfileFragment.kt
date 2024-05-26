package com.ziko.dodopizza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ziko.dodopizza.R
import com.ziko.dodopizza.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        binding.apply {

            historyLiner.setOnClickListener {

            }

            lichnieLiner.setOnClickListener {

            }

            aboutLiner.setOnClickListener {

            }

        }

        return binding.root
    }

}
