package com.gfd.player.injection.component

import com.gfd.common.injection.component.ActivityComponent
import com.gfd.common.injection.scope.PerComponentScope
import com.gfd.player.injection.moudle.LiveMoudle
import com.gfd.player.ui.activity.PlayWebActivity
import com.gfd.player.ui.activity.PlayerActivity
import com.gfd.player.injection.moudle.PlayMoudle
import com.gfd.player.ui.fragment.LiveFragment
import dagger.Component

/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 14:53
 * @Email：878749089@qq.com
 * @descriptio：
 */
@PerComponentScope
@Component(modules = [(LiveMoudle::class)],dependencies = [(ActivityComponent::class)])
interface LiveComponent {

    fun inject(fragment: LiveFragment)

}