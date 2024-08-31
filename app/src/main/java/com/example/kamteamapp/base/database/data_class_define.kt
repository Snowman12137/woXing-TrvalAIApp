package com.example.kamteamapp.base.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

class data_class_define {
}

//主信息表--->旅游主信息表
@Entity(tableName = "Main_items")
data class Main_item(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,  // 主键
    val time_start: String,  // 起始时间
    val trval_day: Int,   // 旅游天数
    val weathers_id: Int,  // 天气信息主键
    val trvals_id : Int,  // 旅游事件主键
    val name : String,   // 事件名称
    val other1 : String // 其他简略信息
)

//===============================================================
//定义主信息表和事件表的关系
data class mainwithtravel(
    @Embedded val mainItem: Main_item,
    @Relation(
        parentColumn = "trvals_id",
        entityColumn = "main_travel_id"
    )
    val travelItems: List<Travel_item>
)
//旅游事件表
@Entity(tableName = "Travel_Items")
data class Travel_item(
    @PrimaryKey( autoGenerate = true)
    val trval_id :Int,   // 主键
    val main_travel_id: Int, // 外键，
    val model : Int,    //事件性质
    val Detail_travel : Int,  // 详细事件
    val trval_name : String,  // 事件名称
    val time: String,   // 事件时间
    val abbreviate_thing : String,   // 事件内容
    val detailthings : String,   // 事件详细内容
    val travel_location : String,   // 事件地点
)

//====================================================================
//定义主信息表和天气的关系
data class mainwithweather(
    @Embedded val mainItem: Main_item,
    @Relation(
        parentColumn = "weathers_id",
        entityColumn = "main_weather_id"
    )
    val weatherItems: List<Weather_item>
)

//天气事件表
@Entity(tableName = "Weather_Items")
data class Weather_item(
    @PrimaryKey( autoGenerate = true)
    val weather_id: Int,  //
    val main_weather_id: Int, // 外键
    val number_day : Int, //
    val where : String,
    val date : String,
    val week: String,
    val day_weather: String,
    val dat_temp: Int,
    val night_weather: String,
    val night_temp: Int,
    val attention: String,
)

//=====================================================================
//定义旅游表和详细事件的关系
data class travelwithdetail(
    @Embedded val travelitem: Travel_item,
    @Relation(
        parentColumn = "Detail_travel",
        entityColumn = "travel_detail_id"
    )
    val detailthingsItem: List<Detailthings_item>
)
//详细事件表
@Entity(tableName = "Detailthings_Items")
data class Detailthings_item(
    @PrimaryKey( autoGenerate = true)
    val detail_id: Int,  //
    val travel_detail_id: Int, // 外键，引用Travel_Items表的Detail_trval
    val others: String,
    val times: String,
    val my_content: String,
)


//=====================================================================
