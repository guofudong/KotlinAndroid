package com.gfd.music.injection.component

import com.gfd.common.injection.component.ActivityComponent
import com.gfd.common.injection.scope.PerComponentScope
import com.gfd.music.injection.module.MusicMoudle
import com.gfd.music.injection.module.MvDetialMoudle
import com.gfd.music.ui.activity.MvDetailActivity
import com.gfd.music.ui.fragment.RecommendFragment
import dagger.Component

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:38
 * @Email：878749089@qq.com
 * @descriptio：
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(MvDetialMoudle::class)])
interface MvDetailComponent {
    fun inject(activity: MvDetailActivity)
}