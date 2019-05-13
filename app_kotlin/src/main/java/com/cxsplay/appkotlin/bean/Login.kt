package com.cxsplay.appkotlin.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.cxsplay.appkotlin.BR

/**
 * Created by CxS on 2019/5/13 17:41.
 * Description:
 */
class Login : BaseObservable() {

    @get:Bindable
    var name: String? = null
        set(name) {
            field = name
            notifyPropertyChanged(BR.name)
        }
    @get:Bindable
    var pwd: String? = null
        set(pwd) {
            field = pwd
            notifyPropertyChanged(BR.pwd)
        }


    override fun toString(): String {
        return "Login(name=$name, pwd=$pwd)"
    }
}
