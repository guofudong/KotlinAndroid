package com.gfd.common.ui.activity

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.common.widgets.ProgressLoading
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/1 - 16:13
 * @Email：878749089@qq.com
 * @descriptio：
 */
open abstract class BaseMvpActivity<T :BasePresenter<*>>:BaseActivity(),BaseView{

    @Inject
    lateinit var mPresenter: T

    lateinit var mProgressLoading: ProgressLoading

    override fun initOperate(){
        mProgressLoading = ProgressLoading.create(this)
    }

    override fun showLoading() {
        mProgressLoading.showLoading()
    }

    override fun hideLoading() {
        mProgressLoading.hideLoading()
    }

    abstract override fun getLayoutId(): Int


}