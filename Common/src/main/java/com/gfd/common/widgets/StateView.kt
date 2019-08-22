package com.gfd.common.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintLayout
import android.support.v4.view.NestedScrollingChild
import android.support.v4.view.NestedScrollingParent
import android.support.v4.view.ScrollingView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gfd.common.R

/**
 * @Author：郭富东
 * @Date：2019/8/15 : 10:13
 * @Email：878749089@qq.com
 * @description：多状态View loading empty error等
 */
class StateView constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    /** empty布局id*/
    private var mEmptyResource: Int = R.layout.base_empty
    /** retry布局id*/
    private var mRetryResource: Int = R.layout.base_retry
    /** loading布局id*/
    private var mLoadingResource: Int = R.layout.base_loading

    /** empty布局解析成的View*/
    private var mEmptyView: View? = null
    /** retry布局解析成的View*/
    private var mRetryView: View? = null
    /** loading布局解析成的View*/
    private var mLoadingView: View? = null

    var inflater: LayoutInflater? = null
    private var mRetryClickListener: (() -> Unit)? = null
    private var currentState = 0

    /** ConstraintLayout 布局参数*/
    private var mLayoutParamsConstrain: ConstraintLayout.LayoutParams? = null

    init {
        mLayoutParamsConstrain = ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        visibility = View.GONE
        //该Status不做绘制，允许优化
        setWillNotDraw(true)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(0, 0)
    }

    @SuppressLint("MissingSuperCall")
    override fun draw(canvas: Canvas) {
    }

    override fun dispatchDraw(canvas: Canvas) {}

    override fun setVisibility(visibility: Int) {
        setVisibility(mEmptyView, visibility)
        setVisibility(mRetryView, visibility)
        setVisibility(mLoadingView, visibility)
    }

    /**
     * 设置View的可见性
     * @param view View?
     * @param visibility Int
     */
    private fun setVisibility(view: View?, visibility: Int) {
        if (visibility != view?.visibility) {
            view?.visibility = visibility
        }
    }

    /**
     * 显示内容
     */
    fun showContent() {
        currentState = STATE_CONTENT
        //隐藏状态View，调用setVisibility(visibility: Int)方法
        visibility = View.GONE
    }

    /**
     * 显示Empty布局
     * @return View
     */
    fun showEmpty(): View {
        currentState = STATE_EMPTY
        if (mEmptyView == null) {
            mEmptyView = inflate(mEmptyResource)
        }
        showView(mEmptyView!!)
        return mEmptyView!!
    }

    /**
     * 显示Retry布局
     * @return View
     */
    fun showRetry(): View {
        currentState = STATE_ERROR
        if (mRetryView == null) {
            mRetryView = inflate(mRetryResource)
            mRetryView!!.setOnClickListener {
                mRetryClickListener?.invoke()
            }
        }
        showView(mRetryView!!)
        return mRetryView!!
    }

    /**
     * 显示Loading布局
     * @return View
     */
    fun showLoading(): View {
        currentState = STATE_LOADING
        if (mLoadingView == null) {
            mLoadingView = inflate(mLoadingResource)
        }
        showView(mLoadingView!!)
        return mLoadingView!!
    }

    /**
     * 显示状态View
     * @param view View
     */
    private fun showView(view: View) {
        setVisibility(view, View.VISIBLE)
        hideViews(view)
    }

    /**
     * 隐藏除了传入的View的其他View
     * @param showView View
     */
    private fun hideViews(showView: View) {
        when {
            mEmptyView === showView -> {
                setVisibility(mLoadingView, View.GONE)
                setVisibility(mRetryView, View.GONE)
            }
            mLoadingView === showView -> {
                setVisibility(mEmptyView, View.GONE)
                setVisibility(mRetryView, View.GONE)
            }
            else -> {
                setVisibility(mEmptyView, View.GONE)
                setVisibility(mLoadingView, View.GONE)
            }
        }
    }

    private fun inflate(@LayoutRes layoutResource: Int): View {
        val viewParent = parent ?: throw IllegalStateException("StateView必须有一个父布局")

        val factory = inflater ?: LayoutInflater.from(context)
        val view = factory.inflate(layoutResource, viewParent as ViewGroup, false)
        val index = viewParent.indexOfChild(this)
        // 防止还能触摸底下的 View
        view.isClickable = true
        view.visibility = View.GONE
        val layoutParams = layoutParams
        if (layoutParams != null) {
            when (viewParent) {
                is ConstraintLayout -> viewParent.addView(view, index, mLayoutParamsConstrain)
                else -> viewParent.addView(view, index, layoutParams)
            }
        } else {
            viewParent.addView(view, index)
        }
        if (mLoadingView != null && mRetryView != null && mEmptyView != null) {
            viewParent.removeViewInLayout(this)
        }
        return view
    }

    /**
     * 设置 emptyView 的自定义 Layout
     * @param emptyResource emptyView 的 layoutResource
     */
    fun setEmptyResource(@LayoutRes emptyResource: Int) {
        this.mEmptyResource = emptyResource
    }

    /**
     * 设置 retryView 的自定义 Layout
     * @param retryResource retryView 的 layoutResource
     */
    fun setRetryResource(@LayoutRes retryResource: Int) {
        this.mRetryResource = retryResource
    }

    /**
     * 设置 loadingView 的自定义 Layout
     * @param loadingResource loadingView 的 layoutResource
     */
    fun setLoadingResource(@LayoutRes loadingResource: Int) {
        mLoadingResource = loadingResource
    }

    /**
     * 监听重试
     * @param listener () -> Unit
     */
    fun setOnRetryClickListener(listener: () -> Unit) {
        this.mRetryClickListener = listener
    }

    /**
     * 从错误恢复到内容
     */
    fun restoreContent() {
        if (currentState == STATE_ERROR) {
            showContent()
        }
    }

    companion object {

        const val STATE_ERROR = 0x00
        const val STATE_LOADING = 0x01
        const val STATE_CONTENT = 0x02
        const val STATE_EMPTY = 0x03

        /**
         * 注入到 ViewGroup 中
         * @param parentView extends ViewGroup
         * @return StateView
         */
        fun inject(parentView: ViewGroup): StateView {
            var parent = parentView
            // 因为 LinearLayout/ScrollView/AdapterView 的特性
            // 为了 StateView 能正常显示，自动再套一层（开发的时候就不用额外的工作量了）
            if (parent is LinearLayout || parent is ScrollView || parent is AdapterView<*> ||
                    parent is ScrollingView && parent is NestedScrollingChild ||
                    parent is NestedScrollingParent && parent is NestedScrollingChild) {
                val viewParent = parent.parent
                if (viewParent == null) {
                    throw IllegalArgumentException("注入的ViewGroup必须有一个父布局")
                } else {
                    val root = FrameLayout(parent.context)
                    root.layoutParams = parent.layoutParams
                    if (viewParent is ViewGroup) {
                        // 把 parent 从它自己的父容器中移除
                        viewParent.removeView(parent)
                        // 然后替换成新的
                        viewParent.addView(root)
                    }
                    val layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    parent.layoutParams = layoutParams
                    root.addView(parent)
                    parent = root
                }
            }
            val stateView = StateView(parent.context)
            parent.addView(stateView)
            stateView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            stateView.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            return stateView
        }
    }
}
