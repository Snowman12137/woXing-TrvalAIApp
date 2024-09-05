package com.example.kamteamapp.data

//后端数据格式
data class Post(
    val code: Int,
    val data: String,
    val status: Int,
    val time: Int,
    val message: String? = null // 新增字段以保存服务器返回的消息
)