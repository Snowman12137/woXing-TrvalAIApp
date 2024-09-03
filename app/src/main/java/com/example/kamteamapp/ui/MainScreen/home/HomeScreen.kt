package com.example.kamteamapp.ui.MainScreen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.componets.MainCard
import com.example.kamteamapp.data.Main_items
import com.example.kamteamapp.ui.theme.MainPart1


@Composable
fun HomeScreen(
    datas:List<Main_items>
){
    TopPart()
    Spacer(Modifier.height(15.dp))
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
    ) {
        items(datas){item ->
            MainCard(
                imageResources = item.imageResources,
                text = item.my_text
            )
        }
    }
}


@Composable
fun TopPart(){
    Column {
        Text(
            text ="攻略分享",
            style = MaterialTheme.typography.MainPart1,
            modifier = Modifier.padding(15.dp)
        )
    }
}