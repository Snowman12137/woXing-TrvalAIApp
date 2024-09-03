package com.example.kamteamapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kamteamapp.base.prase.InsertMessageItem
import com.example.kamteamapp.base.prase.getMessageItemById
import com.example.kamteamapp.base.prase.parseTravelData
import com.example.kamteamapp.data.Data_my
import com.example.kamteamapp.ui.item.DisplayItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MyUiState(
    var test_data: List<DisplayItem> = ArrayList()
)

class MyViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(MyUiState())

    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    init {

    }

}


//InsertMessageItem(message = data, id = 23)
//val messageItem = getMessageItemById( 2)
//val result = messageItem?.let { parseTravelData(it.message) }
//val a = result?.main
//val b = result?.weather
//val c = result?.trval