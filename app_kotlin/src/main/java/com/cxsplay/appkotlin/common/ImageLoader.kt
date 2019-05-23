package com.cxsplay.appkotlin.common

import android.widget.ImageView

/**
 * Created by CxS on 2019/5/23 16:37.
 * Description:
 */
object ImageLoader {

    fun byUrl(url: String?, iv: ImageView?) {
        GlideApp.with(iv!!)
            .load(url)
            .into(iv)
    }

}
