package com.example.kamteamapp.data

import androidx.compose.ui.graphics.Color
import androidx.room.PrimaryKey
import com.example.kamteamapp.R
//import com.example.inventory.R
import java.time.DayOfWeek
import java.time.LocalDate

data class Temp_Main_Items(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,  // 主键
    val time_start: LocalDate,  // 起始时间
    val trval_day: Int,   // 旅游天数
    val weathers_id: Int,  // 天气信息主键
    val trvals_id : Int,  // 旅游事件主键
    val name : String,   // 事件名称
    val other1 : String // 其他简略信息
)



enum class Week(){

}

// 天气枚举
enum class Weather(val imageResources: Int) {
    SUNNY_DAY(R.drawable.sunny_light),
    //NIGHT_SUNNY(R.drawable.rename_sunny_night),
    CLOUDY_DAY(R.drawable.cloudy_light),
    //CLOUDY_NIGHT(R.drawable.cloudy_night),
    OVER_CAST(R.drawable.overcast_sky),
    HEAVY_RAIN(R.drawable.rainstorm),
    MODERATE_RAIN(R.drawable.moderate_rain),
    SMALL_RAIN(R.drawable.small_rain);

    // 提供一个获取图像资源 ID 的方法
    fun getImageResId(): Int {
        return imageResources
    }
}

enum class ColorPart(val colors: List<BodyColor>){
    Group1(listOf(BodyColor.FIRST_BODY,BodyColor.FIRST_WEATHER1,BodyColor.FIRST_WEATHER2)),
    Group2(listOf(BodyColor.SECOND_BODY,BodyColor.SECOND_WEATHER1,BodyColor.SECOND_WEATHER2)),
    Group3(listOf(BodyColor.THIRD_BODY,BodyColor.THIRD_WEATHER1,BodyColor.THIRD_WEATHER2))
}

enum class BodyColor(val color: Color) {
    FIRST_WEATHER1(Color(0xFFF2B78D)),
    FIRST_WEATHER2(Color(0xFFF5893A)),
    FIRST_BODY(Color(0xFFFFEBDD)),

    SECOND_WEATHER1(Color(0xFFACEBF9)),
    SECOND_WEATHER2(Color(0xFF3AA7F5)),
    SECOND_BODY(Color(0xFFDDF9FF)),

    THIRD_WEATHER1(Color(0xFFF9ACEC)),
    THIRD_WEATHER2(Color(0xFFF53AB6)),
    THIRD_BODY(Color(0xFFFEDDFF))
}


data class Temp_Weather_Items(
    val weather_id: Int,
    val number_day : Int,
    val where : String,
    val data: LocalDate,
    val week: DayOfWeek,
    val day_weather: Weather,
    val dat_temp: Int,
    val night_weather: Weather,
    val night_temp: Int,
    val attention: String,
)


data class Temp_Trval_Items(
    val trval_id :Int,
    val model : Int,
    val day : Int,
    val Detail_trval : Int,
    val trval_name : String,
    val data :LocalDate,
    val ready : Int, // 0否 1是，2没有该选项
    val time :  Between,
    val content : String


)

data class Between(
    val start : Time,
    val end :Time ?= null
)

data class Time(
    val hour: Int,
    val minite : Int =0
)
