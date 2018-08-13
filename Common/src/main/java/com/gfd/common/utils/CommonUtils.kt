package com.gfd.common.utils

import android.content.Context

/**
 * @Author : 郭富东
 * @Date ：2018/8/13 - 10:37
 * @Email：878749089@qq.com
 * @descriptio：
 */
object CommonUtils {

    fun getStatusBarHeight(context: Context): Int {
        try {
            val c = Class.forName("com.android.internal.R\$dimen")
            val obj = c.newInstance()
            val field = c.getField("status_bar_height")
            val x = Integer.parseInt(field.get(obj).toString())
            return context.resources.getDimensionPixelSize(x)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 0
    }

}