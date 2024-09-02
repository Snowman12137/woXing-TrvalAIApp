package com.example.kamteamapp.base.database

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.kamteamapp.base.network.NetViewModel
import com.example.kamteamapp.base.network.getresponse
import com.example.kamteamapp.network.MarsUiState


fun NettoRoomInterface(netViewModel: NetViewModel, statestate: MarsUiState, messagesend:String,messageViewModel: MessageViewModel,id:Int) {
    val response = getresponse(netViewModel = netViewModel, state = statestate,messagesend=messagesend)
    val iteminsert = Message_item(1,response)
    insertMessageItem(messageViewModel = messageViewModel, message_item = iteminsert)
}


