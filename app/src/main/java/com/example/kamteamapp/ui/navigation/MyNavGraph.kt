package com.example.kamteamapp.ui.navigation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.kamteamapp.Utils.ScreenSizeManager
import com.example.kamteamapp.data.TempRes
import com.example.kamteamapp.data.TempRes2
import com.example.kamteamapp.data.TempWeath
import com.example.kamteamapp.data.TempWeath2
import com.example.kamteamapp.ui.chat.ChatDestination
import com.example.kamteamapp.ui.chat.ConversationScreen
import com.example.kamteamapp.ui.home.HomeDestination
import com.example.kamteamapp.ui.home.HomeScreen
import com.example.kamteamapp.ui.intrest.IntrestDesatination
import com.example.kamteamapp.ui.intrest.IntrestScreen
import com.example.kamteamapp.ui.item.Actions
import com.example.kamteamapp.ui.item.Detail.DetailDestination
import com.example.kamteamapp.ui.item.Detail.DetailScreen
import com.example.kamteamapp.ui.item.ItemDetailsDestination
import com.example.kamteamapp.ui.item.ItemDetailsScreen
import com.example.kamteamapp.ui.item.State
import com.example.kamteamapp.ui.item.combineItems


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyNavHost(
    state: State,
    actions: Actions,
    navController: NavHostController,
    modifier: Modifier = Modifier,
){
    NavHost(
        navController = navController,
        startDestination = IntrestDesatination.route,
        modifier = modifier
    ){
        // Home 界面，选择方案
     composable(route = HomeDestination.route){
         HomeScreen(
             navigateToItemUpdate = {
                 navController.navigate("${ItemDetailsDestination.route}/${it}")
             }
         )
     }

        // Dtail 界面，选择细节
    composable(
        route = DetailDestination.routeWithArgss,
        arguments = listOf(navArgument(DetailDestination.itemIdArgs){
            type = NavType.IntType
        })
    ){backStackEntry ->
        DetailScreen(
            itemId = backStackEntry.arguments?.getInt(DetailDestination.itemIdArgs),
            navigateBack = { navController.navigateUp() },
        )
    }
        // 方案细节
     composable(
         route = ItemDetailsDestination.routeWithArgs,
         arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg){
             type = NavType.IntType
         }),
     ){backStackEntry ->
         val isInitialized = remember { mutableStateOf(false) }

         LaunchedEffect(backStackEntry) {
             // 每次进入页面时执行
             isInitialized.value = true
         }

         if (isInitialized.value){
             if (backStackEntry.arguments?.getInt(ItemDetailsDestination.itemIdArg) == 1){
                 actions.setTrvalItem(combineItems(TempRes, TempWeath) )
             } else{
                 actions.setTrvalItem(combineItems(TempRes2, TempWeath2) )
             }
             isInitialized.value = false
         }


         ItemDetailsScreen(
             //itemIdTime=backStackEntry.arguments?.getInt(ItemDetailsDestination.itemIdArg),
             state = actions.get_state(),
             actions = actions,
             //navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
             navigateBack = { navController.navigateUp() },
             navigateToItemUpdate = {
                 navController.navigate("${DetailDestination.route}/${it}")
             },
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
        // 进入的兴趣界面
        composable(route = IntrestDesatination.route){
            IntrestScreen(
                navigateToAIUpdate = {
                    navController.navigate(ChatDestination.route)
                }
            )
        }



    }
}

