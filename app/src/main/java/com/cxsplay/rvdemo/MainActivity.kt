package com.cxsplay.rvdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cxsplay.rvdemo.brvah.BRVAHActivity
import com.cxsplay.rvdemo.qqui.QQUIActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        btn1.setOnClickListener {
            startActivity(Intent(this, RVActivity::class.java))
        }
        btn2.setOnClickListener {
            startActivity(Intent(this, PinchViewActivity::class.java))
        }
        btn3.setOnClickListener {
            val content = "pinduoduo://com.xunmeng.pinduoduo/duo_coupon_landing.html?goods_id=580820933&pid=10001_20999"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(content))
            startActivity(intent)
        }
        btn4.setOnClickListener {
            val content =
                "openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"productDetail\",\"skuId\":\"100000287113\",\"sourceType\":\"JSHOP_SOURCE_TYPE\",\"sourceValue\":\"JSHOP_SOURCE_VALUE\"}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(content))
            startActivity(intent)
        }
        btn5.setOnClickListener {
            startActivity(Intent(this, QQUIActivity::class.java))
        }
        btn6.setOnClickListener {
            startActivity(Intent(this, BRVAHActivity::class.java))
        }
    }
}
