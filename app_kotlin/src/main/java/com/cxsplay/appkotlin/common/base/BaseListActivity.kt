package com.cxsplay.appkotlin.common.base

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.cxsplay.appkotlin.bean.PageBean

/**
 * Created by CxS on 2019/5/24 17:27.
 * Description:
 */
abstract class BaseListActivity : BaseActivity() {

    val pageBean: PageBean = PageBean()
    lateinit var srl: SwipeRefreshLayout

    private var rl: SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        srl.isRefreshing = true
        pageBean.page = 1
        getList()
    }

    protected var rlm: BaseQuickAdapter.RequestLoadMoreListener = BaseQuickAdapter.RequestLoadMoreListener {
        pageBean.page++
        getList()
    }

    open fun initSrl(srl: SwipeRefreshLayout) {
        this.srl = srl
        srl.setOnRefreshListener(rl)
        srl.post { rl.onRefresh() }
    }

    abstract fun getList()
}