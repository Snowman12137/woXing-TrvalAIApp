package com.example.kamteamapp.ui.item

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.data.EMOJI.Plane
import com.example.kamteamapp.data.EMOJI.DestiNation
import com.example.kamteamapp.data.EMOJI.Food
import com.example.kamteamapp.data.Temp_Trval_Items


@Composable
fun CardTopEmoji(
    item: Temp_Trval_Items
){
    var emoji = ""
    if (item.model==0){
        emoji = "$Plane"
    }else if (item.model==1){
        emoji = "$DestiNation"
    }else {
        emoji = "$Food"
    }

    Text(
        text = emoji,
        modifier = Modifier
            .height(20.dp)
    )
    Spacer(modifier = Modifier.width(8.dp))
}


