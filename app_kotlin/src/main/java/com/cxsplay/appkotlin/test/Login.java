package com.cxsplay.appkotlin.test

/**
 * Created by CxS on 2019/5/13 17:41.
 * Description:
 */
class Login {

    var name: String? = null
    var pwd: String? = null
        set(pwd) {


            dd = if (dd == 1) 1 else dd--


            field = pwd
        }
    private var dd: Int = 0

    override fun toString(): String {
        return "Login{" +
                "name='" + name + '\''.toString() +
                ", pwd='" + this.pwd + '\''.toString() +
                '}'.toString()
    }
}
