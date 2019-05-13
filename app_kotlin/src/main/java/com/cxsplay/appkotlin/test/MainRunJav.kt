package com.cxsplay.appkotlin.test

/**
 * Created by CxS on 2019/4/25 17:33.
 * Description:
 */
class MainRunJav {

    private var bean: Login? = null

    private fun test() {
        bean = Login()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            println("hello world.")
        }
    }
}
