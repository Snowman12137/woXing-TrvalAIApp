package com.example.kamteamapp.componets

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.data.BodyColor
import com.example.kamteamapp.ui.item.DisplayItem

@Composable
fun CardLayout(
    item: DisplayItem.BackGroundItem,
    MyColor:List<BodyColor>,
){
    if (item.backGround.isWeather){
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            //elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier
                .size(item.backGround.weide.dp,item.backGround.hight.dp)
        ){

        }
    }else{
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MyColor[0].color
            ),
            shape = RoundedCornerShape(8.dp),
            //elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier
                .size(item.backGround.weide.dp,item.backGround.hight.dp)
        ){

        }
    }
}