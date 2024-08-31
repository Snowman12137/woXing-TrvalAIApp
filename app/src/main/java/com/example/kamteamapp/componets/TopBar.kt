package com.example.kamteamapp.componets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.MyNavHost
import com.example.kamteamapp.R
import com.example.kamteamapp.ui.item.Actions
import com.example.kamteamapp.ui.item.State
//import com.example.inventory.R



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyApp(state: State, actions: Actions) {
    MyNavHost(state = state, actions = actions)
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    navigateUp: () -> Unit ,
    onNavigateToMain: () -> Unit ,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .background(colorResource(R.color.theme))
            .fillMaxWidth()
            .height(45.dp)
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(Modifier.width(15.dp))
        Icon(
            imageVector = Icons.Filled.ArrowBackIosNew,
            tint = colorResource(R.color.white),
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp, 5.dp, 0.dp, 5.dp)
                .clickable { navigateUp() }
            )
        Box (
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .clipToBounds()
                //.background(colorResource(R.color.three_nine_gray))
                .weight(1f)
                .fillMaxHeight(),
            contentAlignment = Alignment.CenterStart
        ){

        }
        IconButton(
            modifier = Modifier.height(45.dp),
            onClick = {onNavigateToMain()}
        ) {
            Icon(
                Icons.Filled.Home,
                contentDescription = null,
                tint = colorResource(R.color.white)
            )
        }


    }
}

