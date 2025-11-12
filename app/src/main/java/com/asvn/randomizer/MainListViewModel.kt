package com.asvn.randomizer

import androidx.lifecycle.ViewModel

class MainListViewModel(val dao: ListDao) : ViewModel() {
    val listElements = dao.getAllLists()
}