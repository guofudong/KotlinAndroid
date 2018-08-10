package com.gfd.home.ext

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.GridLayoutManager
import android.widget.TextView
import com.gfd.common.utils.FixedSpeedScroller
import com.gfd.common.widgets.SpacesItemDecoration
import com.gfd.home.R
import com.github.jdsjlzx.recyclerview.LRecyclerView
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import java.lang.reflect.Field


/**
 * @Author : 郭富东
 * @Date ：2018/8/9 - 9:29
 * @Email：878749089@qq.com
 * @descriptio：扩展
 */

fun LRecyclerView.gridInit(context: Context, span: Int, adapter: LRecyclerViewAdapter) {
    val layoutManager = GridLayoutManager(context, span)
    this.layoutManager = layoutManager
    this.adapter = adapter
    this.setLoadMoreEnabled(false)
    this.setPullRefreshEnabled(false)
    val spacing = resources.getDimensionPixelSize(R.dimen.dp_4)
    this.addItemDecoration(SpacesItemDecoration.newInstance(
            spacing, spacing, span, resources.getColor(R.color.colorItemDecoration)))
}

fun TabLayout.init() {
    //将当前的tab文字放大
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            var view = tab.customView
            if (null != view && view is TextView) {
                view.textSize = resources.getDimension(R.dimen.dp_6)
                view.paint.isFakeBoldText = true
                view.setTextColor(resources.getColor(R.color.colorSearchItemBtn))
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
            var view = tab.customView
            if (null != view && view is TextView) {
                view.textSize = resources.getDimension(R.dimen.dp_5)
                view.paint.isFakeBoldText = false
                view.setTextColor(resources.getColor(R.color.common_black))
            }
        }

        override fun onTabReselected(tab: TabLayout.Tab) {
        }
    })
}


