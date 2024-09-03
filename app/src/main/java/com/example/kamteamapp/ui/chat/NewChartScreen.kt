package com.example.kamteamapp.ui.chat

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import java.lang.reflect.Modifier


@Composable
fun InitDialog(

){
    val showingDialog = remember { mutableStateOf(true) }
    if (showingDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showingDialog.value = false
            },

            title = {
                Text(text = "基本信息表")
            },
            text = {
                Text(text = "副标题")
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
