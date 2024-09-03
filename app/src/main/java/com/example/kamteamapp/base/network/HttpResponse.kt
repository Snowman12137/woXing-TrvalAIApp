package com.example.kamteamapp.base.network

open class HttpResponse @JvmOverloads constructor(
    var errorCode: String = "",
    var errorMsg: String = ""
) {
    var time = System.currentTimeMillis()

    fun setRequestTime(reqTime: Long) {
        time -= reqTime
    }
}