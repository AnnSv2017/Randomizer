package com.asvn.randomizer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewListViewModelFactory(private val listId: Long, private val dao: ListDao)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ViewListViewModel::class.java)) {
            return modelClass.cast(ViewListViewModel(listId, dao))!!
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}