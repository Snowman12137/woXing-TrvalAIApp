package com.example.kamteamapp.componets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.ui.login.LoginViewModel
import com.example.kamteamapp.ui.theme.login_body1
import com.example.kamteamapp.ui.theme.login_body2
import com.example.kamteamapp.ui.theme.login_botton
import com.example.kamteamapp.ui.theme.login_h1


@Composable
fun LoginTitle() {
    Text(
        text = "使用手机号/邮箱进行登录",
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
fun LoginInputBox(
    username: MutableState<String>,
    password: MutableState<String>
) {
    Column {
        LoginTextField(placeHolder = "电话/电子邮箱",username)
        Spacer(modifier = Modifier.height(8.dp))
        LoginTextField(placeHolder = "登录密码(6+)",password)
    }
}

@Composable
fun LoginTextField(placeHolder: String,thing: MutableState<String>) {
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
fun LoginHintWithUnderLine(
    navigateRegister: () -> Unit,
) {
    Column(
        modifier = Modifier
            .paddingFromBaseline(top = 24.dp, bottom = 16.dp)
            .clickable { navigateRegister() }
    ) {
        Text(
            text = "还没有账号->注册账号",
            style = MaterialTheme.typography.login_body2,
            color = MaterialTheme.colorScheme.primary,
            textDecoration = TextDecoration.Underline
        )
    }
}


@Composable
fun LoginButton(
    navigateToAIUpdate: () -> Unit,
    username: MutableState<String>,
    password: MutableState<String>,
    viewModel: LoginViewModel
) {
    val context = LocalContext.current
    Button(
        onClick = {
            if (viewModel.login(username = username.value, password = password.value)) {
                navigateToAIUpdate()
            }
        }, //goHomePage(context) },
        enabled = username.value.isNotEmpty() && password.value.isNotEmpty(),
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onBackground)
    ) {
        Text(
            text = "登录", style = MaterialTheme.typography.login_botton,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}