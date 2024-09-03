package com.example.kamteamapp.base.databasenew.message


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao  {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: Message): Long

    @Delete
    suspend fun delete(message: Message): Int

    @Query("SELECT * FROM message WHERE `key` = :key And `value` = :value ORDER BY id DESC LIMIT 1")
    suspend fun getByValue(key: Long, value: String): Message?

    @Query("SELECT * FROM message WHERE `key` = :key ")
    suspend fun getBykey(key: Long): Flow<Message?>


    @Query("SELECT * FROM message")
    fun getall(): Flow<List<Message>>

    //删除message_item
    @Query("DELETE FROM message")
    fun deleteall()
}
