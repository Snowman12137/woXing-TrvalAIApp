package com.example.kamteamapp.ui.intrest

import java.time.LocalDate

data class MyData(
    val birthday : LocalDate,
    val sex : Boolean,
    )

data class IntrestSelect(
    val intrest : List<String>,
)


val ShowData = listOf(
    "超过30天",
    "10到30天",
    "在10天内",
    "松弛感",
    "特种兵旅行",
    "穷游各地",
    "人文景观",
    "繁华城市",
    "吃吃吃",
    "特色美食",
    "可特殊时间旅行","'冒险'", "'放松'", "'文化探索'", "'美食体验'", "'自然风光'", "'城市游览'", "'海滩度假'", "'徒步旅行'", "'历史遗迹'", "'家庭友好'", "'浪漫之旅'", "'购物天堂'", "'户外探险'", "'野生动物'", "'独自旅行'", "'团体旅游'", "'奢华体验'", "'经济实惠'", "'生态旅游'", "'健康养生'", "'极限运动'", "'节庆活动'", "'自驾游'", "'背包客'", "'摄影'", "'潜水'", "'滑雪'", "'登山'", "'骑行'", "'观鸟'", "'邮轮'", "'岛屿跳岛'", "'乡村体验'", "'艺术欣赏'", "'音乐节'", "'电影节'", "'马拉松'", "'瑜伽静修'", "'葡萄酒品鉴'"


)


