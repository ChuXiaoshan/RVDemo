package com.cxsplay.appkotlin.test

const val str = 2


fun main() {

    var hello: Hello? = null

    hello = Hello()


}

fun dd(hello: Hello): Hello? {
    hello.name = "cxs"

    return hello
}
