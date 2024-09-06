package com.example.kamteamapp.base.databasefinal

import android.content.Context
import kotlinx.coroutines.flow.Flow

object DataHelper {
    private lateinit var travelDatabase: TravelDatabase

    fun init(context: Context) {
        travelDatabase = TravelDatabase.getDatabase(context)
    }

    suspend fun insertmainitem(mainitems: Mainitems) {
        travelDatabase.databaseDao().insertmainitem(mainitems)
    }

    suspend fun insertmessagechat(messagechat: Messagechat) {
        travelDatabase.databaseDao().insertmessagechat(messagechat)
    }

    suspend fun inserttravelitems(travelitems: Travelitems) {
        travelDatabase.databaseDao().inserttravelitems(travelitems)
    }



    fun getmainitembyid(id: Int): Flow<Mainitems> {
        return travelDatabase.databaseDao().getmainitembyid(id)
    }


    fun getallmainitems(): Flow<List<Mainitems>> {
        return travelDatabase.databaseDao().getallmainitems()
    }

    fun get_chat(mainitems: Mainitems): Flow<List<Messagechat>> {
        val idbymain = mainitems.message_id
        return get_allmessagechat(idbymain)
    }

    fun get_travel(mainitems: Mainitems): Flow<Travelitems> {
        val idbymain = mainitems.travel_id
        return get_travelitems(idbymain)
    }

    fun get_allmessagechat(maintochat: Int): Flow<List<Messagechat>> {
        return travelDatabase.databaseDao().getallmessagechat(maintochat)
    }

    fun get_travelitems(maintotravel: Int): Flow<Travelitems> {
        return travelDatabase.databaseDao().getalltravelitems(maintotravel)
    }

    fun findmainitembynullname(): Flow<List<Int>> {
        return travelDatabase.databaseDao().getmainitembynullname()
    }


    suspend fun deleteallmainitems() {
        travelDatabase.databaseDao().deleteallmainitems()
    }


    suspend fun deleteallmessagechat() {
        travelDatabase.databaseDao().deleteallmessagechat()
    }

    suspend fun deletealltravelitems() {
        travelDatabase.databaseDao().deletealltravelitems()
    }

    //更新主表的name
    suspend fun updatemainitembyid(id: Int) {
        travelDatabase.databaseDao().updatemainitembyid(id)
    }


    suspend fun updatemain_string(id: Int, timestart:String, travelday:String, name:String){
        travelDatabase.databaseDao().updatemain_string(id,timestart,travelday,name)

    }

    //根据travel的id跟新travel的tr
    suspend fun updatetravel_string(id: Int, tr: String) {
        travelDatabase.databaseDao().updatetravel_string(id, tr)
    }



    fun closeDatabase() {
        // Room 自动管理数据库连接，通常不需要手动关闭
    }
}
