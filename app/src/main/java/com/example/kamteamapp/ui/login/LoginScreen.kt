package com.example.kamteamapp.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import com.example.kamteamapp.ui.theme.login_body1
import com.example.kamteamapp.ui.theme.login_body2
import com.example.kamteamapp.ui.theme.login_botton
import com.example.kamteamapp.ui.theme.login_h1

@Composable
fun LoginPage() {
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {
        LoginTitle()
        LoginInputBox()
        LoginHintWithUnderLine();
        LoginButton()
    }
}

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
fun LoginInputBox() {
    Column {
        LoginTextField(placeHolder = "电话/电子邮箱")
        Spacer(modifier = Modifier.height(8.dp))
        LoginTextField(placeHolder = "登录密码(6+)")
    }
}

@Composable
fun LoginTextField(placeHolder: String) {
    OutlinedTextField(value = "", onValueChange = {},
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
fun LoginHintWithUnderLine() {
    Column(modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 16.dp)) {
        Text(
            text = "还没有账号->注册账号",
            style = MaterialTheme.typography.login_body2,
            color = MaterialTheme.colorScheme.primary,
            textDecoration = TextDecoration.Underline
        )
    }
}



@Composable
fun BottomText() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = " to Our",
            style = MaterialTheme.typography.login_body2,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Privacy policy.",
            style = MaterialTheme.typography.login_body2,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun LoginButton() {
    val context = LocalContext.current
    Button(
        onClick = { }, //goHomePage(context) },
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


@Preview
@Composable
fun show(){
    KamTeamAppTheme {
        LoginPage()
    }
}