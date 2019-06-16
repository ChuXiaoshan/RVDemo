package com.cxsplay.appkotlin.test

fun main() {

//    doTalk("hello", )
}


//fun talk(word: String): String {
//    System.out.println(word)
//    return word
//}

fun doTalk(word: String, talk: (word: String) -> String) {
    var dd = talk(word)
    System.out.println("hell$dd")

}
