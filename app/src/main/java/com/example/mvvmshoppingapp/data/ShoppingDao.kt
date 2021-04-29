package com.example.mvvmshoppingapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmshoppingapp.data.entities.ShoppingItem

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}