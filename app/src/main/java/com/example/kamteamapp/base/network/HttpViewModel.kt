package com.example.kamteamapp.network
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kamteamapp.ui.chat.Message
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.IOException

class HttpViewModel : ViewModel() {
    private val _marsUiState = MutableStateFlow<MarsUiState>(MarsUiState.Loading)
    val marsUiState: StateFlow<MarsUiState> = _marsUiState

    fun getinformation(message: String) {
        viewModelScope.launch {
            _marsUiState.value = try {
                val listResult = MarsApi.retrofitService.getinputinformation(
//                    name = name,
//                    age = age
                    //code = "utf-8",
                    //q = "鞋子",
                    message = message
                )
                MarsUiState.Success(listResult)
            } catch (e: IOException) {
                MarsUiState.Error
            }
        }
    }
}

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}