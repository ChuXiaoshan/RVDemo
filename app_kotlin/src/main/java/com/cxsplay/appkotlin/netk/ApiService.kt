package com.cxsplay.appkotlin.netk


import com.cxsplay.appkotlin.bean.Product
import com.cxsplay.appkotlin.entity.Base
import com.cxsplay.appkotlin.entity.BaseList
import com.cxsplay.appkotlin.entity.User
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by CxS on 2019/5/21
 */
interface ApiService {

    @GET("/user/get_users")
    fun getUsers(): Observable<Base<List<User>>>

    @POST("/product/add_product")
    fun addProduct(@Body product: Product?): Observable<Base<Any>>

    @GET("/product/get_list")
    fun getProducts(@Query("page") page: Int, @Query("size") size: Int): Observable<Base<BaseList<Product>>>

    @POST("https://pay.dev.vyicoo.com/test/index2")
    fun smilePayInit(@Body body: RequestBody): Observable<Base<Any>>
}
