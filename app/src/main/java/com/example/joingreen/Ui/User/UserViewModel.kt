package com.example.joingreen.Ui.User

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is USER Fragment"
    }
    val text: LiveData<String> = _text
}