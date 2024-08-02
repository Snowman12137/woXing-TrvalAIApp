package com.example.kamteamapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
val TempRes2 = listOf(
    Temp_Trval_Items(
        1,
        0,
        1,
        1,
        "武汉->西安",
        LocalDate.of(2024,7,14),
        0,
        Between(
            Time(
                5,
                30
            )
        ),
        "行李放到酒店，下一行程->钟楼",
        listOf(
            Money_Data(
                "高铁",
                450f,
                "2"
            ), Money_Data(
                "保险",
                10f,
                "2"
            ), Money_Data(
                "高铁小吃",
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
        "钟楼",
        LocalDate.of(2024,7,14),
        2,
        Between(
            Time(10,),
            Time(12)
        ),
        "钟楼打卡拍照"
    ),
    Temp_Trval_Items(
        3,
        1,
        1,
        1,
        "回民街品尝美食",
        LocalDate.of(2024,7,14),
        0,
        Between(
            Time(12, 30),
            Time(13, 40)
        ),
        "体验回民街特色美食"
    ),
    Temp_Trval_Items(
        4,
        2,
        1,
        1,
        "大雁塔",
        LocalDate.of(2024,7,14),
        0,
        Between(
            Time(14),
            Time(17)
        ),
        "大雁塔美景拍照"
    ),
    Temp_Trval_Items(
        5,
        1,
        2,
        1,
        "大唐不夜城",
        LocalDate.of(2024,7,14),
        0,
        Between(
            Time(19),
            Time(21)
        ),
        "观赏大眼特南广场音乐喷泉表演"
    ),Temp_Trval_Items(
        6,
        1,
        2,
        1,
        "秦始皇陵兵马俑",
        LocalDate.of(2024,7,15),
        0,
        Between(
            Time(9),
            Time(12)
        ),
        "参观秦始皇陵兵马俑"
    ),Temp_Trval_Items(
        7,
        1,
        2,
        1,
        "品尝陕西小吃",
        LocalDate.of(2024,7,15),
        0,
        Between(
            Time(12,30),
            Time(13,30)
        ),
        "品尝陕西特色美食"
    ),Temp_Trval_Items(
        8,
        1,
        1,
        1,
        "华清池",
        LocalDate.of(2024,7,15),
        0,
        Between(
            Time(14,30),
            Time(17)
        ),
        "参观华清池"
    ),Temp_Trval_Items(
        9,
        2,
        1,
        1,
        "观赏长恨歌演出",
        LocalDate.of(2024,7,15),
        0,
        Between(
            Time(19),
            Time(21)
        ),
        "长恨歌表演"
    ),Temp_Trval_Items(
        10,
        1,
        1,
        1,
        "西安碑林博物馆",
        LocalDate.of(2024,7,16),
        0,
        Between(
            Time(9),
            Time(12)
        ),
        "西安碑林博物馆收藏了大量的古代碑刻和书法作品，是研究中国文化的宝库"
    ),Temp_Trval_Items(
        11,
        1,
        3,
        1,
        "永兴坊",
        LocalDate.of(2024,7,16),
        0,
        Between(
            Time(13),
            Time(17)
        ),
        "永兴坊是西安面食最著名的美食街，各种面食让人垂涎欲滴"
    ),Temp_Trval_Items(
        12,
        1,
        3,
        1,
        "返回武汉",
        LocalDate.of(2024,7,16),
        0,
        Between(
            Time(20),
        ),
        "返程"
    )
)


@RequiresApi(Build.VERSION_CODES.O)
val temdata11 :LocalDate = LocalDate.of(2024,7,14)
@RequiresApi(Build.VERSION_CODES.O)
val temdata22 :LocalDate = LocalDate.of(2024,7,15)
@RequiresApi(Build.VERSION_CODES.O)
val temdata33 :LocalDate = LocalDate.of(2024,7,16)
@RequiresApi(Build.VERSION_CODES.O)
val TempWeath2 = listOf(
    Temp_Weather_Items(
        weather_id = 1,
        number_day = 1,
        where = "西安",
        data = temdata11,
        week = temdata11.dayOfWeek,
        day_weather = Weather.SUNNY_DAY,
        dat_temp = 35,
        night_weather = Weather.SUNNY_DAY,
        night_temp = 28,
        attention = "注意：白天较热"
    ),
    Temp_Weather_Items(
        weather_id = 2,
        number_day = 2,
        where = "西安",
        data = temdata22,
        week = temdata22.dayOfWeek,
        day_weather = Weather.CLOUDY_DAY,
        dat_temp = 25,
        night_weather = Weather.CLOUDY_DAY,
        night_temp = 20,
        attention = "注意：白天较热"
    ),
    Temp_Weather_Items(
        weather_id = 3,
        number_day = 3,
        where = "西安",
        data = temdata33,
        week = temdata33.dayOfWeek,
        day_weather = Weather.HEAVY_RAIN,
        dat_temp = 30,
        night_weather = Weather.HEAVY_RAIN,
        night_temp = 22,
        attention = "注意：白天较热"
    )
)