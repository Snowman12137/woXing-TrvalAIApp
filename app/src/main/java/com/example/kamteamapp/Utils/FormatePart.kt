package com.example.kamteamapp.Utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.kamteamapp.base.prase.TravelData
import com.example.kamteamapp.data.Between
import com.example.kamteamapp.data.Detail_Trval_Data
import com.example.kamteamapp.data.New_Temp_Trval_Items
import com.example.kamteamapp.data.Temp_Weather_Items
import com.example.kamteamapp.data.Time
import com.example.kamteamapp.data.Travels_Data
import com.example.kamteamapp.data.Weather
import com.example.kamteamapp.ui.item.DisplayItem
import com.example.kamteamapp.ui.item.combineItems
import com.google.gson.Gson
import java.time.LocalDate

class TransPartToDisplay(){

    fun getString(jsonString: String): TravelData {
        val gson = Gson()
        return gson.fromJson(jsonString, TravelData::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDisPlay(tars:TravelData): List<DisplayItem>{
        val itemsWeathers = tars.weather.map { item->
            Temp_Weather_Items(
                item.number_day,
                item.number_day,
                item.where,
                formatData(item.data),
                formatData(item.data).dayOfWeek,
                Weather.OVER_CAST,
                item.day_temp.toInt(),
                Weather.OVER_CAST,
                item.night_temp.toInt(),
                attention = item.attention
            )
        }
        val itemsTrvals = tars.trval.mapNotNull { item ->
            when {
                item.model == 0 && item.detailThings != null -> New_Temp_Trval_Items(
                    trval_id = item.trval_id,
                    model = item.model,
                    day = item.day,
                    Detail_trval = 1,
                    trval_name = item.trval_name,
                    formatTime(item.time),
                    abbreviate_thing = item.abbreviate_thing,
                    detailThings = item.detailThings.map { item2 ->
                        Detail_Trval_Data(
                            item2.detail_id,
                            item2.others,
                            formatTime(item2.times),
                            item2.my_content
                        )
                    },
                    Travels_Data() // Assuming this is the default or placeholder
                )
                item.model == 1 && item.Travels_data != null -> New_Temp_Trval_Items(
                    trval_id = item.trval_id,
                    model = item.model,
                    day = item.day,
                    Detail_trval = 1,
                    trval_name = item.trval_name,
                    formatTime(item.time),
                    abbreviate_thing = item.abbreviate_thing,
                    detailThings = listOf(), // Empty list if no details
                    Travels_Data(
                        item.Travels_data[0].start_star,
                        item.Travels_data[0].end_star,
                        item.Travels_data[0].other
                    )
                )
                else -> null // Return null for items that don't match any condition
            }
        }

        return combineItems(itemsTrvals,itemsWeathers)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatData(input:String):LocalDate{
        val parts = input.split("-")
        return LocalDate.of(parts[0].toInt(),parts[1].toInt(),parts[2].toInt())
    }

    private fun formatTime(input:String):Between{
        val parts = input.split("-")

        if (parts.size > 1) {
            // 返回第二个元素
            val res1 = parts[0].split(":")
            val res2 = parts[1].split(":")
            return Between(
                Time(
                    res1[0].toInt(),
                    res1[1].toInt()
                ),
                Time(
                    res2[0].toInt(),
                    res2[1].toInt()
                )
            )
        } else {
            // 如果没有足够的元素，返回 null
            val res = parts[0].split(":")
            return Between(
                Time(
                    res[0].toInt(),
                    res[1].toInt()
                )
            )
        }
    }

}
