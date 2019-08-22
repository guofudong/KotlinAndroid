package com.gfd.common.ui.activity

import com.gfd.common.common.BaseApplication
import com.gfd.common.injection.component.DaggerActivityComponent
import com.gfd.common.injection.module.ActivityMoudle
import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/1 - 16:13
 * @Email：878749089@qq.com
 * @description：MVP模式下-Activity的基类
 */
abstract class BaseMvpActivity<T : BasePresenter> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    protected lateinit var mActivityComponent: DaggerActivityComponent

    override fun initOperate() {
        initActivityInjection()
        injectComponent()
    }

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
                .activityMoudle(ActivityMoudle(this))
                .appComponent((application as BaseApplication).appComponent)
                .build() as DaggerActivityComponent
    }

    /**
     * 注册依赖对象
     */
    abstract fun injectComponent()

}