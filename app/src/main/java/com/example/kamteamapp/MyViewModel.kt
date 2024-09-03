package com.example.kamteamapp

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kamteamapp.Utils.TransPartToDisplay
import com.example.kamteamapp.base.databasefinal.DataHelper
import com.example.kamteamapp.base.databasefinal.Mainitems
import com.example.kamteamapp.base.databasefinal.Messagechat
import com.example.kamteamapp.base.databasefinal.TravelDatabase
import com.example.kamteamapp.base.databasefinal.Travelitems
import com.example.kamteamapp.base.prase.TravelData
import com.example.kamteamapp.data.Data_my
import com.example.kamteamapp.ui.item.DisplayItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
data class MyUiState(

    var main_data: List<Mainitems> = ArrayList(),
    var test_data: List<DisplayItem> = ArrayList(),
    var updateTime: Long = 0
)




@RequiresApi(Build.VERSION_CODES.O)
class MyViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(MyUiState())
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()
    init {
//插入测试
        viewModelScope.launch {
            val temp = TransPartToDisplay()
            val data = temp.getString(Data_my)
            val mainitem = convertdata(data)
            val messagetest = Messagechat(1,mainitem.message_id,"这是测试消息")
            val traveltest = Travelitems(1,mainitem.travel_id, Data_my)
            DataHelper.insertmessagechat(messagetest)
            DataHelper.insertmainitem(mainitem)
            DataHelper.inserttravelitems(traveltest)
        }

        viewModelScope.launch {
            DataHelper.getallmainitems().collect { mainitems ->
                _uiState.update { state ->
                    state.main_data = mainitems
                    state.copy(updateTime = System.nanoTime())
                }
            }


        }
        viewModelScope.launch {
            DataHelper.getmainitembyid(1).collect { mainitem ->
                Log.d("MAINtest", "mainitem is ${mainitem.toString()}")
                val messagetest = DataHelper.get_chat(mainitem)
                Log.d("MESSAGEtest", "messagetest is ${messagetest.toString()}")
                val traveltest = DataHelper.get_travel(mainitem)
                Log.d("TRAVELtest", "traveltest is  ${traveltest.toString()}")
                }
        }
    }
}




fun convertdata(data:TravelData): Mainitems{
    val id = 1
    val time_start = data.main.time_start
    val travelday = data.main.trval_day
    val travelname = data.main.name
    val other1 =  "other1"
    val messageid = id
    val travelid = id
    return Mainitems(id, time_start, travelday, travelname, other1, messageid, travelid)

}


    //async { WanHelper.insertmessage(22, Data_my) }
//            val test_data = async{WanHelper.getmessage(1)}
//            _uiState.update { state ->
//                test_data.await().let {
//                    val temp = TransPartToDisplay()
//
//
//                    state.test_data = temp.getDisPlay(temp.getString(it!!.value))
//                }
//                state.copy(updateTime = System.nanoTime())
//            }




//    fun add(){
//        viewModelScope.launch{
//            WanHelper.insertmessage(22, Data_my)
//        }
//    }
//
//    fun find(){
//        val test_datas = WanHelper.getmessage(22)
//        _uiState.update {
//            val temp = TransPartToDisplay()
//            it.copy(test_data = temp.getDisPlay(temp.getString(test_datas)))
//        }
//    }



//InsertMessageItem(message = data, id = 23)
//val messageItem = getMessageItemById( 2)
//val result = messageItem?.let { parseTravelData(it.message) }
//val a = result?.main
//val b = result?.weather
//val c = result?.trval