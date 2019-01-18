package com.gfd.common.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.gfd.common.common.BaseApplication
import com.gfd.common.injection.component.DaggerActivityComponent
import com.gfd.common.injection.module.ActivityMoudle
import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.common.utils.LoadingHelper
import com.gfd.common.widgets.ProgressLoading
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 11:47
 * @Email：878749089@qq.com
 * @descriptio：MVP架构 Fragment的基类
 */
abstract class BaseMvpFragment<T : BasePresenter> : BaseFragment(), BaseView {

    @Inject
    lateinit var mPresenter: T
    lateinit var mProgressLoading: ProgressLoading
    lateinit var mActivityComponent: DaggerActivityComponent

    override fun initOperate() {
        initActivityInjection()
        injectComponent()
    }

    /** 注册依赖关系 */
    abstract fun injectComponent()

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent((activity?.application as BaseApplication).appComponent)
                .activityMoudle(ActivityMoudle(activity as Activity))
                .build() as DaggerActivityComponent
    }

    override fun showLoading() {
        LoadingHelper.showLoading(activity)
    }

    override fun hideLoading() {
        LoadingHelper.hideLoading(activity)
    }

    override fun error() {
        mStatusLayoutManager.showErrorLayout()
    }

    override fun empty() {
        mStatusLayoutManager.showEmptyLayout()
    }

}