package com.gfd.home.injection

import com.gfd.common.injection.component.ActivityComponent
import com.gfd.common.injection.scope.PerComponentScope
import com.gfd.home.HomeFragment
import dagger.Component

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 10:38
 * @Email：878749089@qq.com
 * @descriptio：
 */
@PerComponentScope
@Component(modules = arrayOf(VideoModule::class),dependencies = arrayOf(ActivityComponent::class))
interface VideoComponent{
    fun inject(fragment:HomeFragment)
}