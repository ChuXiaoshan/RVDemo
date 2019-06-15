package com.cxsplay.rvdemo;

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

/**
 * Created by CxS on 2019/6/15
 */
class App : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
