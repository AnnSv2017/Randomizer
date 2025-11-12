package com.asvn.randomizer

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewListViewModel(val listId: Long, val dao: ListDao) : ViewModel() {
    val items: LiveData<List<Item>> = dao.getItemsByListId(listId)
    val listName: LiveData<String> = dao.getListNameById(listId)

    fun updateListName(newName: String) {
        viewModelScope.launch {
            dao.updateListName(listId, newName)
        }
    }

    fun updateItemName(item: Item) {
        viewModelScope.launch {
            dao.updateItemName(item.id, item.name)
        }
    }

}