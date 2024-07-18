package com.example.kamteamapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
val TempRes = listOf(
    Temp_Trval_Items(
        1,
        0,
        1,
        1,
        "西安->广州",
        LocalDate.of(2024,7,7),
        0,
        Between(
            Time(
                0,
                30
            )
        ),
        "行李放到酒店，下一行程->打车",
        listOf(
            Money_Data(
                "飞机票",
                890f,
                "2"
            ), Money_Data(
                "保险",
                10f,
                "2"
            ), Money_Data(
                "额外托运",
                100f,
                "1"
            ), Money_Data(
                "机场小吃",
                50f,
                "0"
            )
        )
    ),
    Temp_Trval_Items(
        2,
        1,
        1,
        2,
        "圣心大教堂-爱群大厦",
        LocalDate.of(2024,7,7),
        2,
        Between(
            Time(8,),
            Time(12)
        ),
        "欧式建筑打卡拍照"
    ),
    Temp_Trval_Items(
        3,
        1,
        1,
        1,
        "粤海关博物馆",
        LocalDate.of(2024,7,7),
        0,
        Between(
            Time(12, 30),
            Time(13,40)
        ),
        "广州海关发展史"
    ),
    Temp_Trval_Items(
        4,
        2,
        1,
        1,
        "午餐银灯食府",
        LocalDate.of(2024,7,7),
        0,
        Between(
            Time(14),
            Time(15)
        ),
        "14：00以后打8折"
    ),
    Temp_Trval_Items(
        5,
        1,
        2,
        1,
        "恋爱港-大三巴-疯堂斜港",
        LocalDate.of(2024,7,8),
        0,
        Between(
            Time(11),
            Time(14)
        ),
        "特色手信：永吉街——柠檬王(大三巴附近) /时香花生——话梅皇(大三巴附近)"
    ),Temp_Trval_Items(
        6,
        1,
        2,
        1,
        "疯狂斜港-渔人码头-永利皇宫",
        LocalDate.of(2024,7,8),
        0,
        Between(
            Time(15),
            Time(18)
        ),
        "免费的甜品和奶茶可以拿 / 安德鲁蛋挞"
    ),Temp_Trval_Items(
        7,
        1,
        2,
        1,
        "伦敦人-巴黎人-威尼斯人-银河酒店",
        LocalDate.of(2024,7,8),
        0,
        Between(
            Time(19),
            Time(21,40)
        ),
        "发财车最后一班22：30\n 泰妈船面(官也街附近)，泽贤鸡蛋仔，百乐肉粉面，金利食堂 \n 特色手信:最香饼家--杏仁饼/喜临门--虾仔面"
    ),Temp_Trval_Items(
        8,
        1,
        1,
        1,
        "沙面岛--永兴坊",
        LocalDate.of(2024,7,7),
        0,
        Between(
            Time(15,30),
            Time(19)
        ),
        "需要提前两天预约"
    ),Temp_Trval_Items(
        9,
        2,
        1,
        1,
        "晚餐:大鸽饭",
        LocalDate.of(2024,7,7),
        0,
        Between(
            Time(19),
            Time(18,30)
        ),
        "其他:有囍糖水，银记肠粉"
    ),Temp_Trval_Items(
        10,
        1,
        1,
        1,
        "海心桥广州塔",
        LocalDate.of(2024,7,7),
        0,
        Between(
            Time(20),
            Time(22)
        ),
        "打卡夜景，Citywalk"
    ),Temp_Trval_Items(
        11,
        1,
        3,
        1,
        "旺角-油麻地-尖沙咀",
        LocalDate.of(2024,7,9),
        0,
        Between(
            Time(9,30),
            Time(13)
        ),
        "推荐美食:推荐美食: 旺角——两草(张智霖打卡) 肥姐小食店，吃茶三千招牌国王奶茶 尖沙咀——华嫂冰室/红茶冰室，富豪雪糕车 13/个只收现金!!!!，bakehouse蛋挞 12/个"
    ),Temp_Trval_Items(
        12,
        1,
        3,
        1,
        "星光大道-天星小轮",
        LocalDate.of(2024,7,9),
        0,
        Between(
            Time(14),
            Time(16)
        ),
        "拍照！！！"
    ),Temp_Trval_Items(
        13,
        2,
        3,
        1,
        "湾仔码头-铜锣湾",
        LocalDate.of(2024,7,9),
        0,
        Between(
            Time(17),
            Time(20)
        ),
        "铜锣湾——荣记粉面(陈奕迅推荐，只收现 金!!!!) jollibee炸鸡(小贵)"
    ),Temp_Trval_Items(
        14,
        1,
        3,
        1,
        "中环摩天轮-维多利亚港夜景",
        LocalDate.of(2024,7,9),
        0,
        Between(
            Time(20,30),
            Time(22,30)
        ),
        "摩天轮：20/人 + 拍视频 \uD83C\uDF81 特产手信:珍妮小熊——尖沙咀 711——巨峰乌龙、 兆成行——香薰店，收现金(张曼玉，舒淇 同款) 大生超市——各种巧克力 药品——万宁 护照夹——Petite petite"
    ),Temp_Trval_Items(
        15,
        1,
        4,
        1,
        "坚尼地域-西环泳棚",
        LocalDate.of(2024,7,10),
        0,
        Between(
            Time(8,11),
            Time(11)
        ),
        "坚尼地城和西环泳棚打卡点 地铁坚尼地城C 口出 \uD83D\uDE0B 推荐美食:坚尼地城——诚记"
    ),Temp_Trval_Items(
        16,
        1,
        4,
        1,
        "半山扶梯-平顶山(步行25分钟左右)",
        LocalDate.of(2024,7,10),
        0,
        Between(
            Time(11,40),
            Time(15)
        ),
        "缆车来回88，时间充裕的话还可以在维港拍 成功人士打卡照\n半山扶梯——泰奶，奶绿好喝"
    ),Temp_Trval_Items(
        17,
        2,
        4,
        1,
        "香港->深圳(过海关一个小时)",
        LocalDate.of(2024,7,10),
        0,
        Between(
            Time(18),
            Time(19,30)
        ),
        "XXXX"
    ),Temp_Trval_Items(
        18,
        0,
        4,
        1,
        "住宿",
        LocalDate.of(2024,7,10),
        0,
        Between(
            Time(18),
            Time(19,30)
        ),
        "XXXX"
    ),
)


@RequiresApi(Build.VERSION_CODES.O)
val temdata1 : LocalDate = LocalDate.of(2024,7,9)
@RequiresApi(Build.VERSION_CODES.O)
val temdata2 : LocalDate = LocalDate.of(2024,7,10)
@RequiresApi(Build.VERSION_CODES.O)
val temdata3 : LocalDate = LocalDate.of(2024,7,11)
@RequiresApi(Build.VERSION_CODES.O)
val temdata4 : LocalDate = LocalDate.of(2024,7,12)
@RequiresApi(Build.VERSION_CODES.O)
val temdata5 : LocalDate = LocalDate.of(2024,7,13)
@RequiresApi(Build.VERSION_CODES.O)
val TempWeath = listOf(
    Temp_Weather_Items(
        weather_id = 1,
        number_day = 1,
        where = "广州",
        data = temdata1,
        week = temdata1.dayOfWeek,
        day_weather = Weather.SUNNY_DAY,
        dat_temp = 25,
        night_weather = Weather.OVER_CAST,
        night_temp = 20,
        attention = "注意：夜晚较凉注意增添衣物"
    ),Temp_Weather_Items(
        weather_id = 2,
        number_day = 2,
        where = "澳门",
        data = temdata2,
        week = temdata2.dayOfWeek,
        day_weather = Weather.CLOUDY_DAY,
        dat_temp = 25,
        night_weather = Weather.HEAVY_RAIN,
        night_temp = 20,
        attention = "注意：夜晚较凉注意增添衣物"
    ),Temp_Weather_Items(
        weather_id = 3,
        number_day = 3,
        where = "澳门",
        data = temdata3,
        week = temdata3.dayOfWeek,
        day_weather = Weather.OVER_CAST,
        dat_temp = 25,
        night_weather = Weather.MODERATE_RAIN,
        night_temp = 20,
        attention = "注意：夜晚较凉注意增添衣物"
    ),Temp_Weather_Items(
        weather_id = 4,
        number_day = 4,
        where = "澳门",
        data = temdata4,
        week = temdata4.dayOfWeek,
        day_weather = Weather.SMALL_RAIN,
        dat_temp = 25,
        night_weather = Weather.OVER_CAST,
        night_temp = 20,
        attention = "注意：夜晚较凉注意增添衣物"
    ),Temp_Weather_Items(
        weather_id = 5,
        number_day = 5,
        where = "澳门",
        data = temdata4,
        week = temdata4.dayOfWeek,
        day_weather = Weather.HEAVY_RAIN,
        dat_temp = 25,
        night_weather = Weather.OVER_CAST,
        night_temp = 20,
        attention = "注意：夜晚较凉注意增添衣物"
    )
)
