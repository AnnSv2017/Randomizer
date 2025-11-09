package com.asvn.randomizer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CreateListViewModel(val dao: ListDao) : ViewModel() {
    val newListName = MutableLiveData("")
    val newItemName = MutableLiveData("")
    val items = MutableLiveData<List<Item>>(emptyList())

    private val _save = MutableLiveData(false)
    val save: LiveData<Boolean> = _save

    fun addItem() {
        val name = newItemName.value ?: ""
        if (name.isNotBlank()) {
            val newItem = Item(name = name)
            val updatedList = items.value.orEmpty() + newItem
            items.value = updatedList
            newItemName.value = ""
        }
    }

    fun saveList() {
        viewModelScope.launch {
            val listEntity = ListEntity(name = newListName.value ?: "")
            val currentItems = items.value.orEmpty()
            dao.insertListWithItems(listEntity, currentItems)
            _save.value = true
        }
    }

    fun saveCompleted() {
        _save.value = false
    }
}