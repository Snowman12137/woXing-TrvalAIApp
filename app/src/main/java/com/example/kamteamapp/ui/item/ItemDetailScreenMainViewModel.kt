package com.example.kamteamapp.ui.item

import android.annotation.SuppressLint
import android.os.Build
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kamteamapp.data.Between
import com.example.kamteamapp.data.Money_Data
import com.example.kamteamapp.data.Temp_Trval_Items
import com.example.kamteamapp.data.Temp_Weather_Items
import com.example.kamteamapp.data.Time
import com.example.kamteamapp.data.Weather
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate


val metrics = DisplayMetrics()

val Weather_background = 100

val TimeLang : Int = 200 //200 // 1小时长度
val DayLang  : Int = 600 //550 // 1天宽度
val DayHight : Int = 20


val TimeLangDP : Int = 80 //200 // 1小时长度
val DayLangDP  : Int = 210 //550 // 1天宽度



val BaseHight : Int = 150

val BasePadding: Int = 20

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
            val items = currentState.items.mapIndexed{index,item->
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
                            x= (item.hereItem.weatheritem.number_day-1) * DayLang + BasePadding,
                            y= DayHight,
                            item.hereItem
                        )
                    }
                    is DisplayItem.BackGroundItem->{
                        if (index == state.value.data){
                            ListItem(
                                x= 0,
                                y= 0,
                                DisplayItem.BackGroundItem(
                                    BackGround(
                                        index = 0,
                                        isWeather = true,
                                        weide = state.value.data* DayLangDP,
                                        hight = Weather_background,
                                        color = Color.White
                                    )
                                )
                            )
                        }else{
                            ListItem(
                                x= index * DayLang,
                                y= 0,
                                item.hereItem
                            )
                        }

                    }
                }
            }
            state.value = currentState.copy(items = items)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun setTrvalItem(Temp: List<DisplayItem>) {

        val res = calculateTrval(Temp)

        state.value = state.value.copy(data = res.data,size = res.size, maxX = res.maxX, maxY = res.maxY, items = res.items)
        generateTrvalItems()
    }

    private fun calculateTrval(Temp: List<DisplayItem>):ResultVal{

        val size: Int = Temp.size
        var maxY: Int = TimeLang*24
        var numberday :Int =0



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
                    if (item.weatheritem.number_day> numberday) {
                        numberday = item.weatheritem.number_day
                    }
                    ListItem(
                        0,
                        0,
                        DisplayItem.WeatherItem(item.weatheritem)
                    )
                }
                // 理论上这里绝对不会出现BackGroundItem类型
                is DisplayItem.BackGroundItem->{
                    ListItem(
                        0,
                        0,
                        DisplayItem.BackGroundItem(BackGround(color = Color.White, weide = 0, hight = 0))
                    )
                }
            }
        }
        val itembackground: List<ListItem> = List(numberday+1){index->
            ListItem(
                0,
                0,
                DisplayItem.BackGroundItem(
                    BackGround(
                        index = index,
                        color = Color.White,
                        weide = DayLangDP, // 这里的Weide和hight应该是DP类型，最后再转化也可以
                        hight = 24 * TimeLangDP  // 因为最后输入modify的size是DP类型
                    )                               // 坐标偏移是px和DP不一样
                )
            )
        }
        val Res = itembackground + items


        return ResultVal(numberday,size, numberday * DayLang, maxY, Res)
    }

    override fun ChangeXY_Top(offsetStateY: Int){
        // 首先，获取当前状态的值
        val currentState = state.value
        // 接着，获取当前索引处的 ListItem

        val items = currentState.items.map{item->
            when(item.hereItem){
                is DisplayItem.TravelItem->{
                    ListItem(
                        x = item.x,
                        y = item.y,
                        item.hereItem
                    )
                }
                is DisplayItem.WeatherItem->{
                    ListItem(
                        x= item.x,
                        y= offsetStateY,
                        item.hereItem
                    )
                }
                is DisplayItem.BackGroundItem->{
                    if (item.hereItem.backGround.isWeather){
                        ListItem(
                            x = item.x,
                            y = offsetStateY,
                            item.hereItem
                        )
                    }else{
                        ListItem(
                            x = item.x,
                            y = item.y,
                            item.hereItem
                        )
                    }
                }
            }
        }
        state.value = currentState.copy(items = items)
    }


    private data class ResultVal(
        val data :Int,
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
    val data :Int = 0,
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
    data class BackGroundItem(val backGround: BackGround):DisplayItem()
}

data class BackGround(
    val index : Int = 0,
    val isWeather : Boolean = false,
    val weide : Int,
    val hight :Int,
    val color:Color
)


@Stable
interface Actions {

    fun setTrvalItem(Temp: List<DisplayItem>)
    fun ChangeXY_Top(offsetStateY: Int)
}


fun TimeString(time: Between):String{
    if (time.end !=null){
        return  String.format("%d%d:%d%d - %d%d:%d%d",
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
        "行李放到酒店，下一行程->打车",
        listOf(
            Money_Data(
                "飞机票",
                890f,
                "2"
            ), Money_Data(
                "保险",
                10f,
                "2"
            ), Money_Data(
                "额外托运",
                100f,
                "1"
            ), Money_Data(
                "机场小吃",
                50f,
                "0"
            )
        )
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
        2,
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
        1,
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
        1,
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
    ),Temp_Trval_Items(
        7,
        1,
        2,
        1,
        "伦敦人-巴黎人-威尼斯人-银河酒店",
        LocalDate.of(2024,7,8),
        0,
        Between(
            Time(19),
            Time(21,40)
        ),
        "发财车最后一班22：30\n 泰妈船面(官也街附近)，泽贤鸡蛋仔，百乐肉粉面，金利食堂 \n 特色手信:最香饼家--杏仁饼/喜临门--虾仔面"
    ),Temp_Trval_Items(
        8,
        1,
        1,
        1,
        "沙面岛--永兴坊",
        LocalDate.of(2024,7,7),
        0,
        Between(
            Time(15,30),
            Time(19)
        ),
        "需要提前两天预约"
    ),Temp_Trval_Items(
        9,
        2,
        1,
        1,
        "晚餐:大鸽饭",
        LocalDate.of(2024,7,7),
        0,
        Between(
            Time(19),
            Time(18,30)
        ),
        "其他:有囍糖水，银记肠粉"
    ),Temp_Trval_Items(
        10,
        1,
        1,
        1,
        "海心桥广州塔",
        LocalDate.of(2024,7,7),
        0,
        Between(
            Time(20),
            Time(22)
        ),
        "打卡夜景，Citywalk"
    ),Temp_Trval_Items(
        11,
        1,
        3,
        1,
        "旺角-油麻地-尖沙咀",
        LocalDate.of(2024,7,9),
        0,
        Between(
            Time(9,30),
            Time(13)
        ),
        "推荐美食:推荐美食: 旺角——两草(张智霖打卡) 肥姐小食店，吃茶三千招牌国王奶茶 尖沙咀——华嫂冰室/红茶冰室，富豪雪糕车 13/个只收现金!!!!，bakehouse蛋挞 12/个"
    ),Temp_Trval_Items(
        12,
        1,
        3,
        1,
        "星光大道-天星小轮",
        LocalDate.of(2024,7,9),
        0,
        Between(
            Time(14),
            Time(16)
        ),
        "拍照！！！"
    ),Temp_Trval_Items(
        13,
        2,
        3,
        1,
        "湾仔码头-铜锣湾",
        LocalDate.of(2024,7,9),
        0,
        Between(
            Time(17),
            Time(20)
        ),
        "铜锣湾——荣记粉面(陈奕迅推荐，只收现 金!!!!) jollibee炸鸡(小贵)"
    ),Temp_Trval_Items(
        14,
        1,
        3,
        1,
        "中环摩天轮-维多利亚港夜景",
        LocalDate.of(2024,7,9),
        0,
        Between(
            Time(20,30),
            Time(22,30)
        ),
        "摩天轮：20/人 + 拍视频 \uD83C\uDF81 特产手信:珍妮小熊——尖沙咀 711——巨峰乌龙、 兆成行——香薰店，收现金(张曼玉，舒淇 同款) 大生超市——各种巧克力 药品——万宁 护照夹——Petite petite"
    ),Temp_Trval_Items(
        15,
        1,
        4,
        1,
        "坚尼地域-西环泳棚",
        LocalDate.of(2024,7,10),
        0,
        Between(
            Time(8,11),
            Time(11)
        ),
        "坚尼地城和西环泳棚打卡点 地铁坚尼地城C 口出 \uD83D\uDE0B 推荐美食:坚尼地城——诚记"
    ),Temp_Trval_Items(
        16,
        1,
        4,
        1,
        "半山扶梯-平顶山(步行25分钟左右)",
        LocalDate.of(2024,7,10),
        0,
        Between(
            Time(11,40),
            Time(15)
        ),
        "缆车来回88，时间充裕的话还可以在维港拍 成功人士打卡照\n半山扶梯——泰奶，奶绿好喝"
    ),Temp_Trval_Items(
        17,
        2,
        4,
        1,
        "香港->深圳(过海关一个小时)",
        LocalDate.of(2024,7,10),
        0,
        Between(
            Time(18),
            Time(19,30)
        ),
        "XXXX"
    ),Temp_Trval_Items(
        18,
        0,
        4,
        1,
        "住宿",
        LocalDate.of(2024,7,10),
        0,
        Between(
            Time(18),
            Time(19,30)
        ),
        "XXXX"
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
