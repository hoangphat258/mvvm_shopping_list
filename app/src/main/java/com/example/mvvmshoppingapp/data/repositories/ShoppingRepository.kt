package com.example.mvvmshoppingapp.data.repositories

import com.example.mvvmshoppingapp.data.ShoppingDatabase
import com.example.mvvmshoppingapp.data.entities.ShoppingItem

class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun insert(item: ShoppingItem) = db.getShoppingDao().insert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}