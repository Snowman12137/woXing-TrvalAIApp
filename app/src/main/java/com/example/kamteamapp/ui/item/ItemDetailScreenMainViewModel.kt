package com.example.kamteamapp.ui.item

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kamteamapp.MyUiState
import com.example.kamteamapp.Utils.WideThing
import com.example.kamteamapp.data.Between
import com.example.kamteamapp.data.New_Temp_Trval_Items
import com.example.kamteamapp.data.TempRes
import com.example.kamteamapp.data.TempWeath
import com.example.kamteamapp.data.Temp_Trval_Items
import com.example.kamteamapp.data.Temp_Weather_Items
import com.example.kamteamapp.data.Time
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch



@RequiresApi(Build.VERSION_CODES.O)
class DetailMainViewModel : ViewModel(), Actions {

    val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()


    val hour:Int = 26

    var params:WideThing = WideThing()
    var destiy :Float =0f

    private var generateJob: Job? = null
        set(value) {
            field?.cancel()
            field = value
        }

    fun setParamter(params1:WideThing,destiys:Float){
        params = params.copy(
            Weather_background = params1.Weather_background,
            TimeLang= params1.TimeLang,
            DayLang = params1.DayLang,
            DayHight = params1.DayHight,
            BaseHight= params1.BaseHight,
            BasePadding= params1.BasePadding,
            TimeLangDP = params1.TimeLangDP,
            DayLangDP = params1.DayLangDP,
            screenWidth = params1.screenWidth,
            screenHeight = params1.screenHeight,
            start_part = params1.start_part,
            destiys = destiys
        )
        destiy = destiys
    }

//    init {
//        setTrvalItem(combineItems(TempRes, TempWeath) )
//    }


    override fun get_Paramter():WideThing{
        return params
    }

    override fun get_Size():Pair<Float, Float>{
        return Pair(_state.value.maxX/destiy,_state.value.maxY /destiy  )
    }

    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.O)
    fun generateTrvalItems() {
        generateJob = viewModelScope.launch {
            val currentState = _state.value
            val items = currentState.items.mapIndexed{index,item->
                when(item.hereItem){
                    is DisplayItem.TravelItem->{
                        ListItem(
                            x = (params.start_part*destiy).toInt()+(item.hereItem.trvalitem.day  -1)*params.DayLang,
                            y = time_long(item.hereItem.trvalitem.time.start)+params.BaseHight,
                            long = item.long,
                            hereItem =item.hereItem
                        )
                    }
                    is DisplayItem.WeatherItem->{
                        ListItem(
                            x= (params.start_part*destiy).toInt()+(item.hereItem.weatheritem.number_day-1) * params.DayLang + params.BasePadding,
                            y= params.DayHight,
                            hereItem =item.hereItem
                        )
                    }
                    is DisplayItem.BackGroundItem->{
                        if (index == _state.value.data){
                            ListItem(
                                x= (params.start_part*destiy).toInt(),
                                y= 0,
                                hereItem =DisplayItem.BackGroundItem(
                                    BackGround(
                                        index = 0,
                                        isWeather = true,
                                        weide = (_state.value.data* params.DayLang/destiy).toInt(),
                                        hight = (params.Weather_background /destiy ).toInt(),
                                        color = Color.White
                                    )
                                )
                            )
                        }else{
                            ListItem(
                                x= (params.start_part*destiy).toInt()+index * params.DayLang,
                                y= 0,
                                hereItem =item.hereItem
                            )
                        }
                    }
                    is DisplayItem.TimeLiner->{
                        ListItem(
                            x =0,
                            y=0,
                            hereItem = item.hereItem
                        )
                    }
                    is DisplayItem.New_Temp_Trval->{
                        ListItem(
                            x = (params.start_part*destiy).toInt()+(item.hereItem.new_temp_trval_Items.day  -1)*params.DayLang,
                            y = time_long(item.hereItem.new_temp_trval_Items.time.start)+params.BaseHight,
                            long = item.long,
                            hereItem =item.hereItem
                        )
                    }
                }
            }
            _state.value = currentState.copy(items = items)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun setTrvalItem(Temp: List<DisplayItem>) {

        val res = calculateTrval(Temp)

        _state.value = _state.value.copy(data = res.data,size = res.size, maxX = res.maxX, maxY = res.maxY, items = res.items)
        generateTrvalItems()
    }

    private fun calculateTrval(Temp: List<DisplayItem>):ResultVal{

        val size: Int = Temp.size
        var maxY: Int = params.TimeLang*hour
        var numberday :Int =0



        val items = Temp.map { item ->
            when(item){
                is DisplayItem.TravelItem ->{
                    ListItem(
                        0,
                        0,
                        hereItem =DisplayItem.TravelItem(item.trvalitem)
                    )
                }
                is DisplayItem.WeatherItem->{
                    if (item.weatheritem.number_day> numberday) {
                        numberday = item.weatheritem.number_day
                    }
                    ListItem(
                        0,
                        0,
                        hereItem =DisplayItem.WeatherItem(item.weatheritem)
                    )
                }
                // 理论上这里绝对不会出现BackGroundItem类型
                is DisplayItem.BackGroundItem->{
                    ListItem(
                        0,
                        0,
                        hereItem =DisplayItem.BackGroundItem(BackGround(color = Color.White, weide = 0, hight = 0))
                    )
                }
                is DisplayItem.TimeLiner->{
                    ListItem(
                        0,
                        0,
                        hereItem =DisplayItem.TimeLiner(TimeLinerItem())
                    )
                }
                is DisplayItem.New_Temp_Trval->{
                    ListItem(
                        x = 0,
                        y = 0,
                        long = 0,
                        hereItem =DisplayItem.New_Temp_Trval(item.new_temp_trval_Items)
                    )
                }

            }
        }
        val itembackground: List<ListItem> = List(numberday+1){index->
            ListItem(
                0,
                0,
                hereItem=DisplayItem.BackGroundItem(
                    BackGround(
                        index = index,
                        color = Color.White,
                        weide = (params.DayLang/destiy -params.BasePadding).toInt(), // 这里的Weide和hight应该是DP类型，最后再转化也可以
                        hight = (hour * params.TimeLang/destiy).toInt()  // 因为最后输入modify的size是DP类型
                    )                               // 坐标偏移是px和DP不一样
                )
            )
        }
        val TimeLin:List<ListItem> = List(1){
            ListItem(
                0,
                0,
                hereItem = DisplayItem.TimeLiner(
                    TimeLinerItem()
                )
            )
        }

        val Res =  itembackground + items + TimeLin


        return ResultVal(numberday,size, numberday * params.DayLang  - params.screenWidth + params.BasePadding , maxY - params.screenHeight+ params.BasePadding*destiy.toInt(), Res)
        //return ResultVal(numberday,size, ((numberday * params.DayLang + params.screenWidth )/ destiy).toInt(), ((maxY+params.screenHeight)/destiy).toInt(), Res)
    }

    fun time_long(time:Time):Int{
        return (time.hour*params.TimeLang+time.minite*params.TimeLang/60)
    }

    override fun get_state(): State {
        return _state.value
    }


    private data class ResultVal(
        val data :Int,
        val size: Int,
        val maxX: Int,
        val maxY: Int,
        val items: List<ListItem> // 包含 items 列表
    )
}



data class State(
    val data :Int = 0,
    val size: Int = 0,
    val maxX: Int = 0,
    val maxY: Int = 0,
    var items: List<ListItem> = emptyList(),
)

// 旅游事件的存储
data class ListItem(
    val x: Int,
    var y: Int,
    var long : Int = 0,
    val hereItem : DisplayItem
)


sealed class DisplayItem {
    data class TravelItem(val trvalitem: Temp_Trval_Items) : DisplayItem()
    data class WeatherItem(val weatheritem: Temp_Weather_Items) : DisplayItem()
    data class BackGroundItem(val backGround: BackGround):DisplayItem()
    data class TimeLiner(val timeliner :TimeLinerItem):DisplayItem()
    data class New_Temp_Trval(val new_temp_trval_Items: New_Temp_Trval_Items):DisplayItem()
}

data class TimeLinerItem(
    val sunraise : Int =0,
    val night : Int =0,
)

data class BackGround(
    val index : Int = 0,
    val isWeather : Boolean = false,
    val weide : Int,
    val hight :Int,
    val color:Color
)


@Stable
interface Actions {

    fun setTrvalItem(Temp: List<DisplayItem>)
    //fun ChangeXY_Top(statess: State,offsetStateX: Int,offsetStateY: Int,longs: Int =0 ,itemss: ListItem ?=null)
    fun get_Paramter():WideThing
    fun get_Size():Pair<Float, Float>
    fun get_state():State
}


fun TimeString(time: Between):String{
    if (time.end !=null){
        return  String.format("%d%d:%d%d-%d%d:%d%d",
            time.start.hour/10,
            time.start.hour%10,
            time.start.minite/10,
            time.start.minite%10,
            time.end.hour/10,
            time.end.hour%10,
            time.end.minite/10,
            time.end.minite%10,
        )
    }else{
        return String.format("%d%d:%d%d",
            time.start.hour/10,
            time.start.hour%10,
            time.start.minite/10,
            time.start.minite%10,
            )
    }
}


fun ChangeXY_Top(statess: State,offsetStateX: Int,offsetStateY: Int) {
    statess.items.forEach { items->
        when(items.hereItem){
            is DisplayItem.TravelItem ->{
                    items.copy(
                        x = items.x,
                        y = items.y,
                        long = items.long,
                        hereItem = items.hereItem
                    )
            }
            is DisplayItem.WeatherItem->{
                items.copy(
                    items.x,
                    offsetStateY,
                    hereItem = items.hereItem
                )
            }
            is DisplayItem.BackGroundItem->{
                items.copy(
                    x = items.x,
                    y = items.y,
                    hereItem = items.hereItem
                )
            }
            is DisplayItem.TimeLiner->{
                items.copy(
                    offsetStateX,
                    items.y,
                    hereItem = items.hereItem
                )
            }
            is DisplayItem.New_Temp_Trval->{
                items.copy(
                    x = items.x,
                    y = items.y,
                    long = items.long,
                    hereItem = items.hereItem
                )
            }

        }
    }

//    val items = statess.items.map { item ->
//            when (item.hereItem) {
//                is DisplayItem.TravelItem -> {
//                    if (longs != 0 && itemss != null && item == itemss) {
//                        ListItem(
//                            x = item.x,
//                            y = offsetStateY + longs,
//                            long = item.long,
//                            hereItem = item.hereItem
//                        )
//                    } else {
//                        ListItem(
//                            x = item.x,
//                            y = item.y,
//                            long = item.long,
//                            hereItem = item.hereItem
//                        )
//                    }
//                }
//
//                is DisplayItem.WeatherItem -> {
//                    ListItem(
//                        item.x,
//                        offsetStateY,
//                        hereItem = item.hereItem
//                    )
//                }
//
//                is DisplayItem.BackGroundItem -> {
//                    if (item.hereItem.backGround.isWeather) {
//                        ListItem(
//                            x = item.x,
//                            y = item.y,
//                            hereItem = item.hereItem
//                        )
//                    } else {
//                        ListItem(
//                            x = item.x,
//                            y = item.y,
//                            hereItem = item.hereItem
//                        )
//                    }
//                }
//                is DisplayItem.TimeLiner->{
//                    ListItem(
//                        offsetStateX,
//                        item.y,
//                        hereItem = item.hereItem
//                    )
//                }
//                is DisplayItem.New_Temp_Trval->{
//                    ListItem(
//                        x = item.x,
//                        y = item.y,
//                        long = item.long,
//                        hereItem = item.hereItem
//                    )
//                }
//            }
//        }
//    itemss?.copy(items = items)
}
