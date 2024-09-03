//package com.example.kamteamapp.base.prase
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.activity.viewModels
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import com.example.kamteamapp.base.database.MessageViewModel
//import com.example.kamteamapp.ui.theme.KamTeamAppTheme
//
//class databasetest : ComponentActivity() {
//    private val messageViewModel: MessageViewModel by viewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            KamTeamAppTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MyApp()
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun MyApp() {
//    InsertMessageItem(message = data, id = 23)
//    val messageItem = getMessageItemById( 2)
//    val result = messageItem?.let { parseTravelData(it.message) }
//    val a = result?.main
//    val b = result?.weather
//    val c = result?.trval
//
//    LazyColumn {
//        item {
//            Text("Message Items=========================================================================================================")
//        }
//        item {
//            Text(text = "a is $a")
//        }
//        item {
//            Text(text = "b is $b")
//        }
//    }
//}
//
//
//
