package com.example.kamteamapp.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import com.example.kamteamapp.componets.LoginButton
import com.example.kamteamapp.componets.LoginHintWithUnderLine
import com.example.kamteamapp.componets.LoginInputBox
import com.example.kamteamapp.componets.LoginTitle
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import com.example.kamteamapp.ui.theme.login_body1
import com.example.kamteamapp.ui.theme.login_body2
import com.example.kamteamapp.ui.theme.login_botton
import com.example.kamteamapp.ui.theme.login_h1


@Composable
fun LoginPage(
    navigateToAIUpdate: () -> Unit,
    navigateRegister: () -> Unit,
    navigateBack: () -> Unit ,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Scaffold(
        topBar = {

        }
    ) {innerPadding ->
        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
                .padding(innerPadding)
        ) {
            LoginTitle()
            LoginInputBox(username,password)
            LoginHintWithUnderLine(navigateRegister);
            LoginButton(navigateToAIUpdate,username,password,viewModel)
        }
    }


}



//@Preview
//@Composable
//fun show(){
//    KamTeamAppTheme {
//        LoginPage(navigateToAIUpdate={}, navigateRegister = {})
//    }
//}