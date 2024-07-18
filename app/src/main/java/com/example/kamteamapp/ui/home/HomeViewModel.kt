package com.example.kamteamapp.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.kamteamapp.data.Main_Items
import com.example.kamteamapp.data.Temp_Main_Items
import com.example.kamteamapp.data.tempMainItem1
import com.example.kamteamapp.data.tempMainItem2
import com.example.kamteamapp.data.tempMainItem3
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel:ViewModel(){
    // 创建Flow实例
    private val _homeUiState = MutableStateFlow(HomeUiState(itemList = listOf()))
    val homeUiState: StateFlow<HomeUiState> = _homeUiState

    init {
        // 将 tempMainItem1 和 tempMainItem2 添加到 homeUiState
        _homeUiState.value = HomeUiState(itemList = listOf(tempMainItem1, tempMainItem2,tempMainItem3))
    }


}


data class HomeUiState(val itemList: List<Temp_Main_Items> = listOf())




