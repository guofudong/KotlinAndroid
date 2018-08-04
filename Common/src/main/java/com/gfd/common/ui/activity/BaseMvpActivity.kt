package com.gfd.common.ui.activity

import com.gfd.common.common.BaseApplication
import com.gfd.common.injection.component.DaggerActivityComponent
import com.gfd.common.injection.module.ActivityMoudle
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
abstract class BaseMvpActivity<T : BasePresenter> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: DaggerActivityComponent

    lateinit var mProgressLoading: ProgressLoading

    override fun initOperate() {
        initActivityInjection()
        injectComponent()
        mProgressLoading = ProgressLoading.create(this)

    }

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityMoudle(ActivityMoudle(this))
                .appComponent((application as BaseApplication).appComponent)
                .build() as DaggerActivityComponent
    }

    override fun showLoading() {
        mProgressLoading.showLoading()
    }

    override fun hideLoading() {
        mProgressLoading.hideLoading()
    }

    /**
     * 注册依赖对象
     */
    abstract fun injectComponent()

}