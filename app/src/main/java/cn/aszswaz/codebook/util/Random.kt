package cn.aszswaz.codebook.util

import java.util.Random

fun randomPwd(length: Int, letter: Boolean, number: Boolean, symbol: Boolean): String {
    val letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val numbers = "0123456789"
    val symbols = ",./;'[]\\<>?:\"{}|`~!@#$%^&*()-=_+"

    var seed = ""
    if (letter) seed += letters
    if (number) seed += numbers
    if (symbol) seed += symbols

    val rand = Random()
    var result = ""
    for (i in 0..<length) {
        val index = rand.nextInt(seed.length)
        result += seed[index]
    }
    return result
}