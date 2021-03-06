package com.cxsplay.appkotlin.test

fun main() {

    val oddLength = compose(::isOdd, ::length)
    val strings = listOf("a", "ab", "abc")

    println(strings.filter(oddLength))
}

fun length(s: String) = s.length

fun isOdd(x: Int) = x % 2 != 0

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}
