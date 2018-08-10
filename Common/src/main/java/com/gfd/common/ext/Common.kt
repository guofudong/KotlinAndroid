package com.gfd.common.ext

import android.support.v4.view.ViewPager
import com.gfd.common.utils.FixedSpeedScroller
import java.lang.reflect.Field

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 11:08
 * @Email：878749089@qq.com
 * @descriptio：
 */
fun ViewPager.noScroll() {
    var mScroller: Field? = null
    mScroller = ViewPager::class.java.getDeclaredField("mScroller")
    mScroller!!.isAccessible = true
    val scroller = FixedSpeedScroller(this.context)
    mScroller.set(this, scroller)
}