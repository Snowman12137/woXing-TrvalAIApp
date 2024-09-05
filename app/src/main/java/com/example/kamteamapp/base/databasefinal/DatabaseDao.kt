package com.example.kamteamapp.base.databasefinal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDao  {

    //插入========================================
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertmainitem(mainitems: Mainitems)

    //插入消息
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertmessagechat(messagechat: Messagechat)

    //插入旅游
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserttravelitems(travelitems: Travelitems)

    //查询主要信息byid
    @Query("SELECT * FROM mainitems WHERE id = :id")
    fun getmainitembyid(id: Int): Flow<Mainitems>

    //查询
    @Query("SELECT * FROM mainitems")
    fun getallmainitems(): Flow<List<Mainitems>>

    //根据maintochat查询
    @Query("SELECT * FROM messagechat WHERE maintochat = :maintochat ORDER BY id DESC")
    fun getallmessagechat(maintochat: Int): Flow<List<Messagechat>>

    //根据maintotravel查询
    @Query("SELECT * FROM travelitems WHERE maintotravel = :maintotravel")
    fun getalltravelitems(maintotravel: Int): Flow<Travelitems>


    //根据主表的事件名称为空返回主表的id
    @Query("SELECT id FROM mainitems WHERE name = '无'")
    fun getmainitembynullname(): Flow<List<Int>>

    //将主表的name修改为有
    @Query("UPDATE mainitems SET name = '有' WHERE id = :id")
    suspend fun updatemainitembyid(id: Int)

    //删除全部数据
    @Query("DELETE FROM mainitems")
    suspend fun deleteallmainitems()

    //删除全部数据
    @Query("DELETE FROM messagechat")
    suspend fun deleteallmessagechat()

    //删除全部数据
    @Query("DELETE FROM travelitems")
    suspend fun deletealltravelitems()




}
