package com.example.kamteamapp.ui.item

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Stable
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kamteamapp.data.Between
import com.example.kamteamapp.data.Temp_Trval_Items
import com.example.kamteamapp.data.Temp_Weather_Items
import com.example.kamteamapp.data.Time
import com.example.kamteamapp.data.Weather
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.random.Random


val TimeLang : Int = 200 // 1小时长度
val DayLang  : Int = 550 // 1天宽度
val DayHight : Int = 0
val BaseHight : Int = 150

@RequiresApi(Build.VERSION_CODES.O)
class DetailMainViewModel : ViewModel(), Actions {

    val state = MutableStateFlow(State())

    private var generateJob: Job? = null
        set(value) {
            field?.cancel()
            field = value
        }


    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.O)
    fun generateTrvalItems() {
        generateJob = viewModelScope.launch {
            val currentState = state.value
            val items = currentState.items.map{item->
                when(item.hereItem){
                    is DisplayItem.TravelItem->{
                        ListItem(
                            x = (item.hereItem.trvalitem.day  -1)*DayLang,
                            y = time_long(item.hereItem.trvalitem.time.start)+BaseHight,
                            item.hereItem
                        )
                    }
                    is DisplayItem.WeatherItem->{
                        ListItem(
                            x= (item.hereItem.weatheritem.number_day-1) * DayLang,
                            y= DayHight,
                            item.hereItem
                        )
                    }
                }
            }
            state.value = currentState.copy(items = items)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setTrvalItem(Temp: List<DisplayItem>) {

        val res = calculateTrval(Temp)

        state.value = state.value.copy(size = res.size, maxX = res.maxX, maxY = res.maxY, items = res.items)
        generateTrvalItems()
    }

    private fun calculateTrval(Temp: List<DisplayItem>):ResultVal{

        val size: Int = Temp.size
        var maxX: Int = 0
        var maxY: Int = TimeLang*24



        val items = Temp.map { item ->
            when(item){
                is DisplayItem.TravelItem ->{
                    ListItem(
                        0,
                        0,
                        DisplayItem.TravelItem(item.trvalitem)
                    )
                }
                is DisplayItem.WeatherItem->{
                    if (item.weatheritem.number_day> maxX) {
                        maxX = item.weatheritem.number_day
                    }
                    ListItem(
                        0,
                        0,
                        DisplayItem.WeatherItem(item.weatheritem)
                    )
                }
            }
        }

        return ResultVal(size, maxX * DayLang, maxY, items)
    }
    private data class ResultVal(
        val size: Int,
        val maxX: Int,
        val maxY: Int,
        val items: List<ListItem> // 包含 items 列表
    )
}


fun time_long(time:Time):Int{
    return (time.hour*TimeLang+time.minite*TimeLang/60)
}


fun timecal(time: Between): Int{
    var start = time_long(time.start)
    if (time.end==null){
        return 0
    }else{
        var end = time_long(time.end)
        return end - start
    }

}

data class State(
    val size: Int = 0,
    val maxX: Int = 0,
    val maxY: Int = 0,
    val items: List<ListItem> = emptyList(),
)

// 旅游事件的存储
data class ListItem(
    val x: Int,
    val y: Int,
    val hereItem : DisplayItem
)


sealed class DisplayItem {
    data class TravelItem(val trvalitem: Temp_Trval_Items) : DisplayItem()
    data class WeatherItem(val weatheritem: Temp_Weather_Items) : DisplayItem()
}

@Stable
interface Actions {

    fun setTrvalItem(Temp: List<DisplayItem>)

}


fun TimeString(time: Between):String{
    if (time.end !=null){
        return  String.format("%d%d:%d%d - %d%d%d%d",
            time.start.hour/10,
            time.start.hour%10,
            time.start.minite/10,
            time.start.minite%10,
            time.end.hour/10,
            time.end.hour%10,
            time.end.minite/10,
            time.end.minite%10,
        )
    }else{
        return String.format("%d%d:%d%d",
            time.start.hour/10,
            time.start.hour%10,
            time.start.minite/10,
            time.start.minite%10,
            )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
val TempRes = listOf(
    Temp_Trval_Items(
        1,
        0,
        1,
        1,
        "西安->广州",
        LocalDate.of(2024,7,7),
        0,
        Between(
            Time(
                0,
                30
            )
        ),
        "行李放到酒店，下一行程->打车"
    ),
    Temp_Trval_Items(
        2,
        1,
        1,
        2,
        "圣心大教堂-爱群大厦",
        LocalDate.of(2024,7,7),
        2,
        Between(
            Time(8,),
            Time(12)
        ),
        "欧式建筑打卡拍照"
    ),
    Temp_Trval_Items(
        3,
        1,
        1,
        1,
        "粤海关博物馆",
        LocalDate.of(2024,7,7),
        0,
        Between(
            Time(12, 30),
            Time(13,40)
        ),
        "广州海关发展史"
    ),
    Temp_Trval_Items(
        4,
        0,
        1,
        1,
        "午餐银灯食府",
        LocalDate.of(2024,7,7),
        0,
        Between(
            Time(14),
            Time(15)
        ),
        "14：00以后打8折"
    ),
    Temp_Trval_Items(
        5,
        0,
        2,
        1,
        "恋爱港-大三巴-疯堂斜港",
        LocalDate.of(2024,7,8),
        0,
        Between(
            Time(11),
            Time(14)
        ),
        "特色手信：永吉街——柠檬王(大三巴附近) /时香花生——话梅皇(大三巴附近)"
    ),Temp_Trval_Items(
        6,
        0,
        2,
        1,
        "疯狂斜港-渔人码头-永利皇宫",
        LocalDate.of(2024,7,8),
        0,
        Between(
            Time(15),
            Time(18)
        ),
        "免费的甜品和奶茶可以拿 / 安德鲁蛋挞"
    ),
)


@RequiresApi(Build.VERSION_CODES.O)
val temdata1 :LocalDate = LocalDate.of(2024,7,9)
@RequiresApi(Build.VERSION_CODES.O)
val temdata2 :LocalDate = LocalDate.of(2024,7,10)
@RequiresApi(Build.VERSION_CODES.O)
val temdata3 :LocalDate = LocalDate.of(2024,7,11)
@RequiresApi(Build.VERSION_CODES.O)
val temdata4 :LocalDate = LocalDate.of(2024,7,12)
@RequiresApi(Build.VERSION_CODES.O)
val temdata5 :LocalDate = LocalDate.of(2024,7,13)
@RequiresApi(Build.VERSION_CODES.O)
val TempWeath = listOf(
    Temp_Weather_Items(
        weather_id = 1,
        number_day = 1,
        where = "广州",
        data = temdata1,
        week = temdata1.dayOfWeek,
        day_weather = Weather.SUNNY_DAY,
        dat_temp = 25,
        night_weather = Weather.OVER_CAST,
        night_temp = 20,
        attention = "注意：夜晚较凉注意增添衣物"
    ),Temp_Weather_Items(
    weather_id = 2,
    number_day = 2,
    where = "澳门",
    data = temdata2,
    week = temdata2.dayOfWeek,
    day_weather = Weather.CLOUDY_DAY,
    dat_temp = 25,
    night_weather = Weather.HEAVY_RAIN,
    night_temp = 20,
    attention = "注意：夜晚较凉注意增添衣物"
),Temp_Weather_Items(
        weather_id = 3,
        number_day = 3,
        where = "澳门",
        data = temdata3,
        week = temdata3.dayOfWeek,
        day_weather = Weather.OVER_CAST,
        dat_temp = 25,
        night_weather = Weather.MODERATE_RAIN,
        night_temp = 20,
        attention = "注意：夜晚较凉注意增添衣物"
    ),Temp_Weather_Items(
        weather_id = 4,
        number_day = 4,
        where = "澳门",
        data = temdata4,
        week = temdata4.dayOfWeek,
        day_weather = Weather.SMALL_RAIN,
        dat_temp = 25,
        night_weather = Weather.OVER_CAST,
        night_temp = 20,
        attention = "注意：夜晚较凉注意增添衣物"
    ),Temp_Weather_Items(
        weather_id = 5,
        number_day = 5,
        where = "澳门",
        data = temdata4,
        week = temdata4.dayOfWeek,
        day_weather = Weather.HEAVY_RAIN,
        dat_temp = 25,
        night_weather = Weather.OVER_CAST,
        night_temp = 20,
        attention = "注意：夜晚较凉注意增添衣物"
    )
)
