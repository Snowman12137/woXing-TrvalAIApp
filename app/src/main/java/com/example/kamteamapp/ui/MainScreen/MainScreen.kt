package com.example.kamteamapp.ui.MainScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kamteamapp.R
import com.example.kamteamapp.ui.HistoryProgram.HistoryProgramScreen
import com.example.kamteamapp.ui.MainScreen.MyScreen.MyScreen
import com.example.kamteamapp.ui.MainScreen.home.HomeScreen
import com.example.kamteamapp.ui.chat.ConversationScreen
import com.example.kamteamapp.ui.theme.KamTeamAppTheme
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    navigateUp :()->Unit,
    onNavigateToHistory :(String)->Unit,
    onNavigateToAIChat :()->Unit,
    onNavigateToUser :()->Unit,
    onNavigateToLogin :()->Unit,
    onNavigateToMain:()->Unit = {},

){
    var navIndex by rememberSaveable { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    val homeListState = rememberLazyListState()
    val navItems = listOf(
        NavigationItem("首页", R.drawable.ic_bottom_bar_home),
        NavigationItem("历史方案", R.drawable.ic_bottom_bar_navigation),
        NavigationItem("新建AI对话", R.drawable.ic_bottom_bar_project),
        NavigationItem("我的", R.drawable.ic_bottom_bar_user),
    )
    Scaffold(
        bottomBar = {
            BottomNavigation(
                items = navItems
            ) {
                //首页双击返回顶部
                if ((it == 0) && (navIndex == 0) && homeListState.canScrollBackward) {
                    scope.launch {
                        homeListState.animateScrollToItem(0)
                    }
                }
                navIndex = it
            }
        }
    ){innerPadding ->
        val saveableStateHolder = rememberSaveableStateHolder()
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            when(navIndex){
                0 -> saveableStateHolder.SaveableStateProvider(navItems[0].label){
                    HomeScreen(

                    )
                }
                1 -> saveableStateHolder.SaveableStateProvider(navItems[1].label){
                    HistoryProgramScreen(
                        navigateUp = navigateUp,
                        onNavigateToMain = onNavigateToMain,
                        navigateToItemUpdate = onNavigateToHistory,
                    )
                }

                2 -> saveableStateHolder.SaveableStateProvider(navItems[2].label){
                    ConversationScreen()
                }

                3 -> saveableStateHolder.SaveableStateProvider(navItems[3].label){
                    MyScreen(
                        onNavigateToLogin= onNavigateToLogin
                    )
                }
            }
        }
    }
}


@Composable
fun BottomNavigation(
    items: List<NavigationItem> = listOf(),
    onClick: (index: Int) -> Unit
) {
    var currItem by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(
        modifier = Modifier.shadow(5.dp),
        containerColor = colorResource(R.color.white)
    ) {
        items.forEachIndexed { index, item ->
            val colorId = if (currItem == index) item.selectedColor else item.unselectedColor
            NavigationBarItem(
                selected = currItem == index,
                onClick = {
                    currItem = index
                    onClick(index)
                },
                icon = {
                    BadgedBox(
                        badge = {
//                            if ("我的" == item.label) {
//                                Badge {
//                                    val badgeNumber = "1"
//                                    Text(
//                                        badgeNumber,
//                                        modifier = Modifier.semantics {
//                                            contentDescription = "$badgeNumber new notifications"
//                                        }
//                                    )
//                                }
//                            }
                        }) {
                        Icon(
                            painter = painterResource(id = item.resId),
                            contentDescription = null,
                            modifier = Modifier.size(25.dp),
                            tint = colorResource(colorId)
                        )
                    }
                },
                label = { Text(text = item.label, fontSize = 13.sp, lineHeight = 13.sp) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = colorResource(item.selectedColor),
                    selectedTextColor = colorResource(item.selectedColor),
                    unselectedIconColor = colorResource(item.unselectedColor),
                    unselectedTextColor = colorResource(item.unselectedColor)
                )
            )
        }
    }
}


data class NavigationItem(
    val label: String,
    val resId: Int,
    val selectedColor: Int = R.color.theme_orange,
    val unselectedColor: Int = R.color.theme
)

@Preview(showBackground = true, backgroundColor = 0xFFF0F0F0)
@Composable
fun WanBottomNavigationPreview() {
    val navItems = listOf(
        NavigationItem("首页", R.drawable.ic_bottom_bar_home),
        NavigationItem("导航", R.drawable.ic_bottom_bar_navigation),
        NavigationItem("项目", R.drawable.ic_bottom_bar_project),
        NavigationItem("我的", R.drawable.ic_bottom_bar_user),
    )
    KamTeamAppTheme { BottomNavigation(items = navItems, onClick = { _ -> }) }
}
