package com.cxsplay.rvdemo.common.expand

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.os.Build
import org.jetbrains.anko.internals.AnkoInternals

/**
 * Created by CxS on 2019/6/19 17:48.
 * Description:
 */
inline fun <reified T : Activity> Context.toActivity(vararg params: Pair<String, Any?>) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        startActivity(AnkoInternals.createIntent(this, T::class.java, params), ActivityOptions.makeSceneTransitionAnimation(this as Activity?).toBundle())
    }
}