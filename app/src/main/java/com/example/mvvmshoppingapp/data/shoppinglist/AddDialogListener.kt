package com.example.mvvmshoppingapp.data.shoppinglist

import com.example.mvvmshoppingapp.data.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}