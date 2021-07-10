package com.example.mvvmshoppingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmshoppingapp.R
import com.example.mvvmshoppingapp.data.entities.ShoppingItem
import com.example.mvvmshoppingapp.data.shoppinglist.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AddDialogListener {

    private val viewModel: ShoppingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.shoppingItems.observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
            for (i in it) {
                Log.d("test", "Items' Id: ${i.id}")
            }
        })

        fab.setOnClickListener {
            ShoppingItemDialog(this, this).show()
        }
    }

    override fun onAddButtonClicked(item: ShoppingItem) {
        viewModel.upsert(item)
    }
}