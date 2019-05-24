package com.cxsplay.appkotlin.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.cxsplay.appkotlin.R
import com.cxsplay.appkotlin.bean.Product
import com.cxsplay.appkotlin.common.ImageLoader
import java.util.*

/**
 * Created by CxS on 2019/5/23 17:38.
 * Description:
 */
class ProductsAdapter(layoutResId: Int, data: List<Product>?) :
        BaseQuickAdapter<Product, BaseViewHolder>(layoutResId, data) {

    private val list: MutableList<Product>

    init {
        list = ArrayList()
    }

    override fun convert(helper: BaseViewHolder, item: Product) {
        ImageLoader.byUrl(item.pic, helper.getView(R.id.iv_product))
        helper.setText(R.id.tv_title, item.subTitle)
    }
}
