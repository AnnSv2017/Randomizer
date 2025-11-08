package com.asvn.randomizer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateListViewModel : ViewModel() {
    private val _text = MutableLiveData<String>("Abcd")
    val text: LiveData<String>
        get() = _text

//    init {
//        _text.value = "Initial text"
//    }

    fun save() {
        _text.value = "SAVE"
    }
}