package com.example.kamteamapp.base.databasefinal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kamteamapp.R
import com.example.kamteamapp.ui.chat.CardorImage
import java.time.LocalDate


@Entity
data class Mainitems(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,  // 主键
    val time_start: String,  // 起始时间
    val trval_day: String,   // 旅游天数
    val name : String,   // 事件名称
    val other1 : String,// 其他简略信息
    //去搜索消息信息
    val message_id : Int,  // 消息主键
    //去搜索旅游加载信息
    val travel_id: Int //其他简略信息2
)

@Entity
data class Messagechat(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,  // 主键
    val author : String,  // 作者
    val maintochat: Int,  // 主要信息主键
    val message: String,  // 消息
    val timestamp : String,  // 时间戳
    val cardorimage: String? = null,
    val authorImage: Int
)

@Entity
data class Travelitems(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,  // 主键
    val maintotravel: Int,  // 主要信息主键
    val tr: String,  // 消息
)


