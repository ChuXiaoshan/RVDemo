package com.cxsplay.appkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.ToastUtils
import com.cxsplay.appkotlin.bean.Login
import com.cxsplay.appkotlin.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var bean: Login? = null
    private var bind: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bean = Login()
        bind!!.bean = bean
        init()
    }

    private fun init() {
        val dd = et_name.text
        et_pwd.text = dd
        bind?.btn?.setOnClickListener {
            ToastUtils.showShort(bean.toString())
        }
    }
}
