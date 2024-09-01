package com.example.kamteamapp.ui.HistoryProgram

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardDoubleArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kamteamapp.R
import com.example.kamteamapp.componets.MyTopAppBar
import com.example.kamteamapp.data.BodyColor
import com.example.kamteamapp.data.Temp_Main_Items
import com.example.kamteamapp.ui.theme.Card5Content3
import com.example.kamteamapp.ui.theme.Card6Title
import com.example.kamteamapp.ui.theme.Card7Content


@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryProgramScreen(
    navigateUp :()->Unit,
    onNavigateToMain:()->Unit,
    navigateToItemUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HistoryProgramScreenViewModel = viewModel()
){
    val homeUiState by viewModel.homeUiState.collectAsState()
    Scaffold(
//        topBar = {
//            MyTopAppBar(
//                navigateUp = navigateUp,
//                onNavigateToMain = onNavigateToMain,
//                modifier = Modifier
//            )
//        }

    ) {innerPadding ->
        HistoryProgramBody(
            itemList = homeUiState.itemList,
            onItemClick = navigateToItemUpdate,
            contentPadding=innerPadding ,
            modifier = modifier
                .fillMaxSize()
        )
    }


}


@Composable
private fun HistoryProgramBody(
    itemList: List<Temp_Main_Items>,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemList.isEmpty()){
            Text(
                text = stringResource(R.string.no_item_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding),
            )
        }else{
            MyList(
                itemList = itemList,
                onItemClick = { onItemClick(it.id.toString()) },
                contentPadding = contentPadding,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }

}



@Composable
fun MyList(
    itemList: List<Temp_Main_Items>,
    onItemClick: (Temp_Main_Items) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = itemList,key = {it.id}){item->
            MyItem(
                item = item,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_small))
                    .clickable { onItemClick(item) }
            )
        }
    }
}


// 每个卡片的展示界面
@Composable
fun MyItem(
    item:Temp_Main_Items,modifier: Modifier = Modifier
){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = BodyColor.SECOND_WEATHER1.color
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        //elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.Card6Title
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.in_stock,item.trval_day),
                    style = MaterialTheme.typography.Card5Content3
                )
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = BodyColor.SECOND_WEATHER2.color
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = modifier
                    .padding(5.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,

                ){
                    Text(
                        text =item.other1,
                        style = MaterialTheme.typography.Card7Content,
                        //modifier = Modifier.align(Alignment.Start)
                    )
                    Icon(
                        imageVector = Icons.Outlined.KeyboardDoubleArrowRight,
                        contentDescription = null,
                        //modifier = Modifier.align(Alignment.End)
                    )
                }
            }



            
        }


    }
}



//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@Composable
//fun HomeBodyPreview(){
//    KamTeamAppTheme {
//        HomeBody(listOf(
//            Temp_Main_Items(
//                id = 1,
//                time_start = LocalDate.of(2024,7,9), // 使用 LocalDate 实例
//                trval_day = 5,
//                weathers_id = 1,
//                trvals_id = 1,
//                name = "实例1",
//                other1 = "广州->澳门->深圳五日游"
//            ),
//            Temp_Main_Items(
//                id = 2,
//                time_start = LocalDate.of(2024,7,10), // 使用 LocalDate 实例
//                trval_day = 3,
//                weathers_id = 5,
//                trvals_id = 5,
//                name = "实例2",
//                other1 = "西安->宝鸡->关山牧场三日游"
//            )
//        ), onItemClick = {})
//    }
//}



