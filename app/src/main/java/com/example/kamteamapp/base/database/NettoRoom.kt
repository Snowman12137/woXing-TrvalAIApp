package com.example.kamteamapp.base.database

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
import com.example.kamteamapp.base.network.NetViewModel
import com.example.kamteamapp.base.network.NetViewModelProvider
import com.example.kamteamapp.base.network.getresponse
import com.example.kamteamapp.network.HttpViewModel
import com.example.kamteamapp.ui.theme.KamTeamAppTheme

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
                    val nettoRoomViewModel = NetViewModelProvider(httpViewModel,)
                    NettoRoomScreen(nettoRoomViewModel, messageViewModel)
                }
            }
        }
    }
}
@Composable
fun NettoRoomScreen(netViewModel: NetViewModel, messageViewModel: MessageViewModel) {
    val marsUiState by netViewModel.httpViewModel.marsUiState.collectAsState()
    val all = findallMessageItem(messageViewModel = messageViewModel)
    val item  = findMessageItem(id = 1, messageViewModel = messageViewModel)

    var number =1
    var messagesend = "我们想去西安旅游，我们一共四个人，想玩三天，帮我规划下旅游行程"
    LazyColumn {
        item {
            Spacer(modifier = Modifier.height(64.dp))
            Button(onClick = {
                NettoRoomInterface(netViewModel = netViewModel, statestate = marsUiState, messagesend = messagesend, messageViewModel = messageViewModel, id = number)
            },modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(50.dp)
            )
            {
                Text(text ="获取数据并且插入数据库")
            }
        }
        item {
            Button(onClick = {
                deleteMessageItem(messageViewModel = messageViewModel)
            },modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(50.dp))
            {
                Text(text ="删除数据")
            }
        }
        item {
            Text(text = "Total items: ${all.size}")
            Text(text = "All items: ${all}")
            Text(text = "==================================")
            Text(text = "Item: ${item}")
            Text(text = "==================================")
        }

    }
}



