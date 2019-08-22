package com.gfd.common.ui.fragment

import android.app.Activity
import com.gfd.common.common.BaseApplication
import com.gfd.common.injection.component.DaggerActivityComponent
import com.gfd.common.injection.module.ActivityMoudle
import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 11:47
 * @Email：878749089@qq.com
 * @description：MVP架构 Fragment的基类
 */
abstract class BaseMvpFragment<T : BasePresenter> : BaseFragment(), BaseView {

    @Inject
    lateinit var mPresenter: T
    protected lateinit var mActivityComponent: DaggerActivityComponent

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
}