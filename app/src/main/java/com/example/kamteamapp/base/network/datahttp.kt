package com.example.kamteamapp.base.network
import android.util.Log
import org.json.JSONObject
import org.json.JSONArray
class datahttp {
}
data class MainItem(
    val time_start: String,
    val trval_day: String,
    val name: String
)

data class WeatherItem(
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

data class DetailThing(
    val detail_id: Int,
    val others: String,
    val times: String,
    val my_content: String
)

data class TravelItem(
    val trval_id: Int,
    val model: Int,
    val day: Int,
    val trval_name: String,
    val time: String,
    val abbreviate_thing: String,
    val detailThings: List<DetailThing>?,
    val travels_data: List<TravelData>?
)

data class TravelData(
    val start_star: String,
    val end_star: String,
    val other: String
)


fun parsetest(jsonString: String): String {
    return try {
        val jsonObject = JSONObject(jsonString)
        val mainJson = jsonObject.getJSONObject("main")
        return mainJson.toString()
    } catch (e: Exception) {
        "Error: ${e.message}"
    }
}


fun parseJsonToItems(jsonString: String): Triple<MainItem, List<WeatherItem>, List<TravelItem>> {
    val jsonObject = JSONObject(jsonString)

    // Parse MainItem
    val mainJson = jsonObject.getJSONObject("main")
    val mainItem = MainItem(
        time_start = mainJson.getString("time_start"),
        trval_day = mainJson.getString("trval_day"),
        name = mainJson.getString("name")
    )

    // Parse WeatherItem list
    val weatherJsonArray = jsonObject.getJSONArray("weather")
    val weatherItems = mutableListOf<WeatherItem>()
    for (i in 0 until weatherJsonArray.length()) {
        val weatherJson = weatherJsonArray.getJSONObject(i)
        val weatherItem = WeatherItem(
            number_day = weatherJson.getInt("number_day"),
            where = weatherJson.getString("where"),
            data = weatherJson.getString("data"),
            week = weatherJson.getInt("week"),
            day_weather = weatherJson.getString("day_weather"),
            day_temp = weatherJson.getString("day_temp"),
            night_weather = weatherJson.getString("night_weather"),
            night_temp = weatherJson.getString("night_temp"),
            attention = weatherJson.getString("attention")
        )
        weatherItems.add(weatherItem)
    }

    // Parse TravelItem list
    val travelJsonArray = jsonObject.getJSONArray("trval")
    val travelItems = mutableListOf<TravelItem>()
    for (i in 0 until travelJsonArray.length()) {
        val travelJson = travelJsonArray.getJSONObject(i)
        val detailThingsJsonArray = travelJson.optJSONArray("detailThings")
        val detailThings = detailThingsJsonArray?.let {
            val details = mutableListOf<DetailThing>()
            for (j in 0 until it.length()) {
                val detailJson = it.getJSONObject(j)
                val detailThing = DetailThing(
                    detail_id = detailJson.getInt("detail_id"),
                    others = detailJson.getString("others"),
                    times = detailJson.getString("times"),
                    my_content = detailJson.getString("my_content")
                )
                details.add(detailThing)
            }
            details
        }

        val travelsDataJsonArray = travelJson.optJSONArray("Travels_data")
        val travelsData = travelsDataJsonArray?.let {
            val data = mutableListOf<TravelData>()
            for (j in 0 until it.length()) {
                val dataJson = it.getJSONObject(j)
                val travelData = TravelData(
                    start_star = dataJson.getString("start_star"),
                    end_star = dataJson.getString("end_star"),
                    other = dataJson.getString("other")
                )
                data.add(travelData)
            }
            data
        }

        val travelItem = TravelItem(
            trval_id = travelJson.getInt("trval_id"),
            model = travelJson.getInt("model"),
            day = travelJson.getInt("day"),
            trval_name = travelJson.getString("trval_name"),
            time = travelJson.getString("time"),
            abbreviate_thing = travelJson.getString("abbreviate_thing"),
            detailThings = detailThings,
            travels_data = travelsData
        )
        travelItems.add(travelItem)
    }

    return Triple(mainItem, weatherItems, travelItems)
}