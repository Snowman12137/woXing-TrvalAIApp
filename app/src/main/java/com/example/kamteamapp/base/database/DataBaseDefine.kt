package com.example.kamteamapp.base.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

class DataBaseDefine {
}


//主信息表--->旅游主信息表
@Entity(tableName = "Message_items")
data class Message_item(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,  // 主键
    val message: String,  //信息
)


@Dao
interface MessageDao {
//插入message_item
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertmessageitem(item: Message_item)

    //删除message_item
    @Query("DELETE FROM Message_items")
    fun deletemessageitem()

    //查询所有message_item
    @Query("SELECT * from Message_items")
    fun getallmessageitems(): Flow<List<Message_item>>

    //根据id查询message_item
    @Query("SELECT * from Message_items WHERE id = :id")
    fun getmessageitem(id: Int): Flow<Message_item>
}


@Database(entities = [Message_item::class], version = 1, exportSchema = false)
abstract class  MessageDataBase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    companion object{
        @Volatile
        private var Instance: MessageDataBase? = null
        fun getDatabase(context: Context): MessageDataBase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MessageDataBase::class.java, "message_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}



