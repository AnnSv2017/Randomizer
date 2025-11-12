package com.asvn.randomizer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainListViewModelFactory(private val dao: ListDao)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainListViewModel::class.java)) {
            return modelClass.cast(MainListViewModel(dao))!!
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}