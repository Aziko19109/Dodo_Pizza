package com.ziko.dodopizza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.FirebaseDatabase
import com.ziko.dodopizza.R
import com.ziko.dodopizza.databinding.FragmentOrderBinding
import com.ziko.dodopizza.db.MyPrice
import com.ziko.dodopizza.db.SessionManager

class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding

    private lateinit var sessionManager: SessionManager
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(layoutInflater)
        sessionManager = SessionManager(requireContext())
        firebaseDatabase = FirebaseDatabase.getInstance()

        binding.backFromPizzaFragment.setOnClickListener(){
            findNavController().popBackStack()
        }

        binding.apply {
            nameText.text = sessionManager.name
            phoneText.text = "+998 ${sessionManager.phone}"
            priceText.text = MyPrice.price
        }

        binding.applyBtn.setOnClickListener {
            Toast.makeText(context, "Ваш заказ принят!!!", Toast.LENGTH_SHORT).show()
            clearCart()
        }

        return binding.root
    }

    private fun clearCart() {
        val cartRef = firebaseDatabase.getReference("cart/user1")
        cartRef.removeValue()
            .addOnSuccessListener {
                findNavController().navigate(R.id.homeFragment)
            }
            .addOnFailureListener {
                // Обработка ошибок очистки корзины
            }
    }
}


