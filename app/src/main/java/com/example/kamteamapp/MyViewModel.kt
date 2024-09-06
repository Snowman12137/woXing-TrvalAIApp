package com.example.kamteamapp

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kamteamapp.Utils.TransPartToDisplay
import com.example.kamteamapp.base.databasefinal.CardorImage
import com.example.kamteamapp.base.databasefinal.DataHelper
import com.example.kamteamapp.base.databasefinal.Mainitems
import com.example.kamteamapp.base.databasefinal.Messagechat
import com.example.kamteamapp.base.databasefinal.Travelitems
import com.example.kamteamapp.base.prase.TravelData
import com.example.kamteamapp.data.Data_my
import com.example.kamteamapp.data.Post
import com.example.kamteamapp.ui.chat.Message
import com.example.kamteamapp.ui.item.DisplayItem
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import kotlin.random.Random


//class Conversation(
//    val channelName: String,
//    initialMessages: List<Messagechat>
//) {
//    private val _messages: MutableLiveData<List<Messagechat>> = MutableLiveData(initialMessages)
//    val messages: LiveData<List<Messagechat>> = _messages
//
//    fun addMessage(msg: Messagechat) {
//        val newMessages = _messages.value?.toMutableList() ?: mutableListOf()
//        newMessages.add(0, msg)
//        _messages.value = newMessages
//    }
//
//    fun set
//
//
//}



data class MyUiState(
    var mainid_namenull:List<Int> = ArrayList(),
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
    private var isInitialized = false
    //val conversation = Conversation("General", listOf())



    // http 部分
    private var lastTime: Int? = null // 保存上一次的时间值
    private lateinit var client: OkHttpClient
    private val _post = MutableStateFlow<Post?>(null)
    val post: StateFlow<Post?> = _post

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _conversationHistory = MutableStateFlow<List<Messagechat>>(emptyList())
    val conversationHistory: StateFlow<List<Messagechat>> = _conversationHistory



    init {
//插入测试
        viewModelScope.launch {
            deleteallmainitem()
            deleteallmessagechat()
            deletealltravelitem()
//            //解析数据
            val temp = TransPartToDisplay()
            val data = temp.getString(Data_my)
//            //定义插入的数据
            val mainitem = convertdata(data)
            //插入主要信息
            insertmainitem(mainitem)

            //插入对话消息
            insertmessagechat("me", mainitem.message_id, "这是测试消息", "2021-10-10")
//
////            插入旅游信息
            inserttravelitems(mainitem.travel_id, Data_my)

            createNewData()
            createNewData()
            createNewData()


            // http

            client = OkHttpClient.Builder()
                .build()


        }

        viewModelScope.launch {
            //获取所有的mainitems
            getallmainitems()

            getmainidbynamenull()

//            //获取mainitems by id
//
////            getmainitembyid(1)
//
//            //获取所有的messagechat by id
//            getallmessagechat(1)
//
//            //获取所有的travelitems by id
//            gettravelitem(1)
        }
    }


    fun updateMainItemName(id: Int) {
        viewModelScope.launch {
            DataHelper.updatemainitembyid(id)
        }
    }


    fun getmainidbynamenull() {
        viewModelScope.launch {
            DataHelper.findmainitembynullname().collect { mianids ->
                _uiState.update { state ->
                    state.mainid_namenull = mianids
                    Log.d("miantestid", "getmainidbynamenull: $mianids")
                    state.copy(updateTime = System.nanoTime())
                }
            }
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
                    state.test_data = temp.getDisPlay(temp.getString(travelitems.tr))

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
                    Log.d("test3", "getmainitembyid: ${mainitem.toString()}")
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
   fun deleteallmainitem(){
       viewModelScope.launch {
           DataHelper.deleteallmainitems()
       }
   }

    fun deleteallmessagechat(){
        viewModelScope.launch {
            DataHelper.deleteallmessagechat()
        }
    }
    fun deletealltravelitem(){
        viewModelScope.launch {
            DataHelper.deletealltravelitems()
        }
    }

    fun insertmainitem(mainitems: Mainitems) {
        viewModelScope.launch {
            DataHelper.insertmainitem(mainitems)
        }
    }

    fun insertmessagechat(author:String,maintochatid: Int, message: String,timestamp:String,cardorimage:CardorImage?=null) {

        val messagechat = Messagechat(null,author, maintochatid, message, timestamp)
        viewModelScope.launch {
            DataHelper.insertmessagechat(messagechat)
        }
    }

    fun inserttravelitems(mainidtravel: Int,travel: String) {
        val travelitems = Travelitems(null,mainidtravel, travel)
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

    fun createNewData(){
        val ids = Random.nextInt(Int.MAX_VALUE)
        val message_id = Random.nextInt(Int.MAX_VALUE)
        val travel_id = Random.nextInt(Int.MAX_VALUE)

        insertmainitem(
            mainitems= Mainitems(
                id  = ids,
                time_start = "",
                trval_day = "0",
                name = "无",
                other1 = "",
                message_id =message_id,
                travel_id = travel_id
            )
        )

        insertmessagechat(
            "else",message_id,"请输入你的问题","7:30PM"
        )
        inserttravelitems(
            travel_id,
            ""
        )
    }


    fun fetchPost(userMessage: String,id: Int,trvalId:Int,index_main_id:Int,) {
        _isLoading.value = true
        _errorMessage.value = null

        // 添加用户消息到 conversationHistory
        val userMessageObj = Messagechat(null,"me",id, userMessage, "现在")
        insertmessagechat(author = "me",id,userMessage,"现在")
        _conversationHistory.value = _conversationHistory.value + userMessageObj

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataToSend = _conversationHistory.value.joinToString("\n") { it.message }
                val requestBuilder = Request.Builder()
                    .url("http://39.100.70.79:443/AI_chat?code=1001&status=200&time=${lastTime ?: "1"}&data=$dataToSend")

                val request = requestBuilder.build()

                try {
                    val response = client.newCall(request).execute()
                    if (response.isSuccessful) {
                        response.body?.string()?.let { responseBody ->
                            val post = Gson().fromJson(responseBody, Post::class.java)
                            _post.value = post

                            // 检查 code 是否为 1002
                            if (post.code == 1002) {
                                // 发送第二个请求
                                val secondRequest = Request.Builder()
                                    .url("http://39.100.70.79:443/return_json?code=1001&status=200&time=4&data=${post.data}")
                                    .build()

                                val secondResponse = client.newCall(secondRequest).execute()
                                if (secondResponse.isSuccessful) {
                                    secondResponse.body?.string()?.let { secondResponseBody ->
                                        // 将返回的 JSON 作为服务端消息添加到聊天记录
                                        val serverMessageObj = Messagechat(null,"服务端",id, secondResponseBody, "现在")
                                        //insertmessagechat(author = "服务端",id,secondResponseBody,"现在")
                                        // insertresult
                                        insertresult(secondResponseBody)
                                        _conversationHistory.value = _conversationHistory.value + serverMessageObj
                                    }
                                } else {
                                    _errorMessage.value = "第二次请求失败，错误码：${secondResponse.code}"
                                }
                            } else {
                                post.data?.let { message ->
                                    // 更新 conversationHistory
                                    val serverMessageObj = Messagechat(null,"服务端", id,message, "现在")
                                    insertmessagechat(author = "服务端",id,message,"现在")
                                    _conversationHistory.value = _conversationHistory.value + serverMessageObj
                                }
                            }

                            // 更新 lastTime
                            lastTime = post.time
                        } ?: run {
                            _errorMessage.value = "响应体为空"
                        }
                    } else {
                        _errorMessage.value = "请求失败，错误码：${response.code}"
                    }
                } catch (e: IOException) {
                    _errorMessage.value = "网络请求失败：${e.message}"
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }

    private fun insertresult(result:String){
        val temp = TransPartToDisplay()
        val res = temp.getMain(result)


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