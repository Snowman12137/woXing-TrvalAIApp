package com.example.kamteamapp.base.prase
import com.google.gson.Gson

class PraseViewModel {
}
// 定义数据类以映射 JSON 数据

data class TravelData(
    val main: Main,
    val weather: List<Weather>,
    val trval: List<Travel>
)
data class Main(
    val time_start: String,
    val trval_day: String,
    val name: String
)

data class Weather(
    val number_day: Int,
    val where: String,
    val data: String,
    val week: Int,
    val day_weather: String,
    val day_temp: String,
    val night_weather: String,
    val night_temp: String,
    val attention: String
)


data class Travel(
    val trval_id: Int,
    val model: Int,
    val day: Int,
    val trval_name: String,
    val time: String,
    val abbreviate_thing: String,
    val detailThings: List<DetailThing>? = null,
    val Travels_data: TravelDataDetail? = null
)

data class DetailThing(
    val detail_id: Int,
    val others: String,
    val times: String,
    val my_content: String
)

data class TravelDataDetail(
    val start_star: String,
    val end_star: String,
    val other: String
)

// 函数来解析 JSON 字符串
fun parseTravelData(jsonString: String): TravelData {
    val gson = Gson()
    return gson.fromJson(jsonString, TravelData::class.java)
}