package com.cxsplay.vmcoroutines.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope

/**
 * Created by CxS on 2019/6/20 15:38.
 * Description:
 */
open class BaseViewModel : ViewModel() {

    fun launch(tryBlock: suspend CoroutineScope.() -> Unit) {

    }
}