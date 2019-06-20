package com.cxsplay.vmcoroutines.entity

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

import com.cxsplay.vmcoroutines.BR

/**
 * Created by CxS on 2019/6/20 15:10.
 * Description:
 */
class Tessst : BaseObservable() {

    @get:Bindable
    var test: String? = null
        set(test) {
            field = test
            notifyPropertyChanged(BR.test)
        }
}
