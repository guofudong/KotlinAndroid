package com.gfd.crosstalk.injection.component

import com.gfd.common.injection.component.ActivityComponent
import com.gfd.common.injection.scope.PerComponentScope
import com.gfd.crosstalk.ui.fragment.CrosstalkFragment
import com.gfd.crosstalk.injection.moudle.CrosstalkModule
import dagger.Component

/**
 * @Author : 郭富东
 * @Date ：2018/9/15 - 11:42
 * @Email：878749089@qq.com
 * @description：
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)],modules = [(CrosstalkModule::class)])
interface CrosstalkComponent {
    fun inject(fragment: CrosstalkFragment)
}