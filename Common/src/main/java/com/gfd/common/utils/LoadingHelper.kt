package com.gfd.common.utils

import android.app.Activity
import com.gfd.common.widgets.ProgressLoading

/**
 * @Author : 郭富东
 * @Date ：2018/8/9 - 11:23
 * @Email：878749089@qq.com
 * @descriptio：
 */
object LoadingHelper {

    private var mLoading: ProgressLoading? = null
    fun showLoading(context: Activity?) {
        if (context == null) {
            return
        }
        if (mLoading == null) {
            mLoading = ProgressLoading.create(context)
        }
        mLoading?.showLoading()
    }

    fun hideLoading(context: Activity?) {
        if (context == null) {
            return
        }
        if (mLoading == null) {
            mLoading = ProgressLoading.create(context)
        }
        mLoading?.hideLoading()
        mLoading = null
    }
}