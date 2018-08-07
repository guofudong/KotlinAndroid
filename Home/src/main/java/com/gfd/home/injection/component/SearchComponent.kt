package com.gfd.home.injection.component

import com.gfd.common.injection.component.ActivityComponent
import com.gfd.common.injection.scope.PerComponentScope
import com.gfd.home.injection.module.SearchMoudle
import com.gfd.home.ui.activity.SearchActivity
import dagger.Component

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 16:34
 * @Email：878749089@qq.com
 * @descriptio：
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(SearchMoudle::class))
interface SearchComponent {
    fun inject(activity: SearchActivity)
}