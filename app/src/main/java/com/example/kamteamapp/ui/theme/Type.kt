package com.example.kamteamapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.kamteamapp.R
import org.w3c.dom.Text

// Set of Material typography styles to start with

//val myFontFamily = FontFamily(
//    Font(R.font.angelina),
//    Font(R.font.ar),
//    Font(R.font.dingtalkjinbuti),
//    Font(R.font.emaustin)
//)

val typography = Typography(
    headlineSmall = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.ar)),
        //fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.5.sp
    )
)

val Typography.Weather1:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ar)),
            fontSize = 14.sp,
            lineHeight = 10.sp,
            letterSpacing = 0.5.sp
        )
    }


val Typography.Weather2:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ab)),
            fontSize = 18.sp,
            lineHeight = 10.sp,
            letterSpacing = 0.5.sp
        )
    }

val Typography.Weather3:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ab)),
            fontSize = 20.sp,
            lineHeight = 10.sp,
            letterSpacing = 0.5.sp
        )
    }
val Typography.Weather4:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ar)),
            fontSize = 20.sp,
            lineHeight = 10.sp,
            letterSpacing = 0.5.sp
        )
    }


val Typography.Card1:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.dingtalkjinbuti)),
            fontSize = 20.sp,
            lineHeight = 21.sp,
            letterSpacing = 0.5.sp
        )
    }

val Typography.Card2Time:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ligconsolataregular)),
            fontSize = 15.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.sp
        )
    }