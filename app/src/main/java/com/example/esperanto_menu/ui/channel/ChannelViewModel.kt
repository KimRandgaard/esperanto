package com.example.esperanto_menu.ui.channel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChannelViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is channels Fragment"
    }
    val text: LiveData<String> = _text
}