package com.example.kamteamapp.network
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    private val httpViewModel: HttpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KamTeamAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MarsPhotosScreen(httpViewModel)
                }
            }
        }
    }
}

@Composable
fun MarsPhotosScreen(httpViewModel: HttpViewModel) {
    val marsUiState by httpViewModel.marsUiState.collectAsState()
//    var message by remember { mutableStateOf("") }
    var message ="我们想去西安旅游，我们一共四个人，想玩三天，帮我规划下旅游行程"
    Column {
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "message is $message")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                httpViewModel.getinformation(message)
            },
            modifier = Modifier
                .width(200.dp) // 设置按钮宽度为200dp
                .height(50.dp) // 设置按钮高度为50dp
        ) {
            Text(text = "Fetch Mars Photos")
        }
        when (marsUiState) {
            is MarsUiState.Loading -> {
                Text(text = "Loading...")
            }
            is MarsUiState.Success -> {
                val photos = (marsUiState as MarsUiState.Success).photos
                Text(text = "Success")
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = photos)
            }
            is MarsUiState.Error -> {
                Text(text = "Error fetching data")
            }
        }
    }
}
