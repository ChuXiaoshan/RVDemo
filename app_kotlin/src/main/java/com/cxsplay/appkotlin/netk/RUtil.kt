package com.cxsplay.appkotlin.netk

import com.blankj.utilcode.util.LogUtils
import com.cxsplay.appkotlin.net.BasicParamsInterceptor
import com.cxsplay.appkotlin.net.UnsafeOkHttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

/**
 * Created by CxS on 2018/4/12
 */
internal object RUtil {

    private val okHttpClientBuilder: OkHttpClient.Builder
        get() {
            val basicParamsInterceptor = BasicParamsInterceptor.Builder()
                .addParam("channel", "app")
                .build()
            val logInterceptor =
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> LogUtils.d("----->$message") }).setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            return UnsafeOkHttpClient.getUnsafeOkHttpClient().newBuilder()
                .addInterceptor(basicParamsInterceptor)
                .addInterceptor(logInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
        }

    fun getRetrofitBuilder(baseUrl: String): Retrofit.Builder {
        return Retrofit.Builder()
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
    }

}
