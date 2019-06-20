package com.cxsplay.vmcoroutines.net

import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by CxS on 2019/6/20 14:13.
 * Description:
 */
object RetrofitClient {

    private val okHttpClientBuilder: OkHttpClient.Builder
        get() {
            val logInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Logger.d(message) }).setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
            val basicInterceptor: BasicParamsInterceptor = BasicParamsInterceptor.Builder()
                .addHeaderLine("Connection: Close")
                .build()
            return UnsafeOkHttpClient.unsafeOkHttpClient.newBuilder()
                .addInterceptor(basicInterceptor)
                .addInterceptor(logInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
        }

    fun getService(): ApiService =
        Retrofit.Builder()
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.1.112:8080")
            .build()
            .create(ApiService::class.java)
}