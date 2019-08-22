package com.gfd.common.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager


/**
 * @Author ：郭富东
 * @Date：2019/8/6:14:52
 * @Email：878749089@qq.com
 * @description：软键盘工具类
 */
object SoftKeyboardUtils {

    /**
     * 隐藏软键盘
     * @param activity Activity
     */
    fun closeInoutDecorView(activity: Activity) {
        val view = activity.window.peekDecorView()
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}