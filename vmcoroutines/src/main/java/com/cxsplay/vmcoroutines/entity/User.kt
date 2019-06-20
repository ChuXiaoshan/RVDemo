package com.cxsplay.appkotlin.entity

/**
 * Created by CxS on 2019/5/21 14:55.
 * Description:
 */
class User {

    var id: Int = 0
    var username: String? = null
    private var pwd: String? = null

    override fun toString(): String {
        return "User(id=$id, name=$username, pwd=$pwd)"
    }
}
