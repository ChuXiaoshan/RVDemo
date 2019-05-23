package com.cxsplay.appkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cxsplay.appkotlin.R
import com.cxsplay.appkotlin.bean.Product
import com.cxsplay.appkotlin.common.ImageLoader
import com.cxsplay.appkotlin.databinding.ItemProductsBinding

/**
 * Created by CxS on 2019/5/23 9:58.
 * Description:
 */
class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var data: List<Product>? = null

    fun setData(data: List<Product>?) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_products, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = this.data!![position]
        holder.bind!!.tvTitle.text = data.subTitle
        ImageLoader.byUrl(data.pic, holder.bind.ivProduct)
    }

    override fun getItemCount(): Int {
        return if (data == null) 0 else data!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val bind: ItemProductsBinding? = DataBindingUtil.bind(itemView)
    }
}
