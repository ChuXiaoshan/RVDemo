package com.cxsplay.appkotlin.net;


import retrofit2.Retrofit;

/**
 * Created by CxS on 2018/4/12
 */
public class RHelper {

    private static ApiService apiService;

    static {
        Retrofit retrofit = RUtil.getRetrofitBuilder("").build();
        apiService = retrofit.create(ApiService.class);
    }

    public static ApiService getApiService() {
        return apiService;
    }
}
