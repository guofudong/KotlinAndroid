package com.gfd.home.injection.component

import com.gfd.common.injection.component.ActivityComponent
import com.gfd.common.injection.scope.PerComponentScope
import com.gfd.home.injection.module.CategoryModule
import com.gfd.home.ui.fragment.CategoryFragment
import dagger.Component

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 16:02
 * @Email：878749089@qq.com
 * @descriptio：
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(CategoryModule::class)])
interface CategoryComponent {

    fun inject(fragment: CategoryFragment)

}