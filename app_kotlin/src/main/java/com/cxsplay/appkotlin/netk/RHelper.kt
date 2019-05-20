package com.cxsplay.appkotlin.netk


import com.cxsplay.appkotlin.net.ApiService

/**
 * Created by CxS on 2018/4/12
 */
object RHelper {

    var apiService: ApiService? = null
        private set

    init {
        val retrofit = RUtil.getRetrofitBuilder("").build()
        apiService = retrofit.create(ApiService::class.java)
    }
}
