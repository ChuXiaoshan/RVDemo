package com.cxsplay.appkotlin.ui.adapter

import android.text.TextUtils
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.cxsplay.appkotlin.bean.Product

import java.util.ArrayList

/**
 * Created by CxS on 2019/5/23 17:38.
 * Description:
 */
class ProductssAdapter(layoutResId: Int, data: List<Product>?) :
    BaseQuickAdapter<Product, BaseViewHolder>(layoutResId, data) {

    private val list: MutableList<Product>


    init {
        list = ArrayList()
    }

    override fun convert(helper: BaseViewHolder, item: Product) {
        val dd = ""
        if (TextUtils.isEmpty(dd)) {
            LogUtils.d("----")
        } else {
            LogUtils.d("----")
            val ddc = ArrayList<Product>()
            list.addAll(ddc)
        }
    }
}
