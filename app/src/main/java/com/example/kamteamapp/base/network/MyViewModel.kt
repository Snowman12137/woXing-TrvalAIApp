package com.example.kamteamapp.base.network

import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    init {
        viewModelScope.launch {
            val hotKeyList = async { getHotKeyList() }
        }
    }
}


//@Composable
//fun getmessage(){
//
//    viewModelScope.launch {
//
//
//
//
//    }
//
//
//}