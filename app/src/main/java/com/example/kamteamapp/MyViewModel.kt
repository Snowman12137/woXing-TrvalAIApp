package com.example.kamteamapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kamteamapp.Utils.TransPartToDisplay
import com.example.kamteamapp.base.database.MessageViewModel
import com.example.kamteamapp.base.databasenew.WanHelper
import com.example.kamteamapp.base.prase.InsertMessageItem
import com.example.kamteamapp.base.prase.getMessageItemById
import com.example.kamteamapp.base.prase.parseTravelData
import com.example.kamteamapp.data.Data_my
import com.example.kamteamapp.ui.chat.Message
import com.example.kamteamapp.ui.item.DisplayItem
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.activity.viewModels
data class MyUiState(
    var test_data: List<DisplayItem> = ArrayList(),
    var updateTime: Long = 0
)

@RequiresApi(Build.VERSION_CODES.O)
class MyViewModel: ViewModel(){

    private val _uiState = MutableStateFlow(MyUiState())

    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {

            //async { WanHelper.insertmessage(22, Data_my) }
//            val test_data = async{WanHelper.getmessage(1)}
//            _uiState.update { state ->
//                test_data.await().let {
//                    val temp = TransPartToDisplay()
//
//
//                    state.test_data = temp.getDisPlay(temp.getString(it!!.value))
//                }
//                state.copy(updateTime = System.nanoTime())
//            }
        }
    }

    fun add(){
        viewModelScope.launch{
            WanHelper.insertmessage(22, Data_my)
        }
    }

    fun find(){
        val test_datas = WanHelper.getmessage(22)
        _uiState.update {
            val temp = TransPartToDisplay()
            it.copy(test_data = temp.getDisPlay(temp.getString(test_datas)))
        }
    }

}


//InsertMessageItem(message = data, id = 23)
//val messageItem = getMessageItemById( 2)
//val result = messageItem?.let { parseTravelData(it.message) }
//val a = result?.main
//val b = result?.weather
//val c = result?.trval