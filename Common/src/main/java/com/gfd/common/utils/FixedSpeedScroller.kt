package com.gfd.common.utils

import android.content.Context
import android.widget.Scroller

/**
 * @Author : 郭富东
 * @Date ：2018/8/9 - 10:48
 * @Email：878749089@qq.com
 * @descriptio：
 */
class FixedSpeedScroller(context: Context) : Scroller(context) {

    private val mDuration = 100

    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, mDuration)
    }
}