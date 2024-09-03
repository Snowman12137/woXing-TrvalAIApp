package com.example.kamteamapp.base.network

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.kamteamapp.base.database.MessageViewModel
import com.example.kamteamapp.base.database.NettoRoomScreen
import com.example.kamteamapp.network.HttpViewModel
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import androidx.lifecycle.viewModelScope

class Nettests : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KamTeamAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}






suspend fun getHotKeyList(): HotKeyList {
    return coroutineScope {
        get {
            setUrl("hotkey/json")
        }
    }
}

data class HotKeyList(
    val data: List<HotKey>? = null
) : HttpResponse()

data class HotKey @JvmOverloads constructor(
    val id: String = "",
    val link: String = "",
    val name: String = "",
    val order: String = "",
    val visible: String = ""
)