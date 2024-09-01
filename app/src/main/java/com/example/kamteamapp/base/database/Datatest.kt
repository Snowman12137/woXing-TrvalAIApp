package com.example.kamteamapp.base.database

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import com.example.myapplication.Roomfinal.detailItem1
import com.example.myapplication.Roomfinal.detailItem2
import com.example.myapplication.Roomfinal.detailItem3
import com.example.myapplication.Roomfinal.mainItem
import com.example.myapplication.Roomfinal.travelItem1
import com.example.myapplication.Roomfinal.travelItem2
import com.example.myapplication.Roomfinal.travelItem3
import com.example.myapplication.Roomfinal.weatherItem1
import com.example.myapplication.Roomfinal.weatherItem2
import com.example.myapplication.Roomfinal.weatherItem3


class Datatest : ComponentActivity() {
    private val itemViewModel: TravelViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KamTeamAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    screen(travelViewModel = itemViewModel)
                }
            }

        }
    }
}


@Composable
fun screen(travelViewModel: TravelViewModel) {
    val allMainItems by travelViewModel.allMainItems.collectAsState(initial = emptyList())
    val mainWithTravelItems by travelViewModel.mainWithTravelItems.collectAsState(initial = emptyList())
    val mainWithWeatherItems by travelViewModel.mainWithWeatherItems.collectAsState(initial = emptyList())
    val travelwithdetails by travelViewModel.travelwithdetails.collectAsState(initial = emptyList())
    val mainitemneed by travelViewModel.findmainitem(1).collectAsState(initial = null)
    val mainwithtravelitemneed by travelViewModel.findmainwithtravel(1).collectAsState(initial = emptyList())
    val mainwithweatheritemneed by travelViewModel.findmainwithweather(1).collectAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //显示mainitemneed

        item {
            Text(text = "旅游主信息表 id为1：")
            Spacer(modifier = Modifier.height(16.dp))
            mainitemneed?.let {
                Text(text = "主信息表的id为${it.id},起始时间为${it.time_start},旅游天数为${it.trval_day},天气信息主键为${it.weathers_id},旅游事件主键为${it.trvals_id},事件名称为${it.name},其他简略信息为${it.other1}")
            }
        }


        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "旅游事件表信息：主表id为1")
            mainwithtravelitemneed.forEach { mainWithTravelItem ->
                mainWithTravelItem.travelItems.forEach { travelItem ->
                    Text(text = "旅游事件对应的主表id为${travelItem.main_travel_id},详细事件为${travelItem.abbreviate_thing}")
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "旅游天气表信息：主表id为1")
            mainwithweatheritemneed.forEach { mainWithWeatherItem ->
                mainWithWeatherItem.weatherItems.forEach { weatherItem ->
                    Text(text = "天气对应的主表id为${weatherItem.main_weather_id},天气信息为${weatherItem.day_weather}")
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    travelViewModel.insertmainitem(mainItem)
                    travelViewModel.inserttravelitem(travelItem1)
                    travelViewModel.inserttravelitem(travelItem2)
                    travelViewModel.inserttravelitem(travelItem3)
                    travelViewModel.insertweatheritem(weatherItem1)
                    travelViewModel.insertweatheritem(weatherItem2)
                    travelViewModel.insertweatheritem(weatherItem3)
                    travelViewModel.insertdetailthingsitem(detailItem1)
                    travelViewModel.insertdetailthingsitem(detailItem2)
                    travelViewModel.insertdetailthingsitem(detailItem3)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Insert Data")
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    travelViewModel.deleteallmainitems()
                    travelViewModel.deletealltravelitems()
                    travelViewModel.deleteallweatheritems()
                    travelViewModel.deletealldetailthingsitems()
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Delete Data")
            }
        }
    }
}