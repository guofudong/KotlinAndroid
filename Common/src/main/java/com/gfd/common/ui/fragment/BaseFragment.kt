package com.gfd.common.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gfd.common.R
import com.gfd.common.net.status.StatusLayoutManager


/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 11:26
 * @Email：878749089@qq.com
 * @descriptio：Fragment的基类
 */

abstract class BaseFragment : Fragment() {

    private var isFirstVisible: Boolean = true
    private var isFirstInvisible: Boolean = true
    //第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
    private var isFirstResume: Boolean = true
    private lateinit var rootView: View
    protected lateinit var mStatusLayoutManager: StatusLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initStatusLayout()
        rootView = mStatusLayoutManager.getRootLayout()
        initOperate()
        return rootView
    }

    private fun initStatusLayout() {
        mStatusLayoutManager = StatusLayoutManager.Builder(activity as Context)
                .setContentLayout(getLayoutId())
                .setEmptyLayout(R.layout.layout_status_layout_manager_empty)
                .setErrorLayout(R.layout.layout_status_layout_manager_error)
                .setLoadingLayout(R.layout.layout_status_layout_manager_loading)
                .setErrorLayoutClickId(R.id.error_click)
                .newBuilder()
        mStatusLayoutManager.showContent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        setListener()
    }

    abstract fun initView()

    abstract fun initData()

    open fun setListener() {}

    /**
     * 进行初始化操作，在onCreateView中调用
     */
    open fun initOperate() {

    }

    /**
     * 设置布局id
     */
    abstract fun getLayoutId(): Int

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

    private fun applyPermission() {

    }

    override fun onPause() {
        super.onPause()
        if (userVisibleHint) {
            onUserInvisible()
        }
    }

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


    /**
     * 用户第一次可见
     */
    open fun onFirstUserVisible() {

    }

    /**
     * 用户第一次不可见
     */
    open fun onFirstUserInvisible() {

    }

    /**
     * 用户不可见
     */
    open fun onUserInvisible() {

    }

    /**
     * 用户可见时调用
     */
    open fun onUserVisible() {

    }

    open fun onKeyBackPressed(): Boolean {
        return false
    }

}