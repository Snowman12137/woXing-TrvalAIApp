package com.example.kamteamapp.Utils

import android.content.Context



class ScreenSizeManager(private val context: Context) {
    val resources = context.resources
    val displayMetrics = resources.displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val screenHeight = displayMetrics.heightPixels

    val destiyss = resources.displayMetrics.density

    var _res = WideThing()

    init {
        caculate()
    }

    private fun caculate(){
        _res.Weather_background = screenHeight/ 8
        _res.DayLang =  (screenWidth / 1.8f).toInt()
        _res.DayHight = screenHeight / 150
        _res.TimeLang = (screenHeight / 10f).toInt()
        _res.BaseHight = screenHeight / 14
        _res.BasePadding = screenWidth / 100
        _res.screenWidth= screenWidth
        _res.screenHeight= screenHeight


        _res.TimeLangDP = _res.TimeLang / destiyss
        _res.DayLangDP = (_res.DayLang - _res.BasePadding*2) / destiyss

    }

    fun getRes():WideThing{
        return _res
    }

    fun getDestiy():Float{
        return destiyss
    }

}

data class WideThing(
    var Weather_background:Int = 100,
    var TimeLang : Int = 200, //200 // 1小时长度
    var DayLang  : Int = 600, //550 // 1天宽度
    var DayHight : Int = 20,
    var BaseHight : Int = 150,
    var BasePadding: Int = 20,
    var screenWidth: Int =0,
    var screenHeight: Int =0 ,


    var TimeLangDP :Float = 0f,
    var DayLangDP :Float = 0f,
)



// 1080 2154