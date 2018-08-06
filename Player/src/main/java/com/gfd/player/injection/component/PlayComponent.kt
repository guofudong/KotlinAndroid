package com.gfd.player.injection.component

import com.gfd.common.injection.component.ActivityComponent
import com.gfd.common.injection.scope.PerComponentScope
import com.gfd.player.PlayerActivity
import com.gfd.player.injection.moudle.PlayMoudle
import dagger.Component

/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 14:53
 * @Email：878749089@qq.com
 * @descriptio：
 */
@PerComponentScope
@Component(modules = arrayOf(PlayMoudle::class),dependencies = arrayOf(ActivityComponent::class))
interface PlayComponent {

    fun inject(activity: PlayerActivity)

}