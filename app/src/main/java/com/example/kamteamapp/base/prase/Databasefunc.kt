package com.example.kamteamapp.base.prase

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun InsertMessageItem(message: String,id: Int) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        val database = MessageDatabase.getDatabase(context)
        val messageItem = Message_item(id,message = message)
        database.messageDao().insertMessageItem(messageItem)
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DeleteMessageItem(id: Int) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        val database = MessageDatabase.getDatabase(context)
        database.messageDao().deleteMessageItemById(id)
    }
}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DeleteAllMessageItems() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        val database = MessageDatabase.getDatabase(context)
        database.messageDao().deleteAllMessageItems()
    }
}



@Composable
fun getMessageItems(): List<Message_item> {
    val context = LocalContext.current
    val database = MessageDatabase.getDatabase(context)
    val messageDao = database.messageDao()
    val messageItems = messageDao.getAllMessageItems().collectAsState(initial = emptyList())
    return messageItems.value
}
@Composable
fun getMessageItemById( id: Int): Message_item? {
    val context = LocalContext.current
    val database = MessageDatabase.getDatabase(context)
    val messageDao = database.messageDao()
    var messageItem: Message_item? = null
    runBlocking {
        messageItem = messageDao.getMessageItemById(id).firstOrNull()
    }
    return messageItem
}











