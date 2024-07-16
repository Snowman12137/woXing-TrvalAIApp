package com.example.kamteamapp.ui.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kamteamapp.ui.chat.ChatDestination
import com.example.kamteamapp.ui.chat.ConversationScreen
import com.example.kamteamapp.ui.home.HomeDestination
import com.example.kamteamapp.ui.home.HomeScreen
import com.example.kamteamapp.ui.intrest.IntrestScreen
import com.example.kamteamapp.ui.item.Actions
import com.example.kamteamapp.ui.item.ItemDetailsDestination
import com.example.kamteamapp.ui.item.ItemDetailsScreen
import com.example.kamteamapp.ui.item.State


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyNavHost(
    state: State,
    actions: Actions,
    navController: NavHostController,
    context: Context,
    modifier: Modifier = Modifier,
){
    NavHost(
        navController = navController,
        startDestination = ItemDetailsDestination.route,
        modifier = modifier
    ){

     composable(route = HomeDestination.route){
         HomeScreen(
             navigateToItemUpdate = {
                 navController.navigate("${ItemDetailsDestination.route}/${it}")
             }
         )
     }
        //
     composable(
         route = ItemDetailsDestination.routeWithArgs,
         arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg){
             type = NavType.IntType
         })
     ){
         ItemDetailsScreen(
             state = state,
             actions = actions,
             context=context,
             //navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
             navigateBack = { navController.navigateUp() },
         )
     }
        // 主界面 对话界面
        composable(
            route = ChatDestination.route
        ){
            ConversationScreen(
                navigateToItemUpdate = {
                    navController.navigate(HomeDestination.route)
                }
            )

        }
        composable(route = ItemDetailsDestination.route){
            IntrestScreen(
                navigateToAIUpdate = {
                    navController.navigate(ChatDestination.route)
                }
            )
        }

    }
}

