package com.cxsplay.appkotlin.netk

import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by CxS on 2019/5/21
 */
internal object RUtil {

    private val okHttpClientBuilder: OkHttpClient.Builder
        get() {
            val logInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Logger.d(message) }).setLevel(
                HttpLoggingInterceptor.Level.BODY
            )
            return UnsafeOkHttpClient.unsafeOkHttpClient.newBuilder()
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
