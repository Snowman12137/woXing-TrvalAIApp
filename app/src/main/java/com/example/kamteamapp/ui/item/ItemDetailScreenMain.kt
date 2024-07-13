package com.example.kamteamapp.ui.item

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.PackageManagerCompat.LOG_TAG
import com.example.kamteamapp.R
import com.example.kamteamapp.data.Between
import com.example.kamteamapp.data.BodyColor
import com.example.kamteamapp.data.ColorPart
import com.example.kamteamapp.data.Temp_Trval_Items
import com.example.kamteamapp.data.Temp_Weather_Items
import com.example.kamteamapp.data.Time
import com.example.kamteamapp.data.Weather
import com.example.kamteamapp.ui.item.LazyPart.CustomLazyListScope
import com.example.kamteamapp.ui.item.LazyPart.LazyLayoutState
import com.example.kamteamapp.ui.item.LazyPart.rememberItemProvider
import com.example.kamteamapp.ui.item.LazyPart.rememberLazyLayoutState
import com.example.kamteamapp.ui.theme.Card1
import com.example.kamteamapp.ui.theme.Card2Time
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import com.example.kamteamapp.ui.theme.Weather1
import com.example.kamteamapp.ui.theme.Weather2
import com.example.kamteamapp.ui.theme.Weather3
import com.example.kamteamapp.ui.theme.Weather4
import com.example.kamteamapp.ui.theme.typography
import java.time.DayOfWeek
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalFoundationApi
@Composable
fun LazyMainBoay(
    state: State,
    actions: Actions,
    modifier: Modifier = Modifier
){
    val lazyLayoutState = rememberLazyLayoutState()
    actions.setTrvalItem(combineItems(TempRes, TempWeath) )

        CustomLazyLayout(
            numState = state,
            state = lazyLayoutState,
            modifier = Modifier.fillMaxSize(),
        ){items(state.items){item->
             // 每个块的组件在这里
            when(item.hereItem){
                is DisplayItem.WeatherItem->{
                    val groupIndex = (item.hereItem.weatheritem.number_day%ColorPart.values().size)
                    val colorpart = ColorPart.values()[groupIndex].colors
                    WeatherCard(item = item.hereItem.weatheritem,colorpart)
                }
                is DisplayItem.TravelItem->{
                    ShowCard(item.hereItem.trvalitem,modifier)
                }
            }


        }
    }
}




@SuppressLint("RestrictedApi")
@ExperimentalFoundationApi
@Composable
fun CustomLazyLayout(
    numState: State,
    state: LazyLayoutState = rememberLazyLayoutState(),
    modifier: Modifier,
    content: CustomLazyListScope.() -> Unit
){
    val itemProvider = rememberItemProvider(content)
    LazyLayout(
        modifier = modifier
            .clipToBounds()
            .lazyLayoutPointerInput(state),
        itemProvider = itemProvider,
    ){constraints ->
        val boundaries = state.getBoundaries(constraints)
        val indexes = itemProvider.getItemIndexesInRange(boundaries)


        val indexesWithPlaceables = indexes.associateWith {
            measure(it, Constraints())
        }
        Log.d(LOG_TAG,indexesWithPlaceables.toString())
        layout(constraints.maxWidth, constraints.maxHeight) {
            indexesWithPlaceables.forEach { (index, placeables) ->
                val item = itemProvider.getItem(index)
                item?.let { placeItem(numState,state, item, placeables) }
            }
        }

    }
}




private fun Modifier.lazyLayoutPointerInput(state: LazyLayoutState): Modifier {
    return pointerInput(Unit) {
        detectDragGestures { change, dragAmount ->
            change.consume()
            state.onDrag(IntOffset(dragAmount.x.toInt(), dragAmount.y.toInt()))
        }
    }
}

private fun Placeable.PlacementScope.placeItem(numState: State,state: LazyLayoutState, listItem: ListItem, placeables: List<Placeable>) {
    val xPosition : Int
    val yPosition : Int
    when (listItem.hereItem){
        is DisplayItem.TravelItem->{
            xPosition = listItem.x - state.offsetState.value.x
            yPosition = listItem.y - state.offsetState.value.y
        }
        is DisplayItem.WeatherItem->{
            xPosition = listItem.x
            yPosition = listItem.y
        }
    }

    placeables.forEach { placeable ->
        placeable.placeRelative(
            xPosition,
            yPosition
        )
    }
}

fun combineItems(trvalItems: List<Temp_Trval_Items>, weatherItems: List<Temp_Weather_Items>): List<DisplayItem> {
    return trvalItems.map { DisplayItem.TravelItem(it) } +
            weatherItems.map { DisplayItem.WeatherItem(it) }
}



@Composable
fun ShowCard(
    item: Temp_Trval_Items,
    modifier: Modifier
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(5.dp)

    ) {
        Column (

        ){
            Row (){
                Image(
                    painter = painterResource(id = R.drawable._plane) ,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
                Text(
                    text = item.trval_name,
                    style = typography.Card1
                )
            }
            Column(

            ) {
                Row {
                    Text(text = "预计时间:")
                    Card(
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text(
                            text = TimeString(item.time),
                            style = typography.Card2Time
                        )
                    }
                }
                Text(text = item.content)
            }

        }

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(
    item: Temp_Weather_Items, MyColor:List<BodyColor>, modifier: Modifier = Modifier
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MyColor[1].color,
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(5.dp)
        //.size(150.dp, 70.dp)
    ) {
        Column(
            modifier = Modifier

        ) {
            Row (){
                Column (
                    modifier = Modifier
                        .padding(bottom = 2.dp, start = 2.dp)
                ){
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MyColor[2].color,
                        ),
                        shape = RoundedCornerShape(3.dp),
                        modifier = Modifier
                            .padding(2.dp)

                    ) {
                        Text(
                            text = String.format("Day %d %s",item.number_day,item.where),
                            style = MaterialTheme.typography.Weather2
                        )
                    }
                    Text(
                        text = String.format("%s日 %s",item.data.dayOfMonth, getChineseDayOfWeek(item.data)),
                        style = MaterialTheme.typography.Weather3
                    )
                }

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MyColor[2].color,
                    ),
                    shape = RoundedCornerShape(3.dp),
                    modifier = Modifier
                        .padding(2.dp)
                ) {
                    Column() {
                        Row (verticalAlignment = Alignment.CenterVertically){
                            Image(
                                painter = painterResource(id = item.day_weather.getImageResId()),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                            )
                            Text(
                                text = String.format("%d℃",item.dat_temp),
                                style = MaterialTheme.typography.Weather4
                            )


                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = item.night_weather.getImageResId()),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                            )
                            Text(
                                text = String.format("%d℃",item.night_temp),
                                style = MaterialTheme.typography.Weather4
                            )
                        }
                    }
                }

            }

        }
        Text(
            text = item.attention,
            style = MaterialTheme.typography.Weather1
        )

    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CardPreview(){
    val temdata1 : LocalDate = LocalDate.of(2024,7,9)
    KamTeamAppTheme {
        WeatherCard(
            item = Temp_Weather_Items(
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
            ), MyColor = ColorPart.values()[0].colors
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getChineseDayOfWeek(date: LocalDate): String {
    // 获取星期几
    val dayOfWeek = date.dayOfWeek
    // 将 DayOfWeek 转换为中文
    return when (dayOfWeek) {
        DayOfWeek.MONDAY -> "星期一"
        DayOfWeek.TUESDAY -> "星期二"
        DayOfWeek.WEDNESDAY -> "星期三"
        DayOfWeek.THURSDAY -> "星期四"
        DayOfWeek.FRIDAY -> "星期五"
        DayOfWeek.SATURDAY -> "星期六"
        DayOfWeek.SUNDAY -> "星期日"
    }
}


//@RequiresApi(Build.VERSION_CODES.O)
//@Preview()
//@Composable
//fun ShowCard(){
//    KamTeamAppTheme {
//        ShowCard(
//            ListItem(
//                0,
//                0,
//                Temp_Trval_Items(
//                    1,
//                    0,
//                    1,
//                    1,
//                    "西安->广州",
//                    LocalDate.of(2024,7,7),
//                    0,
//                    Between(
//                        Time(
//                            0,
//                            30
//                        )
//                    ),
//                    "行李放到酒店，下一行程->打车"
//                )
//            ), modifier = Modifier
//        )
//    }
//}
