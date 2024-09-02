package com.example.kamteamapp.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.R
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import com.example.kamteamapp.ui.theme.MainPart2


@Composable
@Preview
fun show(){
    KamTeamAppTheme {
        MainCard(
            R.drawable.temp1,
            "哈尔滨一日游"
        )
    }
}

@Composable
fun MainCard(
    imageResources: Int,
    text:String
){
    Column(
        modifier = Modifier
    ) {
        Image(painter = painterResource(id = imageResources),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width =200.dp, height = 200.dp)
                .clip(shape = RoundedCornerShape(10.dp))
        )
        Text(
            text =text,
            style = MaterialTheme.typography.MainPart2,
            modifier = Modifier
                .padding(start = 2.dp)
        )

    }
}