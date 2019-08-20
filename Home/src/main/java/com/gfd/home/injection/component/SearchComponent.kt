package com.gfd.home.injection.component

import com.gfd.common.injection.component.ActivityComponent
import com.gfd.common.injection.scope.PerComponentScope
import com.gfd.home.injection.module.SearchModule
import com.gfd.home.ui.activity.SearchActivity
import dagger.Component

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 16:34
 * @Email：878749089@qq.com
 * @description：搜索页面-Dagger2-Component
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(SearchModule::class)])
interface SearchComponent {
    fun inject(activity: SearchActivity)
}