package com.example.kamteamapp.ui.login.register

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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kamteamapp.R
import com.example.kamteamapp.componets.RegisterButton
import com.example.kamteamapp.componets.RegisterInputBox
import com.example.kamteamapp.componets.RegisterTitle
import com.example.kamteamapp.ui.login.LoginViewModel
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import com.example.kamteamapp.ui.theme.login_body1
import com.example.kamteamapp.ui.theme.login_body2
import com.example.kamteamapp.ui.theme.login_botton
import com.example.kamteamapp.ui.theme.login_h1



@Composable
fun RegisterPage(
    navigateBack: () -> Unit,
    viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val repassword = remember { mutableStateOf("") }

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {
        RegisterTitle()
        RegisterInputBox(username,password,repassword)
        RegisterButton(navigateBack,username,password,repassword,viewModel)
    }
}



@Preview
@Composable
fun show(){
    KamTeamAppTheme {
        RegisterPage(navigateBack={})
    }
}