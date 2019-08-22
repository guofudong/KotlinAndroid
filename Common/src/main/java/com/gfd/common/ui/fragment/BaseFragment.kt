package com.gfd.common.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gfd.common.R
import com.gfd.common.widgets.StateView
import org.jetbrains.anko.find


/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 11:26
 * @Email：878749089@qq.com
 * @description：Fragment的基类
 */

abstract class BaseFragment : Fragment() {

    private var isFirstVisible: Boolean = true
    private var isFirstInvisible: Boolean = true
    //第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
    private var isFirstResume: Boolean = true
    private lateinit var rootView: View
    private var statusView: StateView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(getLayoutId(), null)
        initOperate()
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isSetStateView()) {
            setStatusLayout()
        }
        initView()
        initData()
        setListener()
    }

    /**
     * 是否设置多状态View 默认为true
     * @return Boolean true:设置
     */
    open fun isSetStateView(): Boolean = true

    /** 设置多状态布局*/
    private fun setStatusLayout() {
        val contentView: ViewGroup? = rootView.find(R.id.content_id)
        if (contentView != null) {
            statusView = StateView.inject(contentView)
        }
    }

    /** 进行初始化操作，在onCreateView中调用*/
    open fun initOperate() {}

    /** 设置布局id*/
    abstract fun getLayoutId(): Int

    /** 初始化视图*/
    abstract fun initView()

    /** 初始化数据*/
    abstract fun initData()

    /** 设置监听*/
    open fun setListener() {}


    /** 显示内容View*/
    fun showContent() {
        statusView?.showContent()
    }

    /** 显示loading状态View*/
    fun showLoading() {
        statusView?.showLoading()
    }

    /** 用户第一次可见*/
    open fun onFirstUserVisible() {}

    /** 用户第一次不可见*/
    open fun onFirstUserInvisible() {}

    /** 用户不可见*/
    open fun onUserInvisible() {}

    /** 用户可见时调用*/
    open fun onUserVisible() {}

    open fun onKeyBackPressed(): Boolean = false

    override fun onResume() {
        super.onResume()
        applyPermission()
        if (isFirstResume) {
            isFirstResume = false
            return
        }
        if (userVisibleHint) {
            onUserVisible()
        }
    }

    override fun onPause() {
        super.onPause()
        if (userVisibleHint) {
            onUserInvisible()
        }
    }


    private fun applyPermission() {}

    //判断用户是否可见，在第一次onResume时是不会调用该方法的
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {//用户可见
            if (isFirstVisible) {
                isFirstVisible = false
                onFirstUserVisible()
            } else {
                onUserVisible()
            }
        } else {//用户不可见
            if (isFirstInvisible) {
                isFirstInvisible = false
                onFirstUserInvisible()
            } else {
                onUserInvisible()
            }

        }
    }
}