package com.ziko.dodopizza.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ziko.dodopizza.R
import com.ziko.dodopizza.databinding.FragmentProfileBinding
import com.ziko.dodopizza.db.SessionManager


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var sessionManager: SessionManager

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        sessionManager = SessionManager(requireContext())

        binding.nameTextView.text = "Привет, ${sessionManager.name}"
        binding.nameEditText.setText(sessionManager.name)
        binding.phoneNumberEditText.setText(sessionManager.phone)

        binding.nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Не используется
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sessionManager.name = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                // Не используется
            }
        })

        binding.phoneNumberEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Не используется
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sessionManager.phone = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                // Не используется
            }
        })


        return binding.root
    }

}
