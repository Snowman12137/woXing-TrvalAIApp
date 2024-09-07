@file:OptIn(ExperimentalFoundationApi::class)

package com.example.kamteamapp.ui.item

import android.os.Build
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.kamteamapp.base.network.NetViewModel
import com.example.kamteamapp.componets.MyTopAppBar
import com.example.kamteamapp.data.New_Temp_Trval_Items
import com.example.kamteamapp.data.Temp_Trval_Items
import com.example.kamteamapp.data.Temp_Weather_Items


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemDetailsScreen(
    state: State,
    actions: Actions,
    navigateBack: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    onNavigateToMain:()->Unit,
    ){
    Scaffold(
        topBar = {
            MyTopAppBar(
                navigateUp = { navigateBack() },
                onNavigateToMain = { onNavigateToMain() }
            )
        }
    ) {innerPadding ->
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            ItemDetailsBody(
                actions = actions,
                state=state,
                onItemClick = navigateToItemUpdate,
                contentPadding = innerPadding,
                modifier = Modifier
                    .fillMaxSize()
                    //.padding(innerPadding)
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
        modifier = Modifier.padding(top = 50.dp)
    ){
        Spacer(modifier = Modifier.width(8.dp))
        MainBody(state = state, actions = actions, onItemClick = onItemClick)
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



fun combineItems(detailss:List<New_Temp_Trval_Items>, weatherItems: List<Temp_Weather_Items>?): List<DisplayItem> {
    if (weatherItems!=null){
        return detailss.map { DisplayItem.New_Temp_Trval(it) } +
                weatherItems.map { DisplayItem.WeatherItem(it) }
    }else{
        return detailss.map { DisplayItem.New_Temp_Trval(it) }
    }

}

