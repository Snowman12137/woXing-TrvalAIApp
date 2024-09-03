package com.example.kamteamapp.ui.chat

import androidx.compose.ui.Modifier
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.ui.theme.KamTeamAppTheme


@Preview
@Composable
fun show(){
    KamTeamAppTheme {
        InitDialog()
    }
}

@Composable
fun InitDialog(

){
    val showingDialog = remember { mutableStateOf(true) }
    val save_part = remember { mutableStateOf(Formate()) }
    if (showingDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showingDialog.value = false
            },

            title = {
                Text(text = "基本信息表")
            },
            text = {
                Column(

                ) {
                    TextAndField("出行人数",save_part.value.num)
                    Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))
                    TextAndField("起始地点",save_part.value.start_part)
                    Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))
                    TextAndField("出行终点",save_part.value.end_part)
                    Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))
                    TextAndField("出发时间",save_part.value.start_time)
                    Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))
                    TextAndField("出行天数",save_part.value.datas)
                }
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        showingDialog.value = false
                    },
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Text("确定输入信息")
                }
            },
        )
    }
}


@Composable
fun TextAndField(
    text:String,
    contents:MutableState<String>
){
    Row (
        modifier = Modifier
            .padding(2.dp)
    ){
        Text(text = text)
        Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
        OutlinedTextField(
            value = contents.value,
            onValueChange = {newValue->
                //contents = newValue
                contents.value = newValue
            },
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .height(30.dp)
                .fillMaxWidth(0.5f)
                .border(1.dp, MaterialTheme.colorScheme.primary)
        )
    }


}


data class Formate(
    var num: MutableState<String> =mutableStateOf(""),
    var start_part:MutableState<String> =mutableStateOf(""),
    var end_part:MutableState<String> =mutableStateOf(""),
    var start_time:MutableState<String> =mutableStateOf(""),
    var datas:MutableState<String> =mutableStateOf("")
)