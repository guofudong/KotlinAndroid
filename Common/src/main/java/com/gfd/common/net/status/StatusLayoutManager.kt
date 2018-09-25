package com.gfd.common.net.status

import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub

/**
 * @Author : 郭富东
 * @Date ：2018/9/25 - 16:08
 * @Email：878749089@qq.com
 * @descriptio：
 */
class StatusLayoutManager(val builder: Builder) {

    @LayoutRes
    internal var loadingLayoutId: Int = -1
    internal var emptyLayout: ViewStub? = null
    internal var errorLayout: ViewStub? = null
    @IdRes
    internal var emptyLayoutClickId: Int = -1
    @IdRes
    internal var errorLayoutClickId: Int = -1
    @LayoutRes
    internal var contentLayoutId: Int = -1
    internal var statusLayoutHelper: StatusLayoutHelper
    internal var context: Context
    internal var onStatusLayoutClickListener:OnStatusLayoutClickListener? = null

    init {
        this.context = builder.context
        this.loadingLayoutId = builder.loadingLayoutId
        this.emptyLayout = builder.emptyLayout
        this.errorLayout = builder.errorLayout
        this.emptyLayoutClickId = builder.emptyLayoutClickId
        this.errorLayoutClickId = builder.errorLayoutClickId
        this.contentLayoutId = builder.contentLayoutId
        this.statusLayoutHelper = StatusLayoutHelper(context)
        statusLayoutHelper.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        statusLayoutHelper.setStatusLayoutManager(this)
    }

    fun setOnStatusLayoutClickListener(onStatusLayoutClickListener: OnStatusLayoutClickListener){
        this.onStatusLayoutClickListener = onStatusLayoutClickListener
    }

    /**
     * 显示加载布局
     */
    fun showLoading() {
        statusLayoutHelper.showLoadingLayout()
    }

    /**
     * 显示内容
     */
    fun showContent() {
        statusLayoutHelper.showContentLayout()
    }

    /**
     * 显示空布局
     */
    fun showEmptyLayout() {
        statusLayoutHelper.showEmptyLayout()
    }

    /**
     * 显示错误的布局
     */
    fun showErrorLayout() {
        statusLayoutHelper.showErrorLayout()
    }

    fun getRootLayout():View{
        return statusLayoutHelper
    }

    class Builder(val context: Context) {

        @LayoutRes
        internal var loadingLayoutId: Int = -1
        @LayoutRes
        internal var contentLayoutId: Int = -1
        internal lateinit var emptyLayout: ViewStub
        internal lateinit var errorLayout: ViewStub
        @IdRes
        internal var emptyLayoutClickId: Int = -1
        @IdRes
        internal var errorLayoutClickId: Int = -1


        fun setLoadingLayout(@LayoutRes loadingLayoutId: Int): Builder {
            this.loadingLayoutId = loadingLayoutId
            return this
        }

        fun setEmptyLayout(@LayoutRes emptyLayoutId: Int): Builder {
            this.emptyLayout = ViewStub(context, emptyLayoutId)
            return this
        }

        fun setErrorLayout(@LayoutRes errorLayoutId: Int): Builder {
            this.errorLayout = ViewStub(context, errorLayoutId)
            return this
        }

        fun setEmptyLayoutClickId(@IdRes emptyLayoutClickId: Int): Builder {
            this.emptyLayoutClickId = emptyLayoutClickId
            return this
        }

        fun setErrorLayoutClickId(@IdRes errorLayoutClickId: Int): Builder {
            this.errorLayoutClickId = errorLayoutClickId
            return this
        }

        fun setContentLayout(@LayoutRes contentLayout: Int): Builder {
            this.contentLayoutId = contentLayout
            return this
        }

        fun newBuilder(): StatusLayoutManager {
            return StatusLayoutManager(this)
        }
    }
}