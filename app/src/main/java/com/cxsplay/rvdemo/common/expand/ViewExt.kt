package com.cxsplay.rvdemo.common.expand

import android.view.View

/**
 * Created by chuxiaoshan on 2019-06-17.
 *
 * View 的一下扩展函数和扩展属性
 *
 */

/**
 * 扩展属性参数：用于标记是否可点击
 */
private var <T : View> T.viewClickable: Boolean
    get() = if (getTag(1 + 2 shl 24) != null) getTag(1 + 2 shl 24) as Boolean else true
    set(value) {
        setTag(1 + 2 shl 24, value)
    }

/**
 * 设置点击监听, 并自定义防抖时间
 */
fun <T : View> T.click(time: Long = 300, action: (T: View) -> Unit) {
    setOnClickListener {
        if (viewClickable) {
            viewClickable = false
            action(it)
            postDelayed({ viewClickable = true }, time)
        }
    }
}

