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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
data class MyUiState(
    var travelitem: Travelitems = Travelitems(0, 0, ""),
    var messagechat: List<Messagechat> = ArrayList(),
    var mainitembyid: Mainitems = Mainitems(0, "", "", "", "", 0, 0),
    var allmain_data: List<Mainitems> = ArrayList(),
    var test_data: List<DisplayItem> = ArrayList(),
    var updateTime: Long = 0
)




@RequiresApi(Build.VERSION_CODES.O)
class MyViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(MyUiState())
    val uiState: StateFlow<MyUiState> = _uiState.asStateFlow()

    init {
//插入测试
        viewModelScope.launch {

            //解析数据
            val temp = TransPartToDisplay()
            val data = temp.getString(Data_my)

            //定义插入的数据
            val mainitem = convertdata(data)
            val messagetest = Messagechat(1, mainitem.message_id, "这是测试消息")
            val traveltest = Travelitems(1, mainitem.travel_id, Data_my)

            //插入数据
            insertmainitem(mainitem)
            insertmessagechat(mainitem.message_id, "这是测试消息")
            inserttravelitems(mainitem.travel_id, Data_my)
        }

        viewModelScope.launch {
            //获取所有的mainitems
            getallmainitems()
            //获取mainitems by id
            getmainitembyid(1)
            //获取所有的messagechat by id
            getallmessagechat(1)
            //获取所有的travelitems by id
            gettravelitem(1)
        }


    }

    fun getallmessagechat(maintochat: Int){
        viewModelScope.launch {
            DataHelper.get_allmessagechat(maintochat).collect { messagechat ->
                _uiState.update { state ->
                    state.messagechat = messagechat
//                    Log.d("test1", "getallmessagechat: ${messagechat.toString()}")
                    state.copy(updateTime = System.nanoTime())
                }
            }
        }
    }

    fun gettravelitem(maintotravel: Int){
        viewModelScope.launch {
            DataHelper.get_travelitems(maintotravel).collect { travelitems ->
                _uiState.update { state ->
                    state.travelitem = travelitems
                    val temp = TransPartToDisplay()
                    state.test_data = temp.getDisPlay(temp.getString(travelitems!!.tr))

//                    Log.d("test2", "gettravelitem: ${travelitems.toString()}")
                    state.copy(updateTime = System.nanoTime())
                }
            }
        }
    }


    fun getmainitembyid(id: Int){
        viewModelScope.launch {
            DataHelper.getmainitembyid(id).collect { mainitem ->
                _uiState.update { state ->
                    state.mainitembyid= mainitem
//                    Log.d("test3", "getmainitembyid: ${mainitem.toString()}")
                    state.copy(updateTime = System.nanoTime())
                }
            }
        }
    }

    fun getallmainitems(){
        viewModelScope.launch {
            DataHelper.getallmainitems().collect { mainitems ->
                _uiState.update { state ->
                    state.allmain_data = mainitems
//                    Log.d("test4", "getallmainitems: ${mainitems.toString()}")
                    state.copy(updateTime = System.nanoTime())
                }
            }
        }
    }


    fun insertmainitem(mainitems: Mainitems) {
        viewModelScope.launch {
            DataHelper.insertmainitem(mainitems)
        }
    }

    fun insertmessagechat(mainidmessage:Int,message: String) {
        val messagechat = Messagechat(1, mainidmessage, message)
        viewModelScope.launch {
            DataHelper.insertmessagechat(messagechat)
        }
    }

    fun inserttravelitems(mainidtravel: Int,travel: String) {
        val travelitems = Travelitems(1, mainidtravel, travel)
        viewModelScope.launch {
            DataHelper.inserttravelitems(travelitems)
        }
    }

    fun convertdata(data: TravelData): Mainitems {
        val id = 1
        val time_start = data.main.time_start
        val travelday = data.main.trval_day
        val travelname = data.main.name
        val other1 = "other1"
        val messageid = id
        val travelid = id
        return Mainitems(id, time_start, travelday, travelname, other1, messageid, travelid)

    }




}

//    fun gettravelbyid(mainitems: Mainitems){
//        viewModelScope.launch {
//            DataHelper.get_travel(mainitems).collect { travelitems ->
//                _uiState.update { state ->
//                    state.travelitem = travelitems
//                    state.copy(updateTime = System.nanoTime())
//                }
//            }
//        }
//    }
//
//    fun getchatbyid(mainitems: Mainitems){
//        viewModelScope.launch {
//            DataHelper.get_chat(mainitems).collect { messagechat ->
//                _uiState.update { state ->
//                    state.messagechat = messagechat
//                    state.copy(updateTime = System.nanoTime())
//                }
//            }
//        }
//    }



//viewModelScope.launch {
//    DataHelper.getmainitembyid(1).collect { mainitem ->
//        Log.d("MAINtest", "mainitem is ${mainitem.toString()}")
//        DataHelper.get_allmessagechat(mainitem.message_id).collect { messagechat ->
//            Log.d("MESSAGEtest", "messagechat is ${messagechat.toString()}")
//        }
//        DataHelper.get_travelitems(mainitem.travel_id).collect { travelitems ->
//            Log.d("TRAVELtest", "travelitems is  ${travelitems.toString()}")
//        }
//    }
//}

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