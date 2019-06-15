package com.cxsplay.appkotlin.netk

/**
 * Created by CxS on 2019/5/21
 */
object RHelper {

    var apiService: ApiService? = null
        private set

    init {
        val retrofit = RUtil.getRetrofitBuilder("http://192.168.1.104:8080").build()
        apiService = retrofit.create(ApiService::class.java)
    }
}
