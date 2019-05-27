package com.cxsplay.appkotlin

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.LogUtils
import com.cxsplay.appkotlin.common.base.BaseActivity
import com.cxsplay.appkotlin.databinding.ActivityMainBinding
import com.cxsplay.appkotlin.ui.AddProductActivity
import com.cxsplay.appkotlin.ui.ProductsActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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


        bind.btnShowAnko.setOnClickListener {
            //            launch {
//                delay(3000)
//                toast("hello")
//            }

            launch {
                LogUtils.d("------1")
                delay(4000)
                LogUtils.d("------4")
            }


            launch(Dispatchers.IO) {
                LogUtils.d("------2")
                delay(1000)
                LogUtils.d("------3")
            }

            launch(Dispatchers.Main) {
                LogUtils.d(withContext(Dispatchers.IO) {
                    delay(4000)
                    LogUtils.d("------40000")
                    "hello"
                })
            }
        }
    }
}