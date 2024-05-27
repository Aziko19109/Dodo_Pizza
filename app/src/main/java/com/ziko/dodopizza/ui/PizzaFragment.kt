package com.ziko.dodopizza.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.ziko.dodopizza.adapter.NewsAdapter
import com.ziko.dodopizza.databinding.FragmentPizzaBinding
import com.ziko.dodopizza.model.Pizza

class PizzaFragment : Fragment() {

    private lateinit var binding: FragmentPizzaBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var list: ArrayList<Pizza>
    private var lastId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPizzaBinding.inflate(layoutInflater)
        firebaseDatabase = FirebaseDatabase.getInstance()
        list = ArrayList()
        takeLastId()
        newsAdapter = NewsAdapter(list)
        binding.rv.adapter = newsAdapter

        binding.backFromPizzaFragment.setOnClickListener() {
            findNavController().popBackStack()
        }

        return binding.root
    }

    fun takeLastId() {

        reference = firebaseDatabase.getReference("pizza")

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
            reference = firebaseDatabase.getReference("pizza/$i")
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