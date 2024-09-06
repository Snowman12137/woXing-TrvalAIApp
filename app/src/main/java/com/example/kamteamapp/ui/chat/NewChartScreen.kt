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
import com.example.kamteamapp.ui.theme.login_body1


//@Preview
//@Composable
//fun show(){
//    KamTeamAppTheme {
//        InitDialog()
//    }
//}

@Composable
fun InitDialog(
    updataInit: () -> Unit,
    gotoPOST: (String) -> Unit,
) {
    val showingDialog = remember { mutableStateOf(true) }
    //val savePart = remember { mutableStateOf(Formate()) } // 这里管理状态
    val num = remember { mutableStateOf("") }
    val start_part = remember { mutableStateOf("") }
    val end_part = remember { mutableStateOf("") }
    val start_time = remember { mutableStateOf("") }
    val datas = remember { mutableStateOf("") }

    if (showingDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showingDialog.value = false
            },

            title = {
                Text(text = "基本信息表")
            },
            text = {
                Column {
                    TextAndField("出行人数", num)
                    Spacer(modifier = Modifier.height(8.dp))
                    TextAndField("起始地点", start_part)
                    Spacer(modifier = Modifier.height(8.dp))
                    TextAndField("出行终点", end_part)
                    Spacer(modifier = Modifier.height(8.dp))
                    TextAndField("出发时间", start_time)
                    Spacer(modifier = Modifier.height(8.dp))
                    TextAndField("出行天数", datas)
                }
            },

            confirmButton = {
                TextButton(
                    onClick = {
                        showingDialog.value = false
                        updataInit()
                        gotoPOST(
                            """
                            {
                                "start_location":"${start_part.value}",
                                "destination":"${end_part.value}",
                                "start_data":"${start_time.value}",
                                "duration":"${datas.value}",
                                "people_count":"${num.value}",
                                "budget_per_person": 2000,
                                "preferences":"年轻化一些的景点"
                            }
                            """.trimIndent()
                        )
                    },
                    modifier = Modifier.padding(5.dp)
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
        //Text(text = text)
        Spacer(modifier = androidx.compose.ui.Modifier.width(8.dp))
        OutlinedTextField(
            value = contents.value,
            onValueChange = {newValue->
                //contents = newValue
                contents.value = newValue
            },
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .height(60.dp)
                .fillMaxWidth(0.5f)
                .border(1.dp, MaterialTheme.colorScheme.primary),
            placeholder = {
                Text(
                    text = text,
                    style = MaterialTheme.typography.login_body1,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        )
    }


}


data class Formate(
    val num: String = "",
    val start_part: String = "",
    val end_part: String = "",
    val start_time: String = "",
    val datas: String = ""
)