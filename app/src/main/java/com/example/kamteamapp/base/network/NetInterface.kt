package com.example.kamteamapp.base.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.kamteamapp.base.database.MessageViewModel
import com.example.kamteamapp.network.MarsUiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetInterface {
}
// 修改 getresponse 函数以返回 Flow<String>

//fun getresponse(netViewModel: NetViewModel, state: MarsUiState, messagesend: String): String {
//    netViewModel.sendmessage(messagesend)
//    while (true) {
//        val response = netViewModel.getresponse(state)
//        if (response != "Loading" && response != "Error") {
//            return response
//            break
//        }
//    }
//}

//获取响应
fun getresponse(netViewModel: NetViewModel,state:MarsUiState,messagesend:String): Flow<String> {
    netViewModel.sendmessage(messagesend)
    val response = netViewModel.getresponse(state)
    return response
}