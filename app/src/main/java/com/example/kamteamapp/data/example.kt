package com.example.myapplication.Roomfinal

import com.example.kamteamapp.base.database.Detailthings_item
import com.example.kamteamapp.base.database.Main_item
import com.example.kamteamapp.base.database.Travel_item
import com.example.kamteamapp.base.database.Weather_item

class example {
}

// 创建一个旅游主信息实例
val mainItem = Main_item(
    id = 1,
    time_start = "2023-10-01",
    trval_day = 5,
    weathers_id = 1,
    trvals_id = 1,
    name = "西安旅游",
    other1 = "简略信息"
)

// 创建三个旅游事件表实例
val travelItem1 = Travel_item(
    trval_id = 1,
    main_travel_id = 1,
    model = 1,
    Detail_travel = 1,
    trval_name = "大雁塔(yes)",
    time = "上午",
    abbreviate_thing = "参观大雁塔",
    detailthings = "详细描述大雁塔",
    travel_location = "西安"
)

val travelItem2 = Travel_item(
    trval_id = 2,
    main_travel_id = 2,
    model = 1,
    Detail_travel = 2,
    trval_name = "钟楼",
    time = "下午",
    abbreviate_thing = "参观钟楼",
    detailthings = "详细描述钟楼",
    travel_location = "西安"
)

val travelItem3 = Travel_item(
    trval_id = 3,
    main_travel_id = 1,
    model = 1,
    Detail_travel = 3,
    trval_name = "回民街(yes)",
    time = "晚上",
    abbreviate_thing = "品尝美食",
    detailthings = "详细描述回民街",
    travel_location = "西安"
)

// 创建三个天气表实例
val weatherItem1 = Weather_item(
    weather_id = 1,
    main_weather_id = 1,
    number_day = 1,
    where = "西安",
    date = "2023-10-01",
    week = "星期一",
    day_weather = "晴",
    dat_temp = 25,
    night_weather = "多云(yes)",
    night_temp = 18,
    attention = "注意防晒"
)

val weatherItem2 = Weather_item(
    weather_id = 2,
    main_weather_id = 2,
    number_day = 2,
    where = "西安",
    date = "2023-10-02",
    week = "星期二",
    day_weather = "阴",
    dat_temp = 22,
    night_weather = "小雨",
    night_temp = 17,
    attention = "带伞"
)

val weatherItem3 = Weather_item(
    weather_id = 3,
    main_weather_id = 1,
    number_day = 3,
    where = "西安",
    date = "2023-10-03",
    week = "星期三",
    day_weather = "晴",
    dat_temp = 26,
    night_weather = "晴(yes)",
    night_temp = 19,
    attention = "适合出行"
)

// 创建三个详细事件表实例
val detailItem1 = Detailthings_item(
    detail_id = 1,
    travel_detail_id = 1,
    others = "其他信息1",
    times = "上午",
    my_content = "详细描述大雁塔"
)

val detailItem2 = Detailthings_item(
    detail_id = 2,
    travel_detail_id = 2,
    others = "其他信息2",
    times = "下午",
    my_content = "详细描述钟楼"
)

val detailItem3 = Detailthings_item(
    detail_id = 3,
    travel_detail_id = 3,
    others = "其他信息3",
    times = "晚上",
    my_content = "详细描述回民街"
)