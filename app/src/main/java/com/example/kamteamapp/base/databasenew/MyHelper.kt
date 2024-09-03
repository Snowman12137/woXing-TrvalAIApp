package com.example.kamteamapp.base.databasenew

import com.example.kamteamapp.base.databasenew.message.Message
import kotlinx.coroutines.flow.Flow

object WanHelper{

    //插入消息
    suspend fun insertmessage(key:Long, message: String) {
        AppDatabase.getHistoryDao().insert(Message(key = key, value = message))
    }
    //删除消息
    suspend fun deleteallmessage() {
        AppDatabase.getHistoryDao().deleteall()
    }

    //获取全部消息
    suspend fun getallmessage(): Flow<List<Message>> {
        return AppDatabase.getHistoryDao().getall()
    }

    //获取消息bykey
    suspend fun getmessage(key: Long): Message? {
        return AppDatabase.getHistoryDao().getBykey(key)
    }

    //关闭数据库
    fun close() {
        AppDatabase.closeDB()
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
