package com.example.kamteamapp.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kamteamapp.data.Post
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import kotlinx.coroutines.Dispatchers



//class MainViewModel : ViewModel() {
//
//    private val _post = MutableStateFlow<Post?>(null)
//    val post: StateFlow<Post?> = _post
//
//    private val _isLoading = MutableStateFlow(false)
//    val isLoading: StateFlow<Boolean> = _isLoading
//
//    private val _errorMessage = MutableStateFlow<String?>(null)
//    val errorMessage: StateFlow<String?> = _errorMessage
//
//    private val _conversationHistory = MutableStateFlow<List<Message>>(emptyList())
//    val conversationHistory: StateFlow<List<Message>> = _conversationHistory
//
//    private var lastTime: Int? = null // 添加变量保存上一次的时间值
//
//    private val client: OkHttpClient
//
//    init {
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//    }
//
//    fun fetchPost(userMessage: String) {
//        _isLoading.value = true
//        _errorMessage.value = null
//
//        // 添加用户消息到 conversationHistory
//        val userMessageObj = Message("客户端", userMessage, "现在")
//        _conversationHistory.value = _conversationHistory.value + userMessageObj
//
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val dataToSend = _conversationHistory.value.joinToString("\n") { it.content }
//
//                val requestBuilder = Request.Builder()
//                    .url("http://39.100.70.79:443/AI_chat?code=1001&status=200&time=${lastTime ?: "1"}&data=$dataToSend")
//
//                val request = requestBuilder.build()
//
//                try {
//                    val response = client.newCall(request).execute()
//                    if (response.isSuccessful) {
//                        response.body?.string()?.let { responseBody ->
//                            val post = Gson().fromJson(responseBody, Post::class.java)
//                            _post.value = post
//                            post.data?.let { message ->
//                                // 更新 conversationHistory
//                                val serverMessageObj = Message("服务端", message, "现在")
//                                _conversationHistory.value = _conversationHistory.value + serverMessageObj
//
//                                // 更新 lastTime
//                                lastTime = post.time
//                            }
//                        } ?: run {
//                            _errorMessage.value = "响应体为空"
//                        }
//                    } else {
//                        _errorMessage.value = "请求失败，错误码：${response.code}"
//                    }
//                } catch (e: IOException) {
//                    _errorMessage.value = "网络请求失败：${e.message}"
//                } finally {
//                    _isLoading.value = false
//                }
//            }
//        }
//    }
//}

class MainViewModel : ViewModel() {

    private val _post = MutableStateFlow<Post?>(null)
    val post: StateFlow<Post?> = _post

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _conversationHistory = MutableStateFlow<List<Message>>(emptyList())
    val conversationHistory: StateFlow<List<Message>> = _conversationHistory

    private var lastTime: Int? = null // 保存上一次的时间值

    private val client: OkHttpClient

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    fun fetchPost(userMessage: String) {
        _isLoading.value = true
        _errorMessage.value = null

        // 添加用户消息到 conversationHistory
        val userMessageObj = Message("客户端", userMessage, "现在")
        _conversationHistory.value = _conversationHistory.value + userMessageObj

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataToSend = _conversationHistory.value.joinToString("\n") { it.content }

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
                                        val serverMessageObj = Message("服务端", secondResponseBody, "现在")
                                        _conversationHistory.value = _conversationHistory.value + serverMessageObj
                                    }
                                } else {
                                    _errorMessage.value = "第二次请求失败，错误码：${secondResponse.code}"
                                }
                            } else {
                                post.data?.let { message ->
                                    // 更新 conversationHistory
                                    val serverMessageObj = Message("服务端", message, "现在")
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
}



