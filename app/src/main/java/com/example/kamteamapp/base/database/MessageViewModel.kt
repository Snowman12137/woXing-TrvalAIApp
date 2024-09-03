package com.example.kamteamapp.base.database

import android.app.Application
import androidx.activity.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kamteamapp.network.HttpViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
//
//class MessageViewModel(application: Application): AndroidViewModel(application) {
//
//    private val databaseport:MessageDao = MessageDataBase.getDatabase(application).messageDao()
//
//    val allMessageItems = databaseport.getallmessageitems()
//
//    fun insertMessageItem(item: Message_item) {
//            databaseport.insertmessageitem(item)
//    }
//
//    fun findMessageItem(id: Int): Flow<Message_item> {
//        return databaseport.getmessageitem(id)
//    }
//
//    fun deleteMessageItem() {
//        databaseport.deletemessageitem()
//    }
//
//}
class myviewlmodel: ViewModel(){




}




class MessageViewModel(application: Application): AndroidViewModel(application) {
    private val databaseport: MessageDao = MessageDataBase.getDatabase(application).messageDao()
    val allMessageItems = databaseport.getallmessageitems()

    fun insertMessageItem(item: Message_item) {
        viewModelScope.launch(Dispatchers.IO) {
            databaseport.insertmessageitem(item)
        }
    }

    fun findMessageItem(id: Int): Flow<Message_item> {
         return databaseport.getmessageitem(id)
    }

    fun deleteMessageItem() {
        viewModelScope.launch(Dispatchers.IO) {
            databaseport.deletemessageitem()
        }
    }
}