package com.example.kamteamapp.ui.MainScreen.MyScreen

import com.example.kamteamapp.base.basevm.BaseViewModel
import com.example.kamteamapp.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import com.example.kamteamapp.Utils.MyHelper
import kotlinx.coroutines.launch


data class MyUiState(
    var userBean: User = User(),
) {
    fun isLogin(): Boolean {
        return userBean.id.isNotBlank()
    }
}

class MyViewModel : BaseViewModel(){

    private val _uiState = MutableStateFlow(MyUiState())

    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    fun getUser() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(userBean = MyHelper.getUser())
            }
        }
    }
}