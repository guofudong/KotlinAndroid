package com.gfd.music.ext

import android.support.design.widget.TabLayout
import android.view.View
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.music.R

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 11:26
 * @Email：878749089@qq.com
 * @descriptio：
 */
fun TabLayout.init() {
    //将当前的tab文字放大
    this.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            val textView = tab.customView?.findViewById(R.id.tab_item_textview) as TextView
            val indicator = tab.customView?.findViewById(R.id.view_music_indicator) as View
            textView.paint.isFakeBoldText = true
            indicator.visibility = View.VISIBLE
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
            val textView = tab.customView?.findViewById(R.id.tab_item_textview) as TextView
            val indicator = tab.customView?.findViewById(R.id.view_music_indicator) as View
            textView.paint.isFakeBoldText = false
            indicator.visibility = View.INVISIBLE
        }

        override fun onTabReselected(tab: TabLayout.Tab) {
        }
    })
}
