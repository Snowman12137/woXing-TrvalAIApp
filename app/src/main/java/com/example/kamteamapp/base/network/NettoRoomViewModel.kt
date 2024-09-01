package com.example.kamteamapp.base.network

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.kamteamapp.network.HttpViewModel
import com.example.kamteamapp.network.MarsUiState

class NettoRoomViewModel(
    val marsUiState: MarsUiState,
    val httpViewModel: HttpViewModel
) : ViewModel() {
    // ViewModel implementation
    var message = "我们想去西安旅游，我们一共四个人，想玩三天，帮我规划下旅游行程"
    fun getinformation() {
        httpViewModel.getinformation(message)
    }

    fun getreturninformation(state:MarsUiState): String {
        return when (state) {
            is MarsUiState.Success -> {
                (state as MarsUiState.Success).photos
            }
            is MarsUiState.Error -> {
                "Error"
            }
            is MarsUiState.Loading -> {
                "Loading"
            }
        }
    }

}

class NettoRoomViewModelFactory(
    private val httpViewModel: HttpViewModel,
    private val marsUiState: MarsUiState
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NettoRoomViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NettoRoomViewModel(marsUiState, httpViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun NettoRoomViewModelProvider(httpViewModel: HttpViewModel): NettoRoomViewModel {
    val marsUiState by httpViewModel.marsUiState.collectAsState()
    return ViewModelProvider(
        LocalViewModelStoreOwner.current!!,
        NettoRoomViewModelFactory(httpViewModel, marsUiState)
    ).get(NettoRoomViewModel::class.java)
}