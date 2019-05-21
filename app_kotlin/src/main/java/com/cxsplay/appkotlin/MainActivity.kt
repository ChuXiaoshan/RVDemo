package com.cxsplay.appkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cxsplay.appkotlin.bean.Login
import com.cxsplay.appkotlin.databinding.ActivityMainBinding
import com.cxsplay.appkotlin.entity.Base
import com.cxsplay.appkotlin.entity.User
import com.cxsplay.appkotlin.netk.RHelper
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.ListCompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var listDisposable: ListCompositeDisposable? = null
    private var bind: ActivityMainBinding? = null
    private var bean: Login? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bean = Login()
        bind!!.bean = bean
        listDisposable = ListCompositeDisposable()

        init()

    }

    private fun init() {
        val dd = et_name.text
        et_pwd.text = dd
        bind?.btn?.setOnClickListener {
            getUsers()
        }
    }

    private fun getUsers() {
        RHelper.apiService!!.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : io.reactivex.Observer<Base<List<User>>> {
                override fun onSubscribe(d: Disposable) {
                    listDisposable!!.add(d)
                }

                override fun onNext(base: Base<List<User>>) {
                    Logger.d("---user--->$base")
                }

                override fun onError(e: Throwable) {
                    Logger.d("---e--->${e.message}")

                }

                override fun onComplete() {

                }

            })
    }

    private fun getNameById(id: Int): String {
        when (id) {
            1 -> return "张三"
            2 -> return "李四"
            3 -> return "王五"
        }
        return ""
    }
}
