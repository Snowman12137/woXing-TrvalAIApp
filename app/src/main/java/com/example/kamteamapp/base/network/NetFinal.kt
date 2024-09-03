package com.example.kamteamapp.base.network

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.kamteamapp.network.HttpViewModel
import com.example.kamteamapp.network.MarsUiState
import com.example.kamteamapp.ui.theme.KamTeamAppTheme

class NetFinal : ComponentActivity() {
    private val httpViewModel: HttpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KamTeamAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val nettoRoomViewModel = NetViewModelProvider(httpViewModel)
                    getresponse(netViewModel = nettoRoomViewModel, messagesend = "请求回应")

                }
            }
        }
    }
}

@Composable
fun getresponse(netViewModel: NetViewModel,messagesend: String) {

    val marsUiState by netViewModel.httpViewModel.marsUiState.collectAsState()
    val response = getresponse(netViewModel = netViewModel, state = marsUiState,messagesend=messagesend)

    LazyColumn {
        item {
            Text(text = response.toString())
        }
    }
}