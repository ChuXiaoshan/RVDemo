package com.cxsplay.appkotlin.entity

/**
 * Created by CxS on 2019/5/21 14:54.
 * Description:
 */
class Base<T> {

    var c: Int = 0
    var m: String? = null
    var d: T? = null

    override fun toString(): String {
        return "Base(c=$c, m=$m, d=$d)"
    }
}
