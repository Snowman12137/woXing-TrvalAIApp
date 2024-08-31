package com.example.kamteamapp.componets

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.Utils.toCustomBoolean
import com.example.kamteamapp.data.Money_Data
import com.example.kamteamapp.data.Temp_Trval_Items
import com.example.kamteamapp.ui.item.Actions
import com.example.kamteamapp.ui.item.CardTopEmoji
import com.example.kamteamapp.ui.item.ListItem
import com.example.kamteamapp.ui.item.State
import com.example.kamteamapp.ui.item.TimeString
import com.example.kamteamapp.ui.theme.Card1
import com.example.kamteamapp.ui.theme.Card2Time
import com.example.kamteamapp.ui.theme.Card2TimeBackGround
import com.example.kamteamapp.ui.theme.Card3Content1
import com.example.kamteamapp.ui.theme.Card4Content2
import com.example.kamteamapp.ui.theme.Card7ContentMoney
import com.example.kamteamapp.ui.theme.Card8Res
import com.example.kamteamapp.ui.theme.typography

@Composable
fun ShowCard(
    item: Temp_Trval_Items,
    CardWeid: Dp,
    onItemClick: (Temp_Trval_Items) -> Unit,
    numState: State,
    actions: Actions,
    itemss: ListItem
){
    val hight = remember { mutableStateOf<Int?>(null) }


    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .padding(5.dp)
            .width(CardWeid)
            .clickable { onItemClick(item) }
    ) {
        Column (
            modifier = Modifier
                .padding(5.dp)
        ){
            Row (){
                CardTopEmoji(item.model)
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
                add(MonyRow.Labels(mymoney.map { it.Lable }+"已付总金额"))
                add(MonyRow.Moneys(mymoney.map { it.Money }+890f))
                add(MonyRow.CheckStates(mymoney.map { it.CheckState }))
            }
            //Log.d(LOG_TAG,printAllData(allData).toString())
            LazyRow (
                state = scrollState,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
                //verticalArrangement = Arrangement.spacedBy(8.dp)
            ){items(allData){item->
                ShowRow(item)

            }
            }
        }
    }

}

@Composable
fun ShowRow(item: MonyRow){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isChecked by remember { mutableStateOf(false) }
        when (item) {
            is MonyRow.Labels -> {
                item.labels.forEachIndexed {index, label ->
                    if (index == item.labels.size-1){
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(text = label, Modifier.padding(vertical = 4.dp), style = MaterialTheme.typography.Card8Res )
                    }else{
                        Text(text = label, Modifier.padding(vertical = 4.dp), style = MaterialTheme.typography.Card7ContentMoney)
                    }
                }
            }
            is MonyRow.Moneys -> {
                item.moneys.forEachIndexed { index, money ->
                    if (index == item.moneys.size-1){
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(text = " ${money.toString()}", Modifier.padding(vertical = 4.dp), style = MaterialTheme.typography.Card8Res )
                    }else{
                        Text(text = " ${money.toString()}", Modifier.padding(vertical = 4.dp), style = MaterialTheme.typography.Card7ContentMoney)
                    }
                }
            }
            is MonyRow.CheckStates -> {

                item.checkstates.forEachIndexed { index,state ->
                    if (state == "0" ){
                        Checkbox(
                            checked = state.toCustomBoolean() || isChecked,
                            onCheckedChange = {isCheck ->
                                isChecked = isCheck
                            },
                            modifier = Modifier
                                .size(23.dp)
                                .padding(3.dp)
                        )
                    }else if (state == "1"){
                        Checkbox(
                            enabled = false,
                            checked = state.toCustomBoolean(),
                            onCheckedChange = null,
                            modifier = Modifier
                                .size(23.dp))
                    }else{

                    }
                }
            }
        }

    }
}


sealed class MonyRow {
    data class Labels(val labels:  List<String>) : MonyRow()
    data class Moneys(val moneys:  List<Float>) : MonyRow()
    data class CheckStates(var checkstates:  List<String>):MonyRow()
}


