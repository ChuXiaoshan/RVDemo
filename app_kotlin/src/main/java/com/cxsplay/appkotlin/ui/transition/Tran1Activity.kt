package com.cxsplay.appkotlin.ui.transition

import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.cxsplay.appkotlin.common.base.BaseActivity
import com.cxsplay.appkotlin.databinding.ActivityTran1Binding
import com.cxsplay.appkotlin.expand.click
import com.cxsplay.appkotlin.expand.toActivity


class Tran1Activity : BaseActivity() {

    private lateinit var bind: ActivityTran1Binding

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        bind = DataBindingUtil.setContentView(this, com.cxsplay.appkotlin.R.layout.activity_tran1)
        bind.btn.click { toActivity<Tran2Activity>() }
    }
}
