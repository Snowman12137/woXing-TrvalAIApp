package com.example.kamteamapp.base.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow

class MessageViewModel(application: Application): AndroidViewModel(application) {

    private val databaseport:MessageDao = MessageDataBase.getDatabase(application).messageDao()

    val allMessageItems = databaseport.getallmessageitems()

    fun insertMessageItem(item: Message_item) {
        databaseport.insertmessageitem(item)
    }

    fun findMessageItem(id: Int): Flow<Message_item> {
        return databaseport.getmessageitem(id)
    }

    fun deleteMessageItem() {
        databaseport.deletemessageitem()
    }

}