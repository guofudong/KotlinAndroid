package com.gfd.common.widgets

/**
 * @Author : 郭富东
 * @Date ：2018/8/4 - 15:20
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface SpanLookup {

    /**
     * @return number of spans in a row
     */
    val spanCount: Int

    /**
     * @param itemPosition
     * @return start span for the item at the given adapter position
     */
    fun getSpanIndex(itemPosition: Int): Int

    /**
     * @param itemPosition
     * @return number of spans the item at the given adapter position occupies
     */
    fun getSpanSize(itemPosition: Int): Int

}