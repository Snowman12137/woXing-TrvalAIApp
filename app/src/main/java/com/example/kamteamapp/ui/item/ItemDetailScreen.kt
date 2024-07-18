@file:OptIn(ExperimentalFoundationApi::class)

package com.example.kamteamapp.ui.item

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kamteamapp.MyTopAppBar
import com.example.kamteamapp.R
import com.example.kamteamapp.Utils.ScreenSizeManager
import com.example.kamteamapp.data.BodyColor
import com.example.kamteamapp.data.ColorPart
import com.example.kamteamapp.data.TempRes
import com.example.kamteamapp.data.TempRes2
import com.example.kamteamapp.data.TempWeath
import com.example.kamteamapp.data.TempWeath2
import com.example.kamteamapp.data.Temp_Trval_Items
import com.example.kamteamapp.data.Temp_Weather_Items
import com.example.kamteamapp.data.Weather
import com.example.kamteamapp.ui.navigation.NavigationDestination
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import com.example.kamteamapp.ui.theme.Weather1
import com.example.kamteamapp.ui.theme.Weather2
import com.example.kamteamapp.ui.theme.Weather3
import com.example.kamteamapp.ui.theme.Weather4
import kotlinx.coroutines.flow.combine
import java.time.DayOfWeek
import java.time.LocalDate

// 导航栏
object ItemDetailsDestination : NavigationDestination {
    override val route = "item_details"
    override val titleRes = R.string.item_detail_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemDetailsScreen(
    itemIdTime :Int?,
    state: State,
    actions: Actions,
    navigateBack: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    ){

    if (itemIdTime == 1){
        actions.setTrvalItem(combineItems(TempRes, TempWeath) )
    } else{
        actions.setTrvalItem(combineItems(TempRes2, TempWeath2) )
    }

    Scaffold (
        topBar = {
            MyTopAppBar(
                title = stringResource(ItemDetailsDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }
    ){innerPadding ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            ItemDetailsBody(
                actions = actions,
                state=state,
                contentPadding = innerPadding,
                onItemClick = navigateToItemUpdate,
                modifier = Modifier
                    .fillMaxSize()

            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun ItemDetailsBody(
    state: State,
    actions: Actions,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onItemClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    Row (
        verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(top = 100.dp)
    ){
        TimeLiner()
        Spacer(modifier = Modifier.width(8.dp))
        MainBody(state = state, actions = actions, onItemClick = onItemClick)
    }
}


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun TimeLiner(
    modifier: Modifier = Modifier
){
    // 时间轴组件
    LazyColumn(
        modifier = Modifier
            .width(20.dp) // 时间轴的宽度
            .background(
                //shape = RoundedCornerShape(10.dp),
                color = colorScheme.background
            )
    ) {
            // 月份
        item{
            Text(
                text = "6月",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth() // 填充宽度
                    .padding(vertical = 4.dp)
                    .height(50.dp)
            )
        }


            items(13) { hour ->
                Column(
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .height(70.dp)
                        .background(
                            shape = RectangleShape,
                            color = MaterialTheme.colorScheme.inversePrimary
                        ),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$hour",
                        color = Color.Black,
                        style = MaterialTheme.typography.labelMedium, // 确保从MaterialTheme获取typography
                        textAlign = TextAlign.Center, // 文本居中对齐
                        modifier = Modifier
                            .fillMaxWidth() // 填充宽度
                            .padding(vertical = 4.dp) // 根据需要添加垂直方向的间距
                    )
                    // 先把颜色取出来
                    val colors = MaterialTheme.colorScheme.secondary
                    Canvas(
                        modifier = Modifier
                            .size(16.dp)
                            .align(Alignment.CenterHorizontally)

                    ){
                        // 画笔颜色
                        drawCircle(
                            color = colors, // 设置圆形颜色
                            radius = 8f // 设置半径，确保它小于 Canvas 的一半尺寸
                        )
                    }
                }

            }


    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainBody(
    state: State,
    actions: Actions,
    onItemClick: (Int) -> Unit,
){
    Column {
        LazyMainBoay(
            state = state,
            actions = actions,
            onItemClick = onItemClick
        )
    }
}



fun combineItems(trvalItems: List<Temp_Trval_Items>, weatherItems: List<Temp_Weather_Items>): List<DisplayItem> {
    return trvalItems.map { DisplayItem.TravelItem(it) } +
            weatherItems.map { DisplayItem.WeatherItem(it) }
}

