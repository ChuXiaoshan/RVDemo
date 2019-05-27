package com.cxsplay.appkotlin.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.cxsplay.appkotlin.bean.Product
import com.cxsplay.appkotlin.common.base.BaseListActivity
import com.cxsplay.appkotlin.databinding.ActivityProductsBinding
import com.cxsplay.appkotlin.entity.Base
import com.cxsplay.appkotlin.entity.BaseList
import com.cxsplay.appkotlin.netk.RHelper
import com.cxsplay.appkotlin.ui.adapter.ProductsAdapter
import com.cxsplay.appkotlin.widget.decoration.SimpleDividerItemDecoration
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class ProductsActivity : BaseListActivity() {

    private lateinit var bind: ActivityProductsBinding
    private lateinit var data: MutableList<Product>
    private lateinit var adapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, com.cxsplay.appkotlin.R.layout.activity_products)
        init()
    }

    private fun init() {

        data = ArrayList()
        adapter = ProductsAdapter(com.cxsplay.appkotlin.R.layout.item_products, data)
        adapter.setOnLoadMoreListener(rlm, bind.rv)
        adapter.openLoadAnimation()
        bind.rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        bind.rv.addItemDecoration(SimpleDividerItemDecoration(ConvertUtils.dp2px(0.6f)))
        bind.rv.adapter = adapter
        initSrl(bind.srl)
    }

    override fun getList() {
        RHelper.apiService!!.getProducts(pageBean.page, pageBean.size)
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Base<BaseList<Product>>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Base<BaseList<Product>>) {
                    if (t.c == 0) {
                        val list = t.d!!.data
                        if (bind.srl.isRefreshing) {
                            bind.srl.isRefreshing = false
                            adapter.setNewData(list)
                            adapter.disableLoadMoreIfNotFullPage()
                        } else {
                            adapter.addData(list!!)
                        }
                        if (list.isNullOrEmpty() || adapter.data.size.toLong() == t.d!!.totalSize) {
                            adapter.loadMoreEnd()
                        } else {
                            adapter.loadMoreEnd()
                            adapter.loadMoreComplete()
                        }
                    } else {
                        loadFailed(t.m)
                    }
                }

                override fun onError(e: Throwable) {
                    loadFailed("加载失败")
                }
            })
    }

    private fun loadFailed(msg: String?) {
        pageBean.page = if (pageBean.page <= 1) 1 else --pageBean.page
        bind.srl.isRefreshing = false
        ToastUtils.showShort(msg)
        adapter.loadMoreFail()
    }
}
