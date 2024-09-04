package com.example.kamteamapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.kamteamapp.ui.item.DetailMainViewModel
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kamteamapp.Utils.ScreenSizeManager
import com.example.kamteamapp.base.databasefinal.DataHelper

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataHelper.init(this)
        enableEdgeToEdge()
        setContent {
            KamTeamAppTheme {

                //val state by viewModel.state.collectAsStateWithLifecycle()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MyNavHost(this)

                }
            }
        }
    }
}
