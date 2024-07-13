package com.example.kamteamapp.ui.item

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.kamteamapp.data.Temp_Weather_Items
import com.example.kamteamapp.data.Weather
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class WeatherViewModel(

):ViewModel(){
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
    val tempWeatherItem1 = Temp_Weather_Items(
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
    )
    @RequiresApi(Build.VERSION_CODES.O)
    val tempWeatherItem2 = Temp_Weather_Items(
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
    )
    @RequiresApi(Build.VERSION_CODES.O)
    val tempWeatherItem3 = Temp_Weather_Items(
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
    )
    @RequiresApi(Build.VERSION_CODES.O)
    val tempWeatherItem4 = Temp_Weather_Items(
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
    )
    @RequiresApi(Build.VERSION_CODES.O)
    val tempWeatherItem5 = Temp_Weather_Items(
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



    private val _weatherUiState = MutableStateFlow(WeatherUiState(itemList = listOf()))
    val weatherUiState: StateFlow<WeatherUiState> = _weatherUiState

    init {
        _weatherUiState.value = WeatherUiState(itemList = listOf(
            tempWeatherItem1,
            tempWeatherItem2,
            tempWeatherItem3,
            tempWeatherItem4,
            tempWeatherItem5
        ))
    }
}


data class WeatherUiState(val itemList: List<Temp_Weather_Items> = listOf())



class TrvalViewModel(

):ViewModel(){

}





