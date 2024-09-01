package com.example.myapplication.Roomfinal
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
@Dao
interface Connect {

    //======================插入数据=========================
    //插入主信息表数据
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertmainitem(item: Main_item)

    //插入旅游事件表数据
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun inserttravelitem(item: Travel_item)

    //插入天气表数据
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertweatheritem(item: Weather_item)

    //插入详细表数据
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertdetailthingsitem(item: Detailthings_item)

    //====================删除单个数据=============================
    @Delete
    fun deletemainitem(item: Main_item)

    @Delete
    fun deletetravelitem(item: Travel_item)

    @Delete
    fun deleteweatheritem(item: Weather_item)

    @Delete
    fun deletedetailthingsitem(item:Detailthings_item)


    @Query("SELECT * from main_items")
    fun getallmainitems(): Flow<List<Main_item>>

    //从主表找到旅游主信息
    @Query("SELECT * from main_items WHERE id = :id")
    fun getmainitem(id: Int): Flow<Main_item>

    //关联查询接口



    //找到特定旅游id的旅游事件
    @Transaction
    @Query("SELECT * FROM main_items WHERE id = :id")
    fun getMainWithTravelItemsbyid( id : Int): Flow<List<mainwithtravel>>

    @Transaction
    @Query("SELECT * FROM main_items WHERE id = :id")
    fun getMainWithWeatherItemsbyid( id : Int): Flow<List<mainwithweather>>


    //从主表找到旅游事件
    @Transaction
    @Query("SELECT * FROM main_items")
    fun getMainWithTravelItems(): Flow<List<mainwithtravel>>

    //从主表找到天气
    @Transaction
    @Query("SELECT * FROM main_items")
    fun getMainWithWeatherItems(): Flow<List<mainwithweather>>

    //从旅游表找详细事件表
    @Transaction
    @Query("SELECT * FROM travel_items")
    fun getTravelWithDetailthingsItems(): Flow<List<travelwithdetail>>


    //删除所有主表
    @Query("DELETE FROM main_items")
    fun deleteAllmainitems() // New method to delete all items

    //删除所有旅游表
    @Query("DELETE FROM travel_items")
    fun deleteAlltravelitems() // New method to delete all items

    //删除所有天气表
    @Query("DELETE FROM weather_items")
    fun deleteAllweatheritems() // New method to delete all items

    //删除所有详细表
    @Query("DELETE FROM detailthings_items")
    fun deleteAlldetailthingsitems() // New method to delete all items

}