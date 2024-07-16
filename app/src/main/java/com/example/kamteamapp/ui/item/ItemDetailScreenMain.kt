package com.example.kamteamapp.ui.item

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.KeyboardDoubleArrowRight
import androidx.compose.material.icons.outlined.Sort
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.PackageManagerCompat.LOG_TAG
import com.example.kamteamapp.R
import com.example.kamteamapp.Utils.convertPixelsToDp
import com.example.kamteamapp.Utils.toCustomBoolean
import com.example.kamteamapp.data.Between
import com.example.kamteamapp.data.BodyColor
import com.example.kamteamapp.data.ColorPart
import com.example.kamteamapp.data.Money_Data
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
import com.example.kamteamapp.ui.theme.Card2TimeBackGround
import com.example.kamteamapp.ui.theme.Card3Content1
import com.example.kamteamapp.ui.theme.Card4Content2
import com.example.kamteamapp.ui.theme.Card5Content3
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import com.example.kamteamapp.ui.theme.Weather1
import com.example.kamteamapp.ui.theme.Weather2
import com.example.kamteamapp.ui.theme.Weather3
import com.example.kamteamapp.ui.theme.Weather4
import com.example.kamteamapp.ui.theme.typography
import java.time.DayOfWeek
import java.time.LocalDate
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection as NestedScrollConnection


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalFoundationApi
@Composable
fun LazyMainBoay(
    state: State,
    actions: Actions,
    context: Context,
    modifier: Modifier = Modifier
){
    val lazyLayoutState = rememberLazyLayoutState()
    actions.setTrvalItem(combineItems(TempRes, TempWeath) )
        val CardWeid = 200.dp
        CustomLazyLayout(
            numState = state,
            actions = actions,
            state = lazyLayoutState,
            modifier = Modifier.fillMaxSize(),
        ){items(state.items){item->
             // 每个块的组件在这里
            when(item.hereItem){
                is DisplayItem.WeatherItem->{
                    val groupIndex = ((item.hereItem.weatheritem.number_day-1)%ColorPart.values().size)
                    val colorpart = ColorPart.values()[groupIndex].colors
                    WeatherCard(item = item.hereItem.weatheritem,colorpart,CardWeid)
                }
                is DisplayItem.TravelItem->{
                    ShowCard(item.hereItem.trvalitem,CardWeid,modifier)
                }
                is DisplayItem.BackGroundItem->{
                    val groupIndex = (item.hereItem.backGround.index % ColorPart.values().size)
                    val colorpart = ColorPart.values()[groupIndex].colors
                    CardLayout(item.hereItem,colorpart,context)
                }
            }


        }
    }
}




@SuppressLint("RestrictedApi")
@ExperimentalFoundationApi
@Composable
fun CustomLazyLayout(
    numState:State,
    actions: Actions,
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

        val updatedArray = ensureContainsZeroTonum(indexes,numState.data).sorted()

        val indexesWithPlaceables = updatedArray.associateWith {
            measure(it, Constraints())
        }

        Log.d(LOG_TAG,indexesWithPlaceables.toString())
        layout(constraints.maxWidth, constraints.maxHeight) {
            indexesWithPlaceables.forEach { (index, placeables) ->
                val item = itemProvider.getItem(index)
                item?.let { placeItem(actions,index,state, item, placeables) }
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

private fun Placeable.PlacementScope.placeItem(actions: Actions,index: Int,state: LazyLayoutState, listItem: ListItem, placeables: List<Placeable>) {
    val xPosition : Int
    val yPosition : Int
    when (listItem.hereItem){
        is DisplayItem.TravelItem->{
            xPosition = listItem.x - state.offsetState.value.x
            yPosition = listItem.y - state.offsetState.value.y
        }
        is DisplayItem.WeatherItem->{
            actions.ChangeXY_Top(state.offsetState.value.y)
            xPosition = listItem.x - state.offsetState.value.x
            yPosition = 0
        }
        is DisplayItem.BackGroundItem->{
            actions.ChangeXY_Top(state.offsetState.value.y)
            if (listItem.hereItem.backGround.isWeather){
                xPosition = listItem.x - state.offsetState.value.x
                yPosition = 0
            }else{
                xPosition = listItem.x - state.offsetState.value.x
                yPosition = listItem.y - state.offsetState.value.y
            }

        }
    }

    placeables.forEach { placeable ->
        Log.d(LOG_TAG,xPosition.toString()+"   "+yPosition.toString())
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
fun CardLayout(
    item:DisplayItem.BackGroundItem,
    MyColor:List<BodyColor>,
    context: Context
){
    Log.d(LOG_TAG,item.backGround.weide.dp.toString()+item.backGround.hight.dp.toString())
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


fun ensureContainsZeroTonum(inputList: List<Int>,maxnum:Int): List<Int> {
    if ((0..maxnum).none { it !in inputList }) {
        return inputList
    }

    val missingNumbers = (0..maxnum).filter { it !in inputList }

    return missingNumbers + inputList
}

@Composable
fun ShowCard(
    item: Temp_Trval_Items,
    CardWeid:Dp,
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
            .width(CardWeid)

    ) {
        Column (
            modifier = Modifier
                .padding(5.dp)
        ){
            Row (){
                CardTopEmoji(item)
                Text(
                    text = item.trval_name,
                    style = typography.Card1
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(

            ) {
                Row {
                    Text(
                        text = "预计时间:",
                        style = MaterialTheme.typography.Card3Content1
                    )
                    Card(
                        shape = RoundedCornerShape(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Card2TimeBackGround
                        ),
                    ) {
                        Text(
                            text = TimeString(item.time),
                            style = typography.Card2Time,
                            modifier = Modifier
                                .padding(start = 2.dp, end = 2.dp)

                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardDoubleArrowRight,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier
                            .height(18.dp),
                        contentDescription = null)
                    Text(
                        text = item.content,
                        style = MaterialTheme.typography.Card4Content2
                    )
                }

            }
            MoneyCaculate(item.MyMoney)

        }

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(
    item: Temp_Weather_Items, MyColor:List<BodyColor>, CardWeid:Dp,modifier: Modifier = Modifier
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MyColor[1].color,
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(5.dp)
            .width(CardWeid)
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


@Composable
fun MoneyCaculate(
    mymoney: List<Money_Data>
){
    var expanded = rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
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
            MoneyShow(mymoney)
        }
    }
}


@Composable
fun MoneyShow(
    mymoney: List<Money_Data>
){
    val scrollState = rememberLazyListState()
    Card(
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(3.dp),
        modifier = Modifier
            .padding(2.dp)
    ) {
        Column {
            Row (
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "项目名称")
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "金额")
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "类别")
            }
            val allData = buildList {
                add(MonyRow.Labels(mymoney.map { it.Lable }))
                add(MonyRow.Moneys(mymoney.map { it.Money }))
                add(MonyRow.CheckStates(mymoney.map { it.CheckState }))
            }
            LazyRow (
                state = scrollState,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
                //verticalArrangement = Arrangement.spacedBy(8.dp)
            ){items(allData){item->
                ShowRow(item)

                }
            }
            Row (
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "总金额")
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "180")
            }

        }
    }

}


sealed class MonyRow {
    data class Labels(val labels:  List<String>) : MonyRow()
    data class Moneys(val moneys:  List<Float>) : MonyRow()
    data class CheckStates(var checkstates:  List<String>):MonyRow()
}

@Composable
fun ShowRow(item: MonyRow){



    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (item) {
            is MonyRow.Labels -> {
                item.labels.forEach { label ->
                    Text(text = label, Modifier.padding(vertical = 4.dp))
                }
            }
            is MonyRow.Moneys -> {
                item.moneys.forEach { money ->
                    Text(text = " ${money.toString()}", Modifier.padding(vertical = 4.dp))
                }
            }
            is MonyRow.CheckStates -> {
                val checkStates by item.checkStatesState()

                checkStates.forEachIndexed { index,state ->
                    if (state == "0" ){
                        Checkbox(
                            checked = state.toCustomBoolean(),
                            onCheckedChange = {isCheck ->
                                //checkStates[index] = isCheck.toString()
                            },
                        )
                    }else if (state == "1"){
                        Checkbox(
                            checked = state.toCustomBoolean(),
                            onCheckedChange = null,
                        )
                    }else{

                    }
                }
            }
        }

    }
}


@Composable
fun MonyRow.CheckStates.checkStatesState(): MutableState<List<String>> {
    // 使用 remember 来创建一个不可变的状态
    return remember { mutableStateOf(this.checkstates) }
}


fun String.toCustomBoolean(): Boolean {
    return this == "1"
}

@Composable
fun FormAble(text1:String,text2:String,text3:String){
    Row (
        horizontalArrangement = Arrangement.Center
    ){
        Text(text = text1)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = text2)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = text3)
    }
}


@Preview
@Composable
fun show(){
    KamTeamAppTheme {
        MoneyShow(
            mymoney = listOf(
                Money_Data(
                    "飞机票",
                    890f,
                    "2"
                ),Money_Data(
                    "保险",
                    10f,
                    "2"
                ),Money_Data(
                    "额外托运",
                    100f,
                    "1"
                ),Money_Data(
                    "机场小吃",
                    50f,
                    "0"
                )
            )
        )
    }
}