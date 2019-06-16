package com.cxsplay.appkotlin

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.ToastUtils
import com.cxsplay.appkotlin.common.base.BaseActivity
import com.cxsplay.appkotlin.databinding.ActivityMainBinding
import com.cxsplay.appkotlin.entity.Base
import com.cxsplay.appkotlin.netk.RHelper
import com.cxsplay.appkotlin.test.expand.click
import com.cxsplay.appkotlin.ui.AddProductActivity
import com.cxsplay.appkotlin.ui.ProductsActivity
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }

    private fun init() {
        bind.btnAddProduct.setOnClickListener {
            startActivity(intentFor<AddProductActivity>())
        }
        bind.btnShowProduct.setOnClickListener {
            startActivity<ProductsActivity>("id" to 4, "title" to "products")
        }

//        bind.btnShowAnko.setOnClickListener {
//            payInit()
//        }

//        bind.btnShowAnko.clickWithInterval(10000) {
//            System.out.println("11111")
//        }
//
        bind.btnShowAnko.click {
            System.out.println("11111")
        }

        bind.btn4.click(0) {
            System.out.println("444444")
        }
    }


    private fun payInit() {
        val params =
            "{\"apdidToken\":\"3vkbN6i56j24Oetk7bDDZzBM5qXYyLB8KxBOt51P3EFhaE4NK2tPawEB\",\"appName\":\"com.alipay.zoloz.smile\",\"appVersion\":\"3.10.0.345\",\"bioMetaInfo\":\"4.2.0:287358976,2\",\"deviceModel\":\"ONEPLUS A6000\",\"deviceType\":\"android\",\"machineInfo\":{\"cameraDriveVer\":\"\",\"cameraModel\":\"AstraPro2\",\"cameraName\":\"AstraPro2\",\"cameraVer\":\"\",\"ext\":\"\",\"group\":\"\",\"machineCode\":\"WkkJpvO/FVcDACZyyrs+bhPQ\",\"machineModel\":\"ONEPLUS A6000\",\"machineVer\":\"9\"},\"merchantInfo\":{\"alipayStoreCode\":\"TEST\",\"appId\":\"2019021463221582\",\"areaCode\":\"TEST\",\"brandCode\":\"TEST\",\"deviceMac\":\"TEST\",\"deviceNum\":\"123456\",\"geo\":\"0.000000,0.000000\",\"merchantId\":\"2088421326544474\",\"partnerId\":\"2088421326544474\",\"storeCode\":\"TEST\",\"wifiMac\":\"TEST\",\"wifiName\":\"TEST\"},\"osVersion\":\"9\",\"remoteLogID\":\"847dc15c2f784e22a167e306574cf7f0342759379\",\"zimVer\":\"1.0.0\"}"
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params)
        RHelper.apiService!!.smilePayInit(body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Base<Any>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(base: Base<Any>) {
                    ToastUtils.showShort(base.m)
                    Logger.d("---user--->$base")
                }

                override fun onError(e: Throwable) {
                    ToastUtils.showShort("添加失败")
                    Logger.d("---e--->${e.message}")

                }

                override fun onComplete() {

                }
            })
    }
}