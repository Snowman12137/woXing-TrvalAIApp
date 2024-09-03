package com.example.kamteamapp.base.databasenew.message

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "key") val key: Long,
    @ColumnInfo(name = "value") val value: String,
)