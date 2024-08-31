package com.example.myapplication.Roomfinal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

class Databasedefine {
}

@Database(entities = [Main_item::class, Travel_item::class,Weather_item::class,Detailthings_item::class], version = 1, exportSchema = false)
abstract class  Traveldatabase : RoomDatabase() {
    abstract fun databaseDao(): Connect
    companion object {
        @Volatile
        private var Instance: Traveldatabase? = null
        fun getDatabase(context: Context): Traveldatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, Traveldatabase::class.java, "my_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}