package com.cxsplay.rvdemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cxsplay.rvdemo.brvah.BRVAHActivity
import com.cxsplay.rvdemo.mmkv.MMKVActivity
import com.cxsplay.rvdemo.photo.PhotoActivity
import com.cxsplay.rvdemo.pickerview.PickerViewActivity
import com.cxsplay.rvdemo.qqui.QQUIActivity
import com.cxsplay.rvdemo.ui.EditTextActivity
import com.cxsplay.rvdemo.ui.ShadowActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        btn1.setOnClickListener { startActivity<RVActivity>() }
        btn2.setOnClickListener { startActivity<PinchViewActivity>() }
        btn3.setOnClickListener { MMKVActivity.toAlipayAppWeb("https://blog.csdn.net/qq_35246620/article/details/70823903") }
        btn4.setOnClickListener {
            val content = "openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"productDetail\",\"skuId\":\"100000287113\",\"sourceType\":\"JSHOP_SOURCE_TYPE\",\"sourceValue\":\"JSHOP_SOURCE_VALUE\"}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(content))
            startActivity(intent)
        }
        btn5.setOnClickListener { startActivity<QQUIActivity>() }
        btn6.setOnClickListener { startActivity<BRVAHActivity>() }
        btn7.setOnClickListener { startActivity<MMKVActivity>() }
        btn8.setOnClickListener { startActivity<PhotoActivity>() }
        btn9.setOnClickListener { startActivity<ShadowActivity>() }
        btn10.setOnClickListener { startActivity<ShadowActivity>() }
        btn11.setOnClickListener { startActivity<ShadowActivity>() }
        btn12.setOnClickListener { startActivity<ShadowActivity>() }
        btn13.setOnClickListener { startActivity<EditTextActivity>() }
        btn14.setOnClickListener { startActivity<PickerViewActivity>() }
    }
}
