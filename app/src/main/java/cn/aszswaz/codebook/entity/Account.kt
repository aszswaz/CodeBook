package cn.aszswaz.codebook.entity

import android.text.Editable

@Suppress("unused")
class Account(
    val id: Int = 0,
    /**
     * 账号
     */
    var account: String = "",
    /**
     * 密码
     */
    var password: String = "",
    /**
     * 账号所属平台
     */
    var platform: String = "",
    /**
     * 密码的长度
     */
    var length: Int = 0,
    /**
     * 密码是否包含字母
     */
    var letter: Boolean = false,
    /**
     * 密码是否包含数字
     */
    var number: Boolean = false,
    /**
     * 密码是否包含标点符号
     */
    var symbol: Boolean = false
)