package com.example.kamteamapp.base.databasenew

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kamteamapp.base.databasenew.message.Message
import com.example.kamteamapp.base.databasenew.message.MessageDao


@Database(entities = [Message::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app-database").build()
        }
    }
}


//@Database(entities = [Message::class], version = 1, exportSchema = false)
//abstract class AppDatabase : RoomDatabase() {
//
//    abstract fun messageDao(): MessageDao
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        private fun getDatabase() = INSTANCE ?: synchronized(AppDatabase::class.java) {
//            INSTANCE ?: buildDatabase().also {
//                INSTANCE = it
//            }
//        }
//
//        private fun buildDatabase(context: Context = BaseContentProvider.context()): AppDatabase {
//            return Room.databaseBuilder(
//                context,
//                AppDatabase::class.java,
//                "app_database"
//            ).build()
//        }
//
//        @JvmStatic
//        fun getHistoryDao(): MessageDao {
//            return getDatabase().messageDao()
//        }
//
//
//        @JvmStatic
//        fun closeDB() {
//            getDatabase().close()
//        }
//
//    }
//    override fun close() {
//        super.close()
//        //数据库关闭后把instance置空
//        INSTANCE = null
//    }
//
//}