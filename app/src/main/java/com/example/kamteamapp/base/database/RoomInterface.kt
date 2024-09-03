package com.example.kamteamapp.base.database

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

class RoomInterface {
}


//根据id查找特定的message
@Composable
fun findMessageItem(id: Int,messageViewModel: MessageViewModel): String {
    val messageinroom by messageViewModel.findMessageItem(id).collectAsState(initial = null)
//    val messageinroom = messageViewModel.findMessageItem(id)
    val output = messageinroom?.message.toString()
    Log.d("test", "dataprase: $output")
    return output
}


//查找所有的message
@Composable
fun findallMessageItem(messageViewModel: MessageViewModel): List<Message_item> {
    val allMessageItems by messageViewModel.allMessageItems.collectAsState(initial = emptyList())
    return allMessageItems
}



fun insertMessageItem(messageViewModel: MessageViewModel, message_item: Message_item) {

    messageViewModel.insertMessageItem(message_item)
}

fun deleteMessageItem(messageViewModel: MessageViewModel) {
    messageViewModel.deleteMessageItem()
}

