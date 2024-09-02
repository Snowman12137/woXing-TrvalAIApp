package com.example.kamteamapp.base.prase
import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters



@Entity(tableName = "Message_items")
data class Message_item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,  // 主键
    val message: String,  // 信息
)
class databasedefinetest {
}


@Dao
interface MessageDao {
    // 插入message_item
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMessageItem(item: Message_item)

    // 查询所有message_item
    @Query("SELECT * FROM Message_items")
    fun getAllMessageItems(): Flow<List<Message_item>>

    // 根据id查询message_item
    @Query("SELECT * FROM Message_items WHERE id = :id")
    fun getMessageItemById(id: Int): Flow<Message_item>
}


@Database(entities = [Message_item::class], version = 1, exportSchema = false)
@TypeConverters()
abstract class MessageDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    companion object {
        @Volatile
        private var instance: MessageDatabase? = null

        fun getDatabase(context: Context): MessageDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): MessageDatabase {
            return Room.databaseBuilder(context, MessageDatabase::class.java, "message_database")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}

