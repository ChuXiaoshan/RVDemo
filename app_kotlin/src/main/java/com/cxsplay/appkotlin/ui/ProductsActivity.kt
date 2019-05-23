package com.cxsplay.appkotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cxsplay.appkotlin.R
import com.cxsplay.appkotlin.bean.Product
import com.cxsplay.appkotlin.databinding.ActivityProductsBinding
import com.cxsplay.appkotlin.entity.Base
import com.cxsplay.appkotlin.entity.BaseList
import com.cxsplay.appkotlin.netk.RHelper
import com.cxsplay.appkotlin.ui.adapter.ProductssAdapter
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class ProductsActivity : AppCompatActivity() {

    private lateinit var bind: ActivityProductsBinding
    private lateinit var data: MutableList<Product>
    private lateinit var adapt: ProductssAdapter

    private var rl: SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        bind.srl.isRefreshing = true
        getList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_products)
        init()
    }

    private fun init() {
        data = ArrayList()
        adapt = ProductssAdapter(R.layout.item_products, data)
        bind.rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        bind.rv.adapter = adapt
        bind.srl.setOnRefreshListener(rl)
        bind.srl.post {
            rl.onRefresh()
        }
    }

    private fun getList() {
        RHelper.apiService!!.getProducts()
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Base<BaseList<Product>>> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Base<BaseList<Product>>) {
                    bind.srl.isRefreshing = false
                    val data1 = t.d!!.data
                    data.addAll(data1!!)
                    adapt.addData(data1)
                    adapt.notifyDataSetChanged()
                }

                override fun onError(e: Throwable) {
                }
            })
    }
}
