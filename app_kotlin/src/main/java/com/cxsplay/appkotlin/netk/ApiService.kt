package com.cxsplay.appkotlin.netk


import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by CxS on 2018/8/20
 */
interface ApiService {

    @FormUrlEncoded
    @POST("/quickpay/m/login/get_sms_captcha")
    fun getImgCaptcha(@FieldMap params: Map<String, Any>): Observable<String>
}
