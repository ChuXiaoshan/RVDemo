package com.cxsplay.appkotlin.net;


import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

/**
 * Created by CxS on 2018/8/20
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("/quickpay/m/login/get_sms_captcha")
    Observable<String> getImgCaptcha(@FieldMap Map<String, Object> params);
}
