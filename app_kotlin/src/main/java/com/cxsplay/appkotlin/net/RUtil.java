package com.cxsplay.appkotlin.net;

import com.blankj.utilcode.util.LogUtils;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by CxS on 2018/4/12
 */
class RUtil {

    private static OkHttpClient.Builder getOkHttpClientBuilder() {
//        //公共参数
        BasicParamsInterceptor basicParamsInterceptor = new BasicParamsInterceptor.Builder()
                .addParam("channel", "app")
                .build();
        //log
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtils.d("----->" + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);
        return UnsafeOkHttpClient.getUnsafeOkHttpClient().newBuilder()
                .addInterceptor(basicParamsInterceptor)
                .addInterceptor(logInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false);
    }

    static Retrofit.Builder getRetrofitBuilder(String baseUrl) {
        return new Retrofit.Builder()
                .client(getOkHttpClientBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl);
    }

}
