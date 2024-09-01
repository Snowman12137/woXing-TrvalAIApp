package com.example.kamteamapp.base.network

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.network.HttpViewModel
import com.example.kamteamapp.network.MarsPhotosScreen
import com.example.kamteamapp.network.MarsUiState
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class NettoRoom : ComponentActivity() {
    private val httpViewModel: HttpViewModel by viewModels()
    private val messageViewModel: MessageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KamTeamAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val nettoRoomViewModel = NettoRoomViewModelProvider(httpViewModel)
                    NettoRoomScreen(nettoRoomViewModel, messageViewModel)
                }
            }
        }
    }
}

@Composable
fun NettoRoomScreen(nettoRoomViewModel: NettoRoomViewModel, messageViewModel: MessageViewModel) {
    val marsUiState by nettoRoomViewModel.httpViewModel.marsUiState.collectAsState()
    var number by rememberSaveable { mutableStateOf(0) }
    val message = nettoRoomViewModel.getreturninformation(marsUiState)
    val allMessageItems by messageViewModel.allMessageItems.collectAsState(initial = emptyList())
    LazyColumn {
        item { Spacer(modifier = Modifier.height(64.dp)) }
        item {
            Button(
                onClick = {
                    nettoRoomViewModel.getinformation()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "get response")
            }
        }
        item {
            //插入数据
            Button(onClick = {
                val testx = Message_item(number,message)
                messageViewModel.insertMessageItem(testx)
                number = number+1
            }, modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(50.dp)
            )
            {
                Text(text ="插入数据")
            }
        }
        item {
            Button(onClick = {
                messageViewModel.deleteMessageItem()
            },modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(50.dp))
            {
                Text(text ="删除数据")
            }
        }
        item {
            Text(text = "Total items: ${allMessageItems.size}")
        }
        item { Text(text = "===============================") }
        item {
            Text(text = "Message: $message")
        }
    }

}
