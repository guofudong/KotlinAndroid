package com.gfd.common.mvp.presenter

import com.gfd.common.mvp.view.BaseView

/**
 * @Author : 郭富东
 * @Date ：2018/8/1 - 16:15
 * @Email：878749089@qq.com
 * @descriptio：
 */
open class BasePresenter<T : BaseView> {

    lateinit var mView: T

}