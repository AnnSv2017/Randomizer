package com.asvn.randomizer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CreateListViewModelFactory(private val dao: ListDao)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CreateListViewModel::class.java)) {
            return modelClass.cast(CreateListViewModel(dao))!!
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}