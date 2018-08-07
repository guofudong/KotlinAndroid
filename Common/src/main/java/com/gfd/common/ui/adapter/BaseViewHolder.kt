package com.gfd.common.ui.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 13:58
 * @Email：878749089@qq.com
 * @descriptio：
 */
open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val views: SparseArray<View> = SparseArray()

    fun <T : View> getView(viewId: Int): T {
        var view = views.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }
}