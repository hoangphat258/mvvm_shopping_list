package com.example.mvvmshoppingapp.data.shoppinglist

import android.util.Log
import androidx.lifecycle.*
import com.example.mvvmshoppingapp.data.entities.ShoppingItem
import com.example.mvvmshoppingapp.data.repositories.ShoppingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingViewModel @Inject constructor(
    private val repository: ShoppingRepository
): ViewModel() {

    init {
        getAllShoppingItems()
    }

    private val _shoppingItems = MutableLiveData<List<ShoppingItem>>()
    val shoppingItems: LiveData<List<ShoppingItem>> = _shoppingItems

    fun upsert(item: ShoppingItem) = viewModelScope.launch {
        repository.insert(item)
    }

    fun delete(item: ShoppingItem) = viewModelScope.launch {
        repository.delete(item)
    }

    private fun getAllShoppingItems() = viewModelScope.launch(Dispatchers.IO) {
        repository.getAllShoppingItems().collect {
            _shoppingItems.postValue(it)
        }
    }

}