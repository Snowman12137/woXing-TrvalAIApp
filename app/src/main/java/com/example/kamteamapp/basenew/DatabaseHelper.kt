package com.example.kamteamapp.base.databasenew

import android.content.Context
import com.example.kamteamapp.base.databasenew.message.Message
import kotlinx.coroutines.flow.Flow

object DatabaseHelper {
    private lateinit var appDatabase: AppDatabase

    fun init(context: Context) {
        appDatabase = AppDatabase.getDatabase(context)
    }

    suspend fun insertMessage(key: Long, value: String) {
        appDatabase.messageDao().insert(Message(key = key, value = value))
    }

    suspend fun deleteAllMessages() {
        appDatabase.messageDao().deleteall()
    }

    fun getAllMessages(): Flow<List<Message>> {
        return appDatabase.messageDao().getall()
    }

    fun getMessageByKey(key: Long): Flow<Message?> {
        return appDatabase.messageDao().getBykey(key)
    }

    fun closeDatabase() {
        // Room 自动管理数据库连接，通常不需要手动关闭
    }
}


////获取发送消息历史
//fun getsendhistory(): Flow<List<Message>> {
//    return AppDatabase.getHistoryDao().getByType(sendmessage)
//}
//
////插入接收消息
//suspend fun insertresponse(message: String) {
//    val history = AppDatabase.getHistoryDao().getByValue(key = acceptmessage, value = message)
//    if (history != null) {
//        AppDatabase.getHistoryDao().delete(history)
//    }
//    AppDatabase.getHistoryDao().insert(Message(key = sendmessage, value = message))
//}
//
////获取接收消息历史
//fun getacceptHistory(): Flow<List<Message>> {
//    return AppDatabase.getHistoryDao().getByType(acceptmessage)
//}
