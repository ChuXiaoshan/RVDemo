package com.cxsplay.vmcoroutines.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.cxsplay.vmcoroutines.R
import com.cxsplay.vmcoroutines.databinding.ActivityProductsBinding

class ProductsActivity : BaseActivity<ProductsModel>(), LifecycleOwner {

    private lateinit var bind: ActivityProductsBinding

    override fun providerVMClass(): Class<ProductsModel> {
        return ProductsModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_products)
        init()
    }

    private fun init() {
        bind.btnGetList.setOnClickListener {
            mViewModel.getList()
        }

        mViewModel.data.observe(this, Observer {
            bind.tvContent.text = mViewModel.data.value.toString()
        })
    }
}
