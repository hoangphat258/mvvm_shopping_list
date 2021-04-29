package com.example.mvvmshoppingapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @ColumnInfo(name = "item_name")
    var name: String,
    @ColumnInfo(name = "item_amount")
    var amount: Int,
) {
    //Room will automatically increase id
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}