package com.example.kamteamapp.base.network
import com.example.kamteamapp.base.database.Main_item
import com.example.kamteamapp.base.database.Travel_item
import com.example.kamteamapp.base.database.Weather_item
import org.json.JSONObject
import org.json.JSONArray
class JsonAnalysis {
    fun parseJsonToItems(jsonString: String): Triple<Main_item, List<Travel_item>, List<Weather_item>> {
        val jsonObject = JSONObject(jsonString)
        // 解析 Main_item
        val mainItemJson = jsonObject.getJSONObject("main_item")
        val mainItem = Main_item(
            id = mainItemJson.getInt("id"),
            time_start = mainItemJson.getString("time_start"),
            trval_day = mainItemJson.getInt("trval_day"),
            weathers_id = mainItemJson.getInt("weathers_id"),
            trvals_id = mainItemJson.getInt("trvals_id"),
            name = mainItemJson.getString("name"),
            other1 = mainItemJson.getString("other1")
        )

        // 解析 Travel_item 列表
        val travelItemsJsonArray = jsonObject.getJSONArray("travel_items")
        val travelItems = mutableListOf<Travel_item>()
        for (i in 0 until travelItemsJsonArray.length()) {
            val travelItemJson = travelItemsJsonArray.getJSONObject(i)
            val travelItem = Travel_item(
                trval_id = travelItemJson.getInt("trval_id"),
                main_travel_id = travelItemJson.getInt("main_travel_id"),
                model = travelItemJson.getInt("model"),
                Detail_travel = travelItemJson.getInt("Detail_travel"),
                trval_name = travelItemJson.getString("trval_name"),
                time = travelItemJson.getString("time"),
                abbreviate_thing = travelItemJson.getString("abbreviate_thing"),
                detailthings = travelItemJson.getString("detailthings"),
                travel_location = travelItemJson.getString("travel_location")
            )
            travelItems.add(travelItem)
        }

        // 解析 Weather_item 列表
        val weatherItemsJsonArray = jsonObject.getJSONArray("weather_items")
        val weatherItems = mutableListOf<Weather_item>()
        for (i in 0 until weatherItemsJsonArray.length()) {
            val weatherItemJson = weatherItemsJsonArray.getJSONObject(i)
            val weatherItem = Weather_item(
                weather_id = weatherItemJson.getInt("weather_id"),
                main_weather_id = weatherItemJson.getInt("main_weather_id"),
                number_day = weatherItemJson.getInt("number_day"),
                where = weatherItemJson.getString("where"),
                date = weatherItemJson.getString("date"),
                week = weatherItemJson.getString("week"),
                day_weather = weatherItemJson.getString("day_weather"),
                dat_temp = weatherItemJson.getInt("dat_temp"),
                night_weather = weatherItemJson.getString("night_weather"),
                night_temp = weatherItemJson.getInt("night_temp"),
                attention = weatherItemJson.getString("attention")
            )
            weatherItems.add(weatherItem)
        }

        return Triple(mainItem, travelItems, weatherItems)
    }
}


