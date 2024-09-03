package com.example.kamteamapp.base.network

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.example.kamteamapp.network.HttpViewModel
import com.example.kamteamapp.network.MarsUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetViewModel(
    val httpViewModel: HttpViewModel
) : ViewModel() {
    // ViewModel implementation
    fun sendmessage(message: String) {
        httpViewModel.sendquestion(message)
    }

    fun getresponse(state: MarsUiState): Flow<String> = flow {
        when (state) {
            is MarsUiState.Success -> {
                emit((state as MarsUiState.Success).photos)
            }
            is MarsUiState.Error -> {
                emit("Error")
            }
            is MarsUiState.Loading -> {
                emit("Loading")
            }
        }
    }
}
    class NetViewModelFactory(
        private val httpViewModel: HttpViewModel,
        private var marsUiState: MarsUiState
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NetViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return NetViewModel(httpViewModel) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    @Composable
    fun NetViewModelProvider(httpViewModel: HttpViewModel): NetViewModel {
        val marsUiState by httpViewModel.marsUiState.collectAsState()
        return ViewModelProvider(
            LocalViewModelStoreOwner.current!!,
            NetViewModelFactory(httpViewModel, marsUiState)
        ).get(NetViewModel::class.java)
    }