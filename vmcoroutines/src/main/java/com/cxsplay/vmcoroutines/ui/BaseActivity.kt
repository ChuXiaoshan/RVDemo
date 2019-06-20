package com.cxsplay.vmcoroutines.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.cxsplay.vmcoroutines.base.BaseViewModel

/**
 * Created by CxS on 2019/6/20 15:58.
 * Description:
 */
open class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVM()
    }

    open fun providerVMClass(): Class<VM>? = null

    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProviders.of(this).get(it)
        }
    }
}