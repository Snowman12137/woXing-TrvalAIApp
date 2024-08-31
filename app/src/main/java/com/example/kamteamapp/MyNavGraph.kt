package com.example.kamteamapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kamteamapp.data.New_Temp_Res
import com.example.kamteamapp.data.TempRes
import com.example.kamteamapp.data.TempRes2
import com.example.kamteamapp.data.TempWeath
import com.example.kamteamapp.data.TempWeath2
import com.example.kamteamapp.ui.HistoryProgram.HistoryProgramScreen
import com.example.kamteamapp.ui.chat.ConversationScreen
import com.example.kamteamapp.ui.MainScreen.MainScreen
import com.example.kamteamapp.ui.intrest.IntrestScreen
import com.example.kamteamapp.ui.item.Actions
import com.example.kamteamapp.ui.item.Detail.DetailScreen
import com.example.kamteamapp.ui.item.ItemDetailsScreen
import com.example.kamteamapp.ui.item.State
import com.example.kamteamapp.ui.item.combineItems
import com.example.kamteamapp.ui.login.LoginPage
import com.example.kamteamapp.ui.login.register.RegisterPage


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyNavHost(
    state: State,
    actions: Actions,
    modifier: Modifier = Modifier,
){
    val navController = rememberNavController()
    val MyNavActions = remember(navController) { MyNavActions(navController) }
    NavHost(
        navController = navController,
        startDestination = MyDestinations.MAIN_SCREEN,
        modifier = modifier
    ){

        composable(MyDestinations.MAIN_SCREEN){
            MainScreen(
                navigateUp = {MyNavActions.navigateUp()},
                onNavigateToHistory = {MyNavActions.navigateToDetailItem(it)},
                onNavigateToAIChat = { MyNavActions.navigateToChat() },
                onNavigateToUser = {},
                onNavigateToLogin = {MyNavActions.navigateLoginDesatinations()},
                onNavigateToMain = {MyNavActions.navigateToMainScreen()},
            )
        }

        // 登录界面
        composable(MyDestinations.LOGIN_ROUTE){
            LoginPage(
                navigateToAIUpdate = { MyNavActions.navigateToInterest() },
                navigateRegister={ MyNavActions.navigateToRegister() },
                navigateBack = { MyNavActions.popBackStack(MyDestinations.MAIN_SCREEN) }
            )
        }

        // 注册界面
        composable(MyDestinations.REGISTER){
            RegisterPage(navigateBack = { MyNavActions.navigateToMainScreen() })
        }


        // 进入的兴趣界面
        composable(MyDestinations.INTREST){
            IntrestScreen(
                //navigateToAIUpdate = { MyNavActions.navigateToMainScreen() }
                navigateToAIUpdate = { MyNavActions.navigateToChat() }
            )
        }

        composable(MyDestinations.HISTORY_PROGRAM){
            HistoryProgramScreen(
                navigateUp = {MyNavActions.navigateUp()},
                onNavigateToMain = {MyNavActions.navigateToMainScreen()},
                navigateToItemUpdate = {MyNavActions.navigateToDetailItem(it)}
            )
        }


        // Dtail 界面，选择细节
    composable("${MyDestinations.DETAIL}/{cid}"){ backStackEntry ->
        val cid = backStackEntry.arguments?.getString("cid").toString()
        DetailScreen(
            itemId =  cid.toInt(),
            navigateBack = { navController.navigateUp() },
            onNavigateToMain = { MyNavActions.navigateToMainScreen() }
        )
    }
        // 方案细节
     composable("${MyDestinations.ITEM_DETAILS}/{id}")
     {backStackEntry ->
         val id = backStackEntry.arguments?.getString("id").toString()
         val isInitialized = remember { mutableStateOf(false) }

         LaunchedEffect(backStackEntry) {
             // 每次进入页面时执行
             isInitialized.value = true
         }

         if (isInitialized.value){
             if (id == "1"){
                 actions.setTrvalItem(combineItems(New_Temp_Res,TempRes, TempWeath) )
             } else{
                 actions.setTrvalItem(combineItems(New_Temp_Res,TempRes2, TempWeath2) )
             }
             isInitialized.value = false
         }


         ItemDetailsScreen(
             state = actions.get_state(),
             actions = actions,
             navigateBack = { navController.navigateUp() },
             navigateToItemUpdate = { MyNavActions.navigateToDetail(it.toString()) },
             onNavigateToMain = { MyNavActions.navigateToMainScreen() }
         )
     }
        // 主界面 对话界面
        composable(MyDestinations.CHAT_PART){
            ConversationScreen(
                //navigateToItemUpdate = { MyNavActions.navigateToHistoryProgram() }
            )

        }
    }
}

class MyNavActions(
    private val navController: NavHostController
){
    val navigateLoginDesatinations:() ->Unit = {
        navigate(MyDestinations.LOGIN_ROUTE)
    }
    val navigateToInterest:() ->Unit = {
        navigate(MyDestinations.INTREST)
    }
    val navigateToChat:()->Unit = {
        navigate(MyDestinations.CHAT_PART)
    }
    val navigateToHistoryProgram:()->Unit = {
        navigate(MyDestinations.HISTORY_PROGRAM)
    }
    val navigateToMainScreen:()->Unit = {
        navigate(MyDestinations.MAIN_SCREEN)
    }
    val navigateToDetail:(id:String)->Unit = {
        navigate(MyDestinations.DETAIL + "/$it")
    }

    val navigateToDetailItem:(id:String)->Unit = {
        navigate(MyDestinations.ITEM_DETAILS + "/$it")
    }
    val navigateToRegister:()->Unit = {
        navigate(MyDestinations.REGISTER)
    }
    val navigateUp: () -> Unit = {
        if (!navController.navigateUp()) {
            navigate(MyDestinations.MAIN_SCREEN)
        }
    }
    val popBackStack: (route: String) -> Unit = {
        navController.popBackStack(it, false)
    }





    fun navigate(route: String) {
        return navController.navigate(route)
//        navController.graph.findNode(route) ?: return
//        if (requiredLoginRoute(route)) {
//            WanHelper.getUser {
//                navController.navigate(if (it.isLogin()) {
//                    WanDestinations.LOGIN_ROUTE
//                } else {
//                    route
//                })
//            }
//        } else {
//            navController.navigate(route)
//        }
    }
}

object MyDestinations{
    const val CHAT_PART = "chatpart"
    const val DETAIL = "detail"
    const val HISTORY_PROGRAM = "history_program"
    const val INTREST = "intrest"
    const val LOGIN_ROUTE = "login_route"
    const val MAIN_SCREEN = "main_screen"
    const val ITEM_DETAILS = "item_details"
    const val REGISTER = "register"
}

