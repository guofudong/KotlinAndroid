package com.gfd.common.widgets

import android.support.v7.widget.GridLayoutManager



/**
 * @Author : 郭富东
 * @Date ：2018/8/4 - 15:20
 * @Email：878749089@qq.com
 * @descriptio：
 */
internal object SpanLookupFactory {

    fun singleSpan(): SpanLookup {
        return object : SpanLookup {
            override val spanCount: Int
                get() = 1

            override fun getSpanIndex(itemPosition: Int): Int {
                return 0
            }

            override fun getSpanSize(itemPosition: Int): Int {
                return 1
            }
        }
    }

    fun gridLayoutSpanLookup(layoutManager: GridLayoutManager): SpanLookup {
        return object : SpanLookup {
            override val spanCount: Int
                get() = layoutManager.spanCount

            override fun getSpanIndex(itemPosition: Int): Int {
                return layoutManager.spanSizeLookup.getSpanIndex(itemPosition, spanCount)
            }

            override fun getSpanSize(itemPosition: Int): Int {
                return layoutManager.spanSizeLookup.getSpanSize(itemPosition)
            }
        }
    }

}