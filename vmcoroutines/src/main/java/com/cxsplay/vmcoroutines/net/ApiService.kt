package com.cxsplay.vmcoroutines.net


import com.cxsplay.appkotlin.entity.Base
import com.cxsplay.appkotlin.entity.BaseList
import com.cxsplay.vmcoroutines.entity.Product
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by CxS on 2019/5/21
 */
interface ApiService {

    @GET("/product/get_list")
    suspend fun getProducts(@Query("page") page: Int, @Query("size") size: Int): Base<BaseList<Product>>
}
