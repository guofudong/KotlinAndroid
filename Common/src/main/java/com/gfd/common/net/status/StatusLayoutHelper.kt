package com.gfd.common.net.status

import android.content.Context
import android.support.annotation.LayoutRes
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import org.jetbrains.anko.collections.forEach

/**
 * @Author : 郭富东
 * @Date ：2018/9/25 - 16:30
 * @Email：878749089@qq.com
 * @descriptio：
 */
class StatusLayoutHelper(context: Context) : FrameLayout(context) {

    /**
     * loading 加载id
     */
    val LAYOUT_LOADING_ID = 1

    /**
     * 内容id
     */
    val LAYOUT_CONTENT_ID = 2

    /**
     * 异常id
     */
    val LAYOUT_ERROR_ID = 3

    /**
     * 网络异常id
     */
    val LAYOUT_NETWORK_ERROR_ID = 4

    /**
     * 空数据id
     */
    val LAYOUT_EMPTYDATA_ID = 5


    /**
     * 存放布局集合
     */
    private val layoutSparseArray: SparseArray<View> = SparseArray()

    /**
     * 布局管理器
     */
    private lateinit var mStatusLayoutManager: StatusLayoutManager


    fun setStatusLayoutManager(statusLayoutManager: StatusLayoutManager) {
        this.mStatusLayoutManager = statusLayoutManager
        if (mStatusLayoutManager.contentLayoutId != -1) {
            addLayout(mStatusLayoutManager.contentLayoutId, LAYOUT_CONTENT_ID)
        }
        if (mStatusLayoutManager.loadingLayoutId != -1) {
            addLayout(mStatusLayoutManager.loadingLayoutId, LAYOUT_LOADING_ID)
        }
        if (mStatusLayoutManager.emptyLayout != null) {
            addView(mStatusLayoutManager.emptyLayout)
        }
        if (mStatusLayoutManager.errorLayout != null) {
            addView(mStatusLayoutManager.errorLayout)
        }
    }

    fun addLayout(@LayoutRes layoutId: Int, id: Int) {
        val layoutView = LayoutInflater.from(context).inflate(layoutId, null)
        layoutSparseArray.put(id, layoutView)
        addView(layoutView)
    }

    fun showLayoutById(id: Int) {
        layoutSparseArray.forEach { key, view ->
            view.visibility = if (key == id) View.VISIBLE else View.GONE
        }
    }

    fun showLoadingLayout() {
        if (layoutSparseArray[LAYOUT_LOADING_ID] != null) {
            showLayoutById(LAYOUT_LOADING_ID)
        }
    }

    fun showContentLayout() {
        if (layoutSparseArray[LAYOUT_CONTENT_ID] != null) {
            showLayoutById(LAYOUT_CONTENT_ID)
        }
    }

    fun showEmptyLayout() {
        if (layoutSparseArray[LAYOUT_EMPTYDATA_ID] == null) {
            if (mStatusLayoutManager.emptyLayout != null) {
                val emptyView = mStatusLayoutManager.emptyLayout?.inflate()
                layoutSparseArray.put(LAYOUT_EMPTYDATA_ID, emptyView)
            }
        }
        showLayoutById(LAYOUT_EMPTYDATA_ID)
    }

    fun showErrorLayout() {
        if (layoutSparseArray[LAYOUT_ERROR_ID] == null) {
            if (mStatusLayoutManager.errorLayout != null) {
                val errorView = mStatusLayoutManager.errorLayout?.inflate()
                layoutSparseArray.put(LAYOUT_ERROR_ID, errorView)
                if(mStatusLayoutManager.errorLayoutClickId != -1){
                    val view = errorView?.findViewById<View>(mStatusLayoutManager.errorLayoutClickId)
                    if(view != null){
                        view.setOnClickListener {
                            mStatusLayoutManager.onStatusLayoutClickListener?.onErrorViewClick(view)
                        }
                    }
                }
            }
        }
        showLayoutById(LAYOUT_ERROR_ID)
    }
}