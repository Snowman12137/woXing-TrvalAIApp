package com.example.kamteamapp.ui.item

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.KeyboardDoubleArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.content.PackageManagerCompat.LOG_TAG
import com.example.kamteamapp.Utils.toCustomBoolean
import com.example.kamteamapp.componets.CardLayout
import com.example.kamteamapp.componets.ShowCard
import com.example.kamteamapp.componets.ShowDetails
import com.example.kamteamapp.componets.TimeLinerLayout
import com.example.kamteamapp.componets.WeatherCard
import com.example.kamteamapp.data.BodyColor
import com.example.kamteamapp.data.ColorPart
import com.example.kamteamapp.data.Money_Data
import com.example.kamteamapp.data.Temp_Trval_Items
import com.example.kamteamapp.data.Temp_Weather_Items
import com.example.kamteamapp.data.TimeLinerColor
import com.example.kamteamapp.ui.item.LazyPart.CustomLazyListScope
import com.example.kamteamapp.ui.item.LazyPart.LazyLayoutState
import com.example.kamteamapp.ui.item.LazyPart.rememberItemProvider
import com.example.kamteamapp.ui.item.LazyPart.rememberLazyLayoutState
import com.example.kamteamapp.ui.theme.Card1
import com.example.kamteamapp.ui.theme.Card2Time
import com.example.kamteamapp.ui.theme.Card2TimeBackGround
import com.example.kamteamapp.ui.theme.Card3Content1
import com.example.kamteamapp.ui.theme.Card4Content2
import com.example.kamteamapp.ui.theme.Card7ContentMoney
import com.example.kamteamapp.ui.theme.Card8Res
import com.example.kamteamapp.ui.theme.TimeLiner
import com.example.kamteamapp.ui.theme.TimeLiner2
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
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val lazyLayoutState = rememberLazyLayoutState()
    val paramter = actions.get_Paramter()
        val CardWeid = (paramter.DayLangDP - paramter.BasePadding).dp
        CustomLazyLayout(
            numState = state,
            actions = actions,
            state = lazyLayoutState,
            modifier = Modifier
                .fillMaxSize()
        ){items(state.items){item->
             // 每个块的组件在这里
            when(item.hereItem){
                is DisplayItem.WeatherItem->{
                    val groupIndex = ((item.hereItem.weatheritem.number_day-1)%ColorPart.values().size)
                    val colorpart = ColorPart.values()[groupIndex].colors
                    WeatherCard(item = item.hereItem.weatheritem,colorpart,CardWeid)
                }
                is DisplayItem.TimeLiner->{
                    TimeLinerLayout(
                        actions = actions,
                        modifier
                    )
                }
                is DisplayItem.TravelItem->{
                    ShowCard(
                        item.hereItem.trvalitem,
                        CardWeid,
                        onItemClick = {onItemClick(it.trval_id)},
                        numState = state,
                        actions,
                        item
                    )
                }
                is DisplayItem.BackGroundItem->{
                    val groupIndex = (item.hereItem.backGround.index % ColorPart.values().size)
                    val colorpart = ColorPart.values()[groupIndex].colors
                    CardLayout(item.hereItem,colorpart)
                }
                is DisplayItem.New_Temp_Trval->{
                    ShowDetails(
                        CardWeid,
                        item.hereItem.new_temp_trval_Items,
                        modifier
                    )
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
    var updatedArray by remember { mutableStateOf(listOf<Int>()) }
    LazyLayout(
        modifier = modifier
            .clipToBounds()
            .lazyLayoutPointerInput(state, numState),
        itemProvider = itemProvider,
    ){constraints ->
        val boundaries = state.getBoundaries(constraints)
        val indexes = itemProvider.getItemIndexesInRange(boundaries)

        updatedArray = ensureContainsZeroTonum(indexes,numState.data,numState.items.size-1).sorted()

        ChangeXY_Top(numState,state.offsetState.value.x,state.offsetState.value.y)


        val indexesWithPlaceables = updatedArray.associateWith {
            measure(it, Constraints())
        }


        layout(constraints.maxWidth, constraints.maxHeight) {
            indexesWithPlaceables.forEach { (index, placeables) ->
                val item = itemProvider.getItem(index)
                placeables.forEach { item_long->
                    item?.long = item_long.height
                }
                solve_constrain(numState)
                item?.let { placeItem(state, item, placeables) }
            }
        }
    }
}


fun solve_constrain(numState: State){
    numState.items.forEachIndexed{index1,items1->
        if (items1.hereItem is DisplayItem.New_Temp_Trval){
            numState.items.forEachIndexed{index2,items2->
                if (items2.hereItem is DisplayItem.New_Temp_Trval){
                    if (items1.hereItem.new_temp_trval_Items.day==items2.hereItem.new_temp_trval_Items.day){
                        if (items2.y > items1.y){
                            if (items2.y - items1.y -items1.long<0){
                                items2.y = items1.y + items1.long
                            }
                        }
                    }
                }
            }
        }
    }
}





private fun Modifier.lazyLayoutPointerInput(state: LazyLayoutState,numState:State): Modifier {
    state.set_bounary(numState.maxX,numState.maxY)
    //Log.d(LOG_TAG,"numState.maxX"+numState.maxX.toString()+"numState.maxY"+numState.maxY.toString())
    return pointerInput(Unit) {
        detectDragGestures { change, dragAmount ->
            change.consume()
            state.onDrag(IntOffset(dragAmount.x.toInt(), dragAmount.y.toInt()))
        }
    }
}

private fun Placeable.PlacementScope.placeItem(state: LazyLayoutState, listItem: ListItem, placeables: List<Placeable>) {

    val xPosition : Int
    val yPosition : Int

    when (listItem.hereItem){
        is DisplayItem.TravelItem->{
            xPosition = listItem.x - state.offsetState.value.x
            yPosition = listItem.y - state.offsetState.value.y
        }
        is DisplayItem.WeatherItem->{
            xPosition = listItem.x - state.offsetState.value.x
            yPosition = 0
        }
        is DisplayItem.BackGroundItem->{
            if (listItem.hereItem.backGround.isWeather){
                xPosition = listItem.x - state.offsetState.value.x
                yPosition = 0
            }else{
                xPosition = listItem.x - state.offsetState.value.x
                yPosition = listItem.y - state.offsetState.value.y
            }

        }
        is DisplayItem.TimeLiner->{
            xPosition = 0
            yPosition = listItem.y - state.offsetState.value.y
        }
        is DisplayItem.New_Temp_Trval->{
            xPosition = listItem.x - state.offsetState.value.x
            yPosition = listItem.y - state.offsetState.value.y
        }
    }

    placeables.forEach { placeable ->
        placeable.placeRelative(
            xPosition,
            yPosition
        )
    }
}











fun ensureContainsZeroTonum(inputList: List<Int>,maxnum:Int,lastnum:Int): List<Int> {
    if ((0..maxnum).none { it !in inputList }) {
        return inputList
    }
    val containsMaxnum = inputList.contains(lastnum)

    val missingNumbers = (0..maxnum).filter { it !in inputList }

    if (!containsMaxnum){
        return missingNumbers + inputList + lastnum
    }

    return missingNumbers + inputList
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








