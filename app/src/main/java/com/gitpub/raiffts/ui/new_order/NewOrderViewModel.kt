package com.gitpub.raiffts.ui.new_order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewOrderViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is New Order Fragment"
    }
    val text: LiveData<String> = _text
}