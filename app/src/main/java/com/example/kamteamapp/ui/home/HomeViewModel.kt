package com.example.kamteamapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.kamteamapp.data.Main_Items
import com.example.kamteamapp.data.Temp_Main_Items
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel:ViewModel(){
    @RequiresApi(Build.VERSION_CODES.O)
    val tempMainItem1 = Temp_Main_Items(
        id = 1,
        time_start = LocalDate.of(2024,7,9), // 使用 LocalDate 实例
        trval_day = 5,
        weathers_id = 1,
        trvals_id = 1,
        name = "实例1",
        other1 = ""
    )
    @RequiresApi(Build.VERSION_CODES.O)
    val tempMainItem2 = Temp_Main_Items(
        id = 2,
        time_start = LocalDate.of(2024,7,9), // 使用 LocalDate 实例
        trval_day = 3,
        weathers_id = 5,
        trvals_id = 5,
        name = "实例2",
        other1 = ""
    )
    // 创建Flow实例
    private val _homeUiState = MutableStateFlow(HomeUiState(itemList = listOf()))
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    init {
        // 将 tempMainItem1 和 tempMainItem2 添加到 homeUiState
        _homeUiState.value = HomeUiState(itemList = listOf(tempMainItem1, tempMainItem2))
    }


}


data class HomeUiState(val itemList: List<Temp_Main_Items> = listOf())
