package com.example.kamteamapp.base.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Roomfinal.Connect
import com.example.myapplication.Roomfinal.Detailthings_item
import com.example.myapplication.Roomfinal.Main_item
import com.example.myapplication.Roomfinal.Travel_item
import com.example.myapplication.Roomfinal.Traveldatabase
import com.example.myapplication.Roomfinal.Weather_item
import com.example.myapplication.Roomfinal.mainwithtravel
import com.example.myapplication.Roomfinal.mainwithweather
import com.example.myapplication.Roomfinal.travelwithdetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TravelViewModel(application: Application):AndroidViewModel(application) {
    private val databasefun: Connect = Traveldatabase.getDatabase(application).databaseDao()
    val allMainItems: Flow<List<Main_item>> = databasefun.getallmainitems()
    val mainWithTravelItems: Flow<List<mainwithtravel>> = databasefun.getMainWithTravelItems()
    val mainWithWeatherItems: Flow<List<mainwithweather>> = databasefun.getMainWithWeatherItems()
    val travelwithdetails: Flow<List<travelwithdetail>> = databasefun.getTravelWithDetailthingsItems()


    fun findmainwithtravel(id: Int): Flow<List<mainwithtravel>> {
        return databasefun.getMainWithTravelItemsbyid(id)
    }

    fun findmainwithweather(id: Int): Flow<List<mainwithweather>> {
        return databasefun.getMainWithWeatherItemsbyid(id)
    }

    fun  findmainitem(id: Int): Flow<Main_item> {
        return databasefun.getmainitem(id)
    }

    fun insertmainitem(item: Main_item) {
        viewModelScope.launch(Dispatchers.IO) {
            databasefun.insertmainitem(item)
        }
    }

    fun inserttravelitem(item: Travel_item) {
        viewModelScope.launch(Dispatchers.IO) {
            databasefun.inserttravelitem(item)
        }
    }

    fun insertweatheritem(item: Weather_item) {
        viewModelScope.launch(Dispatchers.IO) {
            databasefun.insertweatheritem(item)
        }
    }

    fun insertdetailthingsitem(item: Detailthings_item) {
        viewModelScope.launch(Dispatchers.IO) {
            databasefun.insertdetailthingsitem(item)
        }
    }

    fun deleteallmainitems() {
        viewModelScope.launch(Dispatchers.IO) {
            databasefun.deleteAllmainitems()
        }
    }
    fun deletealltravelitems() {
        viewModelScope.launch(Dispatchers.IO) {
            databasefun.deleteAlltravelitems()
        }
    }
    fun deleteallweatheritems() {
        viewModelScope.launch(Dispatchers.IO) {
            databasefun.deleteAllweatheritems()
        }
    }

    fun deletealldetailthingsitems() {
        viewModelScope.launch(Dispatchers.IO) {
            databasefun.deleteAlldetailthingsitems()
        }
    }




}

