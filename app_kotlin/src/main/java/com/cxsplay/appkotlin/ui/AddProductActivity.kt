package com.cxsplay.appkotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.cxsplay.appkotlin.R
import com.cxsplay.appkotlin.bean.Product
import com.cxsplay.appkotlin.databinding.ActivityAddProductBinding
import com.cxsplay.appkotlin.entity.Base
import com.cxsplay.appkotlin.netk.RHelper
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AddProductActivity : AppCompatActivity() {

    private var bind: ActivityAddProductBinding? = null
    private var bean: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_add_product)
        init()
    }

    private fun init() {
        bean = Product()
        bind!!.bean = bean
        bind!!.btnSubmit.setOnClickListener {
            addProduct()
        }
    }

    private fun addProduct() {
        LogUtils.d("--->$bean")
        RHelper.apiService!!.addProduct(bean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : io.reactivex.Observer<Base<Any>> {
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
