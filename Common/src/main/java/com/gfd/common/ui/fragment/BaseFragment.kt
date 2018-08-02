package com.gfd.common.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 11:26
 * @Email：878749089@qq.com
 * @descriptio：Fragment的基类
 */
open abstract class BaseFragment : Fragment() {

    private var isFirstVisible: Boolean = true
    private var isFirstInvisible: Boolean = true
    //第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
    private var isFirstResume: Boolean = true
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(getLayoutId(), null)
        initOperate()
        return rootView
    }

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
    fun onFirstUserVisible() {

    }

    /**
     * 用户第一次不可见
     */
    fun onFirstUserInvisible() {

    }

    /**
     * 用户不可见
     */
    fun onUserInvisible() {

    }

    /**
     * 用户可见时调用
     */
    fun onUserVisible() {

    }

}