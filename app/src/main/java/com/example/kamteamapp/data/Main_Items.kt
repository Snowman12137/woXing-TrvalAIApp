package com.example.kamteamapp.data

import android.provider.ContactsContract.Data
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "main_items")
data class Main_Items(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0 ,  // 主键
    val time_start: LocalDate,  // 起始时间
    val trval_day: Int,   // 旅游天数
    val weathers_id: Int,  // 天气信息主键
    val trvals_id : Int,  // 旅游事件主键
    val name : String,   // 事件名称
)




