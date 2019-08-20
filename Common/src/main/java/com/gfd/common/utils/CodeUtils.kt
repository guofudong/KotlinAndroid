package com.gfd.common.utils

import java.util.regex.Pattern
/**
 * @Author : 郭富东
 * @Date ：2018/9/15 - 14:37
 * @Email：878749089@qq.com
 * @descriptio：
 */
object CodeUtils{
    /**
     * unicode转中文
     * @param codeStr
     * @return
     */
    fun unicodeToString(codeStr: String): String {
        var str = codeStr
        val pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))")
        val matcher = pattern.matcher(str)
        var ch: Char
        while (matcher.find()) {
            ch = Integer.parseInt(matcher.group(2), 16).toChar()
            str = str.replace(matcher.group(1), ch + "")
        }
        return str
    }
}