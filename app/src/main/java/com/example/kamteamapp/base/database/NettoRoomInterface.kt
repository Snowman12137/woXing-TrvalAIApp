package com.example.kamteamapp.base.database

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.kamteamapp.base.network.NetViewModel
import com.example.kamteamapp.base.network.getresponse
import com.example.kamteamapp.network.MarsUiState
// 修改 NettoRoomInterface 函数以收集 Flow 并等待有效响应
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

//fun NettoRoomInterface(netViewModel: NetViewModel, statestate: MarsUiState, messagesend: String, messageViewModel: MessageViewModel, id: Int) {
//    runBlocking {
//        getresponse(netViewModel = netViewModel, state = statestate, messagesend = messagesend).collect { response ->
//            val iteminsert = Message_item(id, response)
//            insertMessageItem(messageViewModel = messageViewModel, message_item = iteminsert)
//        }
//    }
//}

fun NettoRoomInterface(netViewModel: NetViewModel, statestate: MarsUiState, messagesend:String,messageViewModel: MessageViewModel,id:Int) {

    val response = getresponse(netViewModel = netViewModel, state = statestate,messagesend=messagesend)
    val iteminsert = Message_item(id, response.toString())
    insertMessageItem(messageViewModel = messageViewModel, message_item = iteminsert)
}


