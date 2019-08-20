package com.gfd.player.injection.component

import com.gfd.common.injection.component.ActivityComponent
import com.gfd.common.injection.scope.PerComponentScope
import com.gfd.player.injection.moudle.LiveModule
import com.gfd.player.ui.fragment.LiveFragment
import dagger.Component

/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 14:53
 * @Email：878749089@qq.com
 * @description：
 */
@PerComponentScope
@Component(modules = [(LiveModule::class)],dependencies = [(ActivityComponent::class)])
interface LiveComponent {

    fun inject(fragment: LiveFragment)

}