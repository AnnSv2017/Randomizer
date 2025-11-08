package com.asvn.randomizer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CreateListViewModel(val dao: ItemDao) : ViewModel() {
    var newItemName = ""
    val items = dao.getAll()

    fun addItem() {
        viewModelScope.launch {
            val item = Item()
            item.name = newItemName
            dao.insert(item)
        }
    }
}