package com.cxsplay.appkotlin.netk


import com.cxsplay.appkotlin.entity.Base
import com.cxsplay.appkotlin.entity.User
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by CxS on 2019/5/21
 */
interface ApiService {

    @GET("/user/get_users")
    fun getUsers(): Observable<Base<List<User>>>
}
