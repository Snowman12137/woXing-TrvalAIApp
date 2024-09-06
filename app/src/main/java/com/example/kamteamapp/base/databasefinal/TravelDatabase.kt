package com.example.kamteamapp.base.databasefinal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Mainitems::class,Messagechat::class,Travelitems::class], version = 1)
abstract class TravelDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao
    companion object {
        private var instance: TravelDatabase? = null

        fun getDatabase(context: Context): TravelDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): TravelDatabase {
            return Room.databaseBuilder(context,TravelDatabase::class.java, "databaselastfinal3").build()
        }
    }
}