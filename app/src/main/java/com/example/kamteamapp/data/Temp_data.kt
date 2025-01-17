package com.example.kamteamapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.room.PrimaryKey
import com.example.kamteamapp.R
import java.time.DayOfWeek
import java.time.LocalDate

data class Temp_Main_Items(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,  // 主键
    val time_start: LocalDate,  // 起始时间
    val trval_day: Int,   // 旅游天数
    val name : String,   // 事件名称
    val other1 : String, // 其他简略信息
    val other2 : String
)





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

enum class TimeLinerColor(val color: Color){
    HOUR_4_light(Color(0x4D12ACA2)),
    HOUR_4_normal(Color(0xFF155E8B)),
    HOUR_8_light(Color(0x4DF48BDC)),
    HOUR_8_normal(Color(0xFFE95A3C)),
    HOUR_12_light(Color(0x4DFAA342)),
    HOUR_12_normal(Color(0xFFF12B27)),

    HOUR_16_light(Color(0x4DE54791)),
    HOUR_16_normal(Color(0xFF6F2668)),
    HOUR_20_light(Color(0x4D817DB9)),
    HOUR_20_normal(Color(0xFF2658B0)),
    HOUR_24_light(Color(0x4D48B6F1)),
    HOUR_24_normal(Color(0xFF399A64))
    ;

    fun getColorByNumber1(number:Int):Color{
        check(number in 1..5) { "Number must be between 1 and 6" }
        return when (number){
            1->HOUR_4_normal.color
            2->HOUR_8_normal.color
            3->HOUR_12_normal.color
            4->HOUR_16_normal.color
            5->HOUR_20_normal.color
            else -> throw IllegalArgumentException("Unsupported number: $number")
        }

    }
    fun getColorByNumber2(number:Int):Color{
        check(number in 1..5) { "Number must be between 1 and 6" }
        return when (number){
            1->HOUR_8_light.color
            2->HOUR_12_light.color
            3->HOUR_16_light.color
            4->HOUR_20_light.color
            5->HOUR_24_light.color
            else -> throw IllegalArgumentException("Unsupported number: $number")
        }
    }

}



data class Temp_Weather_Items(
    val weather_id: Int,  //
    val number_day : Int, //
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
    val content : String,
    val MyMoney :List<Money_Data> = emptyList()
)

data class Temp_Detail_Items(
    val trval_id: Int,
    val model :Int,
    val imageResources: Int ?,
    val name: String,
    val special :List<String>,
    val jianjie :String,

)


data class New_Temp_Trval_Items(
    val trval_id :Int,
    val model : Int,
    val day : Int,
    val Detail_trval : Int,
    val trval_name : String,
    val time :  Between,
    val abbreviate_thing:String,
    val detailThings: List<Detail_Trval_Data> = listOf(),
    val taval_tar:Travels_Data
)
data class Travels_Data(
    val start_tar:String="",
    val end_tar :String="",

    val other:String=""
)

data class Detail_Trval_Data(
    val detail_trval_id: Int,
    val others:String="",
    val times:Between,
    val my_content:String
)


data class Money_Data(
    val Lable:String,
    val Money: Float,
    val CheckState: String // 0是预计花费 1是可选花费 2是已付款
)

data class Between(
    val start : Time,
    val end :Time ?= null
)

data class Time(
    val hour: Int,
    val minite : Int =0
)


// 首页暂时的数据存储
data class Main_items(
    val imageResources: Int,
    val my_text:String,
)

val Tempss = listOf(
    Main_items(
        R.drawable.temp1,
        "哈尔滨两天一夜极限挑战"
    ),Main_items(
        R.drawable.temp2,
        "天津两日游攻略"
    ),Main_items(
        R.drawable.temp3,
        "深圳悠闲四日游"
    ),Main_items(
        R.drawable.temp4,
        "大理带老人五日游"
    ),Main_items(
        R.drawable.temp5,
        "单人川西自驾详细攻略"
    ),

)


//@RequiresApi(Build.VERSION_CODES.O)
//val tempMainItem1 = Temp_Main_Items(
//    id = 1,
//    time_start = LocalDate.of(2024,7,9), // 使用 LocalDate 实例
//    trval_day = 5,
//    weathers_id = 1,
//    trvals_id = 1,
//    name = "粤港澳5日游",
//    other1 = "广州->澳门->香港->深圳"
//)
//@RequiresApi(Build.VERSION_CODES.O)
//val tempMainItem2 = Temp_Main_Items(
//    id = 2,
//    time_start = LocalDate.of(2024,7,9), // 使用 LocalDate 实例
//    trval_day = 3,
//    weathers_id = 5,
//    trvals_id = 5,
//    name = "粤港澳3日游",
//    other1 = "澳门->香港->深圳"
//)
//
//@RequiresApi(Build.VERSION_CODES.O)
//val tempMainItem3 = Temp_Main_Items(
//    id = 3,
//    time_start = LocalDate.of(2024,7,9), // 使用 LocalDate 实例
//    trval_day = 3,
//    weathers_id = 5,
//    trvals_id = 5,
//    name = "西安3日游",
//    other1 = "西安"
//)
//
//
//@RequiresApi(Build.VERSION_CODES.O)
//val tempMainItem4 = Temp_Main_Items(
//    id = 3,
//    time_start = LocalDate.of(2024,7,9), // 使用 LocalDate 实例
//    trval_day = 3,
//    weathers_id = 5,
//    trvals_id = 5,
//    name = "西安3日游(新)",
//    other1 = "西安"
//)



//val exampleUiState: ConversationUiState
//    get() = ConversationUiState(
//        initialMessages = initialMessages,
//        channelName = "新对话",
//    )

//@RequiresApi(Build.VERSION_CODES.O)
//private val initialMessages = listOf(
//    Message(
//        "me",
//        "Check it out!",
//        "8:07 PM"
//    ),
//    Message(
//        "me",
//        "Thank you!$EMOJI_PINK_HEART",
//        "8:06 PM",
//    ),
//    Message(
//        "蓝心大模型",
//        "You can use all the same stuff",
//        "8:05 PM"
//    ),
//    Message(
//        "蓝心大模型",
//        "或者你可以选择方案二",
//        "8:05 PM",
//        CardorImage.CardItem(
//            tempMainItem2
//        )
//    ),
//    Message(
//        "蓝心大模型",
//        "我了解你的需求了，我生成了一个预制方案，请你查看结果是否满意",
//        "8:04 PM",
//        CardorImage.CardItem(
//            tempMainItem1
//        )
//    ),
//    Message(
//        "me",
//        "我想去广州附近玩五天，明天就想出发，我们有两个人，我喜欢时间安排的紧凑一些，多去一些地方，预算大概在3000块左右，酒店我们希望中等价格就可以",
//        "8:03 PM"
//    )
//)


object EMOJI{
    val Bus = "\uD83D\uDE8C"
    val Plane = "\u2708\uFE0F"
    val Photo = "\uD83D\uDCF7"
    val DestiNation = "\uD83D\uDCCD"
    val Food = "\uD83C\uDF71"
    val Home = "\uD83D\uDECF\uFE0F"
}

