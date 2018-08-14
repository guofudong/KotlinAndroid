package com.gfd.music.injection.component

import com.gfd.common.injection.component.ActivityComponent
import com.gfd.common.injection.scope.PerComponentScope
import com.gfd.music.injection.module.ShortFilmMoudle
import com.gfd.music.ui.fragment.ShortFilmFragment
import dagger.Component

/**
 * @Author : 郭富东
 * @Date ：2018/8/13 - 13:57
 * @Email：878749089@qq.com
 * @descriptio：
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(ShortFilmMoudle::class)])
interface ShortFilmComponent {
    fun inject(fragment: ShortFilmFragment)
}