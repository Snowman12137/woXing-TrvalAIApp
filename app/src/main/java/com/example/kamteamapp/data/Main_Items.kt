package com.example.kamteamapp.data

import android.provider.ContactsContract.Data
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "main_items")
data class Main_Items(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0 ,  // 主键
    val time_start: LocalDate,  // 起始时间
    val trval_day: Int,   // 旅游天数
    val weathers_id: Int,  // 天气信息主键
    val trvals_id : Int,  // 旅游事件主键
    val name : String,   // 事件名称
)



val Data_my = "{\n" +
        "     \"main\": {\n" +
        "         \"time_start\": \"2024-9-1\",\n" +
        "         \"trval_day\": \"3\",\n" +
        "         \"name\": \"西安三日游\"\n" +
        "     },\n" +
        "     \"weather\": [\n" +
        "         {\n" +
        "             \"number_day\": 1,\n" +
        "             \"where\": \"西安市\",\n" +
        "             \"data\": \"2024-9-1\",\n" +
        "             \"week\": 3,\n" +
        "             \"day_weather\": \"晴\",\n" +
        "             \"day_temp\": \"35\",\n" +
        "             \"night_weather\": \"晴\",\n" +
        "             \"night_temp\": \"24\",\n" +
        "             \"attention\": \"注意防晒和补水\"\n" +
        "         },\n" +
        "         {\n" +
        "             \"number_day\": 2,\n" +
        "             \"where\": \"西安市\",\n" +
        "             \"data\": \"2024-9-2\",\n" +
        "             \"week\": 4,\n" +
        "             \"day_weather\": \"多云\",\n" +
        "             \"day_temp\": \"35\",\n" +
        "             \"night_weather\": \"多云\",\n" +
        "             \"night_temp\": \"25\",\n" +
        "             \"attention\": \"带件外套，晚上较凉爽\"\n" +
        "         },\n" +
        "         {\n" +
        "             \"number_day\": 3,\n" +
        "             \"where\": \"西安市\",\n" +
        "             \"data\": \"2024-9-3\",\n" +
        "             \"week\": 5,\n" +
        "             \"day_weather\": \"晴\",\n" +
        "             \"day_temp\": \"36\",\n" +
        "             \"night_weather\": \"晴\",\n" +
        "             \"night_temp\": \"24\",\n" +
        "             \"attention\": \"注意防晒和补水\"\n" +
        "         }\n" +
        "     ],\n" +
        "     \"trval\": [\n" +
        "         {\n" +
        "             \"trval_id\": 1,\n" +
        "             \"model\": 0,\n" +
        "             \"day\": 1,\n" +
        "             \"trval_name\": \"武汉->西安\",\n" +
        "             \"time\": \"08:00-10:00\",\n" +
        "             \"abbreviate_thing\": \"飞机\",\n" +
        "             \"detailThings\": [\n" +
        "                 {\n" +
        "                     \"detail_id\": 1,\n" +
        "                     \"others\": \"\",\n" +
        "                     \"times\": \"08:00\",\n" +
        "                     \"my_content\": \"请提前到达机场办理值机手续\"\n" +
        "                 }\n" +
        "             ]\n" +
        "         },\n" +
        "         {\n" +
        "             \"trval_id\": 2,\n" +
        "             \"model\": 0,\n" +
        "             \"day\": 1,\n" +
        "             \"trval_name\": \"西安咸阳国际机场->酒店\",\n" +
        "             \"time\": \"10:00-10:30\",\n" +
        "             \"abbreviate_thing\": \"约30分钟/35元\",\n" +
        "             \"detailThings\": [\n" +
        "                 {\n" +
        "                     \"detail_id\": 1,\n" +
        "                     \"others\": \"\",\n" +
        "                     \"times\": \"10:00\",\n" +
        "                     \"my_content\": \"到达酒店后可先休息一会儿\"\n" +
        "                 }\n" +
        "             ]\n" +
        "         },\n" +
        "         {\n" +
        "             \"trval_id\": 3,\n" +
        "             \"model\": 1,\n" +
        "             \"day\": 1,\n" +
        "             \"trval_name\": \"酒店->钟楼\",\n" +
        "             \"time\": \"14:00-16:00\",\n" +
        "             \"abbreviate_thing\": \"约花费2小时/打车费约50元\",\n" +
        "             \"Travels_data\": [\n" +
        "                 {\n" +
        "                     \"start_star\": \"8天国际酒店\",\n" +
        "                     \"end_star\": \"钟楼\",\n" +
        "                     \"other\": \"钟楼是古城西安的标志性建筑，周围有很多美食和商店，可以逛逛品尝当地特色小\"\n" +
        "                 }\n" +
        "             ]\n" +
        "         },\n" +
        "         {\n" +
        "             \"trval_id\": 4,\n" +
        "             \"model\": 1,\n" +
        "             \"day\": 2,\n" +
        "             \"trval_name\": \"酒店->大雁塔\",\n" +
        "             \"time\": \"09:00-12:00\",\n" +
        "             \"abbreviate_thing\": \"约花费3小时/门票约40元\",\n" +
        "             \"Travels_data\": [\n" +
        "                 {\n" +
        "                     \"start_star\": \"8天国际酒店\",\n" +
        "                     \"end_star\": \"大雁塔\",\n" +
        "                     \"other\": \"大雁塔是唐代佛教建筑，是中国著名的古代建筑之一，可以欣赏到壮观的佛教文化\"\n" +
        "                 }\n" +
        "             ]\n" +
        "         },\n" +
        "         {\n" +
        "             \"trval_id\": 5,\n" +
        "             \"model\": 0,\n" +
        "             \"day\": 3,\n" +
        "             \"trval_name\": \"酒店->兵马俑\",\n" +
        "             \"time\": \"08:00-12:00\",\n" +
        "             \"abbreviate_thing\": \"约花费4小时/门票约120元\",\n" +
        "             \"detailThings\": [\n" +
        "                 {\n" +
        "                     \"detail_id\": 1,\n" +
        "                     \"others\": \"\",\n" +
        "                     \"times\": \"08:00\",\n" +
        "                     \"my_content\": \"请提前到达兵马俑景区，以免错过开放时间\"\n" +
        "                 }\n" +
        "             ]\n" +
        "         },\n" +
        "         {\n" +
        "             \"trval_id\": 6,\n" +
        "             \"model\": 0,\n" +
        "             \"day\": 3,\n" +
        "             \"trval_name\": \"兵马俑->西安咸阳国际机场\",\n" +
        "             \"time\": \"12:30-14:00\",\n" +
        "             \"abbreviate_thing\": \"约花费1.5小时/约80元\",\n" +
        "             \"detailThings\": [\n" +
        "                 {\n" +
        "                     \"detail_id\": 1,\n" +
        "                     \"others\": \"\",\n" +
        "                     \"times\": \"12:30\",\n" +
        "                     \"my_content\": \"返回机场，结束愉快的旅程\"\n" +
        "                 }\n" +
        "             ]\n" +
        "         }\n" +
        "     ]\n" +
        "}"

val default =  "{\n" +
        "    \"main\":{\n" +
        "        \"time_start\":\"未定义\",\n" +
        "        \"trval_day\":\"3\",\n" +
        "        \"name\":\"未命名旅游\"\n" +
        "    },\n" +
        "    \"weather\":[\n" +
        "        {\n" +
        "            \"number_day\":\"1\",\n" +
        "            \"where\":\"未定义\",\n" +
        "            \"data\":\"2024-9-1\",\n" +
        "            \"week\":3,\n" +
        "            \"day_weather\":\"晴天\",\n" +
        "            \"day_temp\":\"0\",\n" +
        "            \"night_weather\":\"晴\",\n" +
        "            \"night_temp\":\"0\",\n" +
        "            \"attention\":\"请与AI对话获取信息\"\n" +
        "        },{\n" +
        "            \"number_day\":\"2\",\n" +
        "            \"where\":\"未定义\",\n" +
        "            \"data\":\"2024-9-1\",\n" +
        "            \"week\":3,\n" +
        "            \"day_weather\":\"晴天\",\n" +
        "            \"day_temp\":\"0\",\n" +
        "            \"night_weather\":\"晴\",\n" +
        "            \"night_temp\":\"0\",\n" +
        "            \"attention\":\"请与AI对话获取信息\"\n" +
        "        },{\n" +
        "            \"number_day\":\"3\",\n" +
        "            \"where\":\"未定义\",\n" +
        "            \"data\":\"2024-9-1\",\n" +
        "            \"week\":3,\n" +
        "            \"day_weather\":\"晴天\",\n" +
        "            \"day_temp\":\"0\",\n" +
        "            \"night_weather\":\"晴\",\n" +
        "            \"night_temp\":\"0\",\n" +
        "            \"attention\":\"请与AI对话获取信息\"\n" +
        "        },\n" +
        "    ],\n" +
        "    \"trval\":[]\n" +
        "}"
