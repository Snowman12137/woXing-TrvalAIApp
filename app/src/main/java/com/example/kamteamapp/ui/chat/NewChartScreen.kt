package com.example.kamteamapp.ui.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier


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
                    //TextAndField("出行人数",save_part.value)

                }
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        showingDialog.value = false
                    },
                    //modifier = Modifier
                        //.padding(16.dp)
                ) {
                    Text("按钮")
                }
            },
        )
    }
}


@Composable
fun TextAndField(
    text:String,
    index:Int,
    contents:String
){
    Row (

    ){
        Text(text = text)
        TextField(
            value = contents,
            onValueChange = {newValue->
                //contents = newValue
            }
        )
    }


}


data class Formate(
    var num:String="",
    var start_part:String="",
    var end_part:String="",
    var start_time:String="",
    var datas:String=""
)