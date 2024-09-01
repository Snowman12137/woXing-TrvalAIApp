package com.example.kamteamapp.network
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(500, TimeUnit.SECONDS) // 连接超时
    .readTimeout(500, TimeUnit.SECONDS)    // 读取超时
    .writeTimeout(500, TimeUnit.SECONDS)   // 写入超时
    .build()

// 将 BASE_URL 设置为主机部分
//private const val BASE_URL = "https://suggest.taobao.com/"
//private  const val BASE_URL = "https://yiyan.baidu.com/"
private const val BASE_URL = "http://39.100.70.79:443/"

// 构建和创建 Retrofit 对象
private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

// 定义 Retrofit 服务接口
interface MarsApiService {
//    @GET(".")
    //@GET("sug")
    //@GET("chat/4565847819")
//    @GET("AI_travel_get")
    @GET("return_json")
    suspend fun getinputinformation(
        @Query("message") message: String,
//        @Query("name") name: String,
//        @Query("age") age: Int
        //@Query("code") code: String,
        //@Query("q") q: String,
        //@Query("appid") appid: Int,
        //@Query("bk_key") bkKey: String
    ): String
}

// 创建一个单例对象来访问 API 服务
object MarsApi {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}

class HttpApiServer {
}
