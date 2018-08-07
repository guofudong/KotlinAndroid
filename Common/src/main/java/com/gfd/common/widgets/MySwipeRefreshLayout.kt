package com.gfd.common.widgets

import android.content.Context
import android.support.v4.view.ViewCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.util.AttributeSet
import android.view.View
import android.widget.AbsListView


/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 10:02
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MySwipeRefreshLayout : SwipeRefreshLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    //实际需要滑动的child view
    private var mScrollUpChild: View? = null

    fun setScrollUpChild(childView: View) {
        mScrollUpChild = childView
    }

    override fun canChildScrollUp(): Boolean {
        if (mScrollUpChild != null) {
            if (android.os.Build.VERSION.SDK_INT < 14) {
                if (mScrollUpChild is AbsListView) {
                    val absListView = mScrollUpChild as AbsListView?
                    return absListView!!.childCount > 0 && (absListView.firstVisiblePosition > 0 || absListView.getChildAt(0)
                            .top < absListView.paddingTop)
                } else {
                    return ViewCompat.canScrollVertically(mScrollUpChild!!, -1) || mScrollUpChild!!.getScrollY() > 0
                }
            } else {
                return ViewCompat.canScrollVertically(mScrollUpChild!!, -1)
            }
        }
        return super.canChildScrollUp()
    }
}