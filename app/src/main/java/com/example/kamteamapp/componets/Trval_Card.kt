package com.example.kamteamapp.componets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.KeyboardDoubleArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.data.Between
import com.example.kamteamapp.data.New_Temp_Trval_Items
import com.example.kamteamapp.ui.item.CardTopEmoji
import com.example.kamteamapp.ui.item.TimeString
import com.example.kamteamapp.ui.theme.Card1
import com.example.kamteamapp.ui.theme.Card2Time
import com.example.kamteamapp.ui.theme.Card2TimeBackGround
import com.example.kamteamapp.ui.theme.Card4Content2
import com.example.kamteamapp.ui.theme.typography

@Composable
fun ShowDetails(
    CardWeid: Dp,
    item:New_Temp_Trval_Items,
    modifier: Modifier,

    ){
    var expanded = rememberSaveable { mutableStateOf(false) }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .padding(2.dp)
            .width(CardWeid)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )

    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
        ) {
            Row {
                CardTopEmoji(item.model)
                Text(
                    text = item.trval_name,
                    style = typography.Card1
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            IconButton(
                onClick = { expanded.value = !expanded.value },
                colors = IconButtonColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor =  MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .size(20.dp,20.dp)
            ) {
                Icon(
                    imageVector = if(expanded.value) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowRight,
                    contentDescription = null)
            }
            if(expanded.value){
                xiangxi(item)
            }else{
                jianlue(item)
            }
        }
    }
}



@Composable
fun jianlue(
    item: New_Temp_Trval_Items
){
    if (item.model==0){
        yiyang(item,true)
    }else if (item.model==1){
        Row (
            modifier = Modifier
        ){
            TwoCirclesWithLine()
            Column {
                Text(text = item.taval_tar.start_tar)
                yiyang(item,true)
                Text(text = item.taval_tar.end_tar)
            }
        }
    }else{

    }
}

@Composable
fun TwoCirclesWithLine() {
    Canvas(
        modifier = Modifier
            .size(20.dp, 100.dp)
            .padding(top = 10.dp)
    ) {
        val radius = 10f // 圆的半径
        val circleCenterY1 = radius // 第一个圆的中心 Y 坐标
        val circleCenterY2 = size.height - radius // 第二个圆的中心 Y 坐标

        val paint = Paint() // 创建画笔

        // 绘制第一个圆
        drawCircle(
            color = Color.Blue,
            radius = radius,
            center = Offset(size.width / 2f, circleCenterY1)
        )

        // 绘制第二个圆
        drawCircle(
            color = Color.Red,
            radius = radius,
            center = Offset(size.width / 2f, circleCenterY2)
        )

        // 绘制连接两个圆的线
        drawLine(
            color = Color.Black,
            start = Offset(size.width / 2f, circleCenterY1 + radius),
            end = Offset(size.width / 2f, circleCenterY2 - radius),
            strokeWidth = 2f // 线条宽度
        )
    }
}

@Composable
fun yiyang(
    item: New_Temp_Trval_Items,
    where:Boolean
){
    Column (
        modifier = Modifier.padding(3.dp)
    ){
        Card(
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = Card2TimeBackGround
            ),
        ) {
            Text(
                text = TimeString(Between(item.time.start)),
                style = typography.Card2Time,
                modifier = Modifier
                    .padding(start = 2.dp, end = 2.dp)
            )
        }

        Spacer(modifier = Modifier.height(2.dp))
        if (where){
            showpart(item.abbreviate_thing)
        }else{
            showpart(item.taval_tar.other)
        }

        if (item.time.end !=null){
            Row {
                Card(
                    shape = RoundedCornerShape(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Card2TimeBackGround
                    ),
                ) {
                    Text(
                        text = TimeString(Between(item.time.end)),
                        style = typography.Card2Time,
                        modifier = Modifier
                            .padding(start = 2.dp, end = 2.dp)

                    )
                }
            }
        }
    }

}

@Composable
fun xiangxi(
    item: New_Temp_Trval_Items
){
    if (item.model==0){
        detail_diedai(item)
    }else if (item.model==1){
        Row (
            modifier = Modifier
        ){
            TwoCirclesWithLine()
            Column {
                Text(text = item.taval_tar.start_tar)
                yiyang(item,false)
                Text(text = item.taval_tar.end_tar)
            }
        }
    }else{

    }
}

@Composable
fun showpart(
    thing:String
){
    Row {
        Icon(
            imageVector = Icons.Outlined.KeyboardDoubleArrowRight,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .height(18.dp),
            contentDescription = null)
        Text(
            text = thing,
            style = MaterialTheme.typography.Card4Content2
        )
    }
}


@Composable
fun detail_diedai(
    item: New_Temp_Trval_Items
){
    Column(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
    ) {
        item.detailThings.forEach { itemss ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Column {
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Card2TimeBackGround
                        ),
                        modifier = Modifier
                            .padding(2.dp)
                    ) {
                        Text(
                            text = TimeString(Between(item.time.start)),
                            style = typography.Card2Time,
                            modifier = Modifier
                                .padding(start = 2.dp, end = 2.dp)
                        )
                    }
                    showpart(itemss.my_content)

                }

            }

        }
    }
}



//@Preview
//@Composable
//fun show(){
//    KamTeamAppTheme {
//        ShowDetails(New_Temp_Res,modifier = Modifier)
//    }
//}