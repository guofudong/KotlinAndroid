package com.gfd.music.injection.component

import com.gfd.common.injection.component.ActivityComponent
import com.gfd.common.injection.scope.PerComponentScope
import com.gfd.music.injection.module.SearchModule
import com.gfd.music.ui.activity.SearchActivity
import dagger.Component

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:38
 * @Email：878749089@qq.com
 * @description：
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(SearchModule::class)])
interface SearchComponent {
    fun inject(activity: SearchActivity)
}