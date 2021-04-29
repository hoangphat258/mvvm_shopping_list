package com.example.mvvmshoppingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmshoppingapp.R
import com.example.mvvmshoppingapp.data.ShoppingDatabase
import com.example.mvvmshoppingapp.data.entities.ShoppingItem
import com.example.mvvmshoppingapp.data.repositories.ShoppingRepository
import com.example.mvvmshoppingapp.data.shoppinglist.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AddDialogListener {

    lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)
        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
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