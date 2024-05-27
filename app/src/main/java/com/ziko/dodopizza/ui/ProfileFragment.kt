package com.ziko.dodopizza.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ziko.dodopizza.R
import com.ziko.dodopizza.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        val sharedPreferences = activity?.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences?.getString("USER_NAME", "Default Name")
        val userPhone = sharedPreferences?.getString("USER_PHONE", "Default Phone")

        binding.nameTextView.text = "Привет, $userName"

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
