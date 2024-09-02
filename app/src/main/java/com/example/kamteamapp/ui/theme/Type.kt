package com.example.kamteamapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
// login部分

val Typography.login_h1:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ab)),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }

val Typography.login_body1:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ab)),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }

val Typography.login_body2:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ab)),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }

val Typography.login_botton:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ab)),
            fontSize = 20.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }



// other
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
            fontSize = 18.sp,
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
            fontWeight = FontWeight.Bold,
            lineHeight = 16.sp,
            letterSpacing = 0.7.sp,
            color = com.example.kamteamapp.ui.theme.Card2Time
        )
    }

val Typography.Card3Content1:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.fangzhengkaisong)),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            lineHeight = 21.sp,
            letterSpacing = 0.5.sp
        )
    }

val Typography.Card4Content2:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.fangzhengkai)),
            fontSize = 14.sp,
            lineHeight = 15.sp,
            letterSpacing = 0.5.sp
        )
    }

val Typography.Card5Content3:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.fangzhengkai)),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 15.sp,
            letterSpacing = 0.5.sp
        )
    }

val Typography.Card6Title:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.dingtalkjinbuti)),
            //fontWeight = FontWeight.Bold,
            fontSize = 27.sp,
            lineHeight = 15.sp,
            letterSpacing = 0.5.sp
        )
    }

val Typography.Card7Content:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ar)),
            //fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            lineHeight = 15.sp,
            letterSpacing = 0.5.sp
        )
    }


val Typography.Card7ContentMoney:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.fangzhengkaisong)),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 15.sp,
            letterSpacing = 0.5.sp
        )
    }

val Typography.Card8Res:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.fangzhengkai)),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 15.sp,
            letterSpacing = 0.5.sp,
            color = Color(0xFF3A0092)
        )
    }

val Typography.Interest1:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.dingtalkjinbuti)),
            fontSize = 30.sp,
            lineHeight = 21.sp,
            letterSpacing = 1.sp
        )
    }

val Typography.Interest2:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.dingtalkjinbuti)),
            color = Color(0x80000000),
            fontSize = 25.sp,
            lineHeight = 21.sp,
            letterSpacing = 1.sp
        )
    }


val Typography.Interest3:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.dingtalkjinbuti)),
            fontSize = 20.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.sp
        )
    }

val Typography.Detail1:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.dingtalkjinbuti)),
            fontSize = 30.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.sp
        )
    }

val Typography.Detail2:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.fangzhengkai)),
            fontSize = 20.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.sp
        )
    }

val Typography.Detail3:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ligconsolataregular)),
            fontSize = 25.sp,
            lineHeight = 27.sp,
            letterSpacing = 1.sp
        )
    }

val Typography.TimeLiner:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ab)),
            color = Color.White,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold
        )
    }



val Typography.TimeLiner2:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ar)),
            color = Color.White,
            fontSize = 20.sp,
            lineHeight = 30.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold
        )
    }


val Typography.MainPart1:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.dingtalkjinbuti)),
            fontSize = 40.sp,
            lineHeight = 30.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold
        )
    }

val Typography.MainPart2:TextStyle
    @Composable
    get(){
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.ab)),
            fontSize = 15.sp,
            lineHeight = 30.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Bold
        )
    }