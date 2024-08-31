package com.example.kamteamapp.componets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.ui.login.register.RegisterViewModel
import com.example.kamteamapp.ui.theme.login_body1
import com.example.kamteamapp.ui.theme.login_botton
import com.example.kamteamapp.ui.theme.login_h1

@Composable
fun RegisterTitle() {
    Text(
        text = "使用手机号/邮箱进行注册",
        modifier = Modifier
            .fillMaxWidth()
            .paddingFromBaseline(
                top = 184.dp,
                bottom = 16.dp
            ),
        style = MaterialTheme.typography.login_h1,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center
    )
}

@Composable
fun RegisterInputBox(
    username: MutableState<String>,
    password: MutableState<String>,
    repassword: MutableState<String>
) {
    Column {
        RegisterTextField(placeHolder = "电话/电子邮箱注册",username)
        Spacer(modifier = Modifier.height(8.dp))
        RegisterTextField(placeHolder = "登录密码设置",password)
        Spacer(modifier = Modifier.height(8.dp))
        RegisterTextField(placeHolder = "再次输入一遍密码",repassword)
    }
}

@Composable
fun RegisterTextField(placeHolder: String,thing: MutableState<String>) {
    OutlinedTextField(
        value = thing.value,
        onValueChange = { newValue ->
            thing.value = newValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(MaterialTheme.shapes.small)
            .border(1.dp, MaterialTheme.colorScheme.primary),
        placeholder = {
            Text(
                text = placeHolder,
                style = MaterialTheme.typography.login_body1,
                color = MaterialTheme.colorScheme.primary
            )
        }
    )
}

@Composable
fun RegisterButton(
    navigateBack: () -> Unit,
    username: MutableState<String>,
    password: MutableState<String>,
    repassword: MutableState<String>,
    viewModel: RegisterViewModel
) {
    val context = LocalContext.current
    Button(
        onClick = {
            if (viewModel.login(username = username.value, password = password.value, repassowrd = repassword.value)) {
                navigateBack()
            }
        }, //goHomePage(context) },
        enabled = username.value.isNotEmpty() && password.value.isNotEmpty() && repassword.value.isNotEmpty(),
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onBackground)
    ) {
        Text(
            text = "注册", style = MaterialTheme.typography.login_botton,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
