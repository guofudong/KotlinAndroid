package com.gfd.common.injection.component

import android.app.Activity
import android.content.Context
import com.gfd.common.injection.module.ActivityMoudle
import com.gfd.common.injection.scope.ActivityScope
import dagger.Component

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 10:00
 * @Email：878749089@qq.com
 * @descriptio：
 */
@ActivityScope
@Component(dependencies = [AppComponent::class],
        modules = [ActivityMoudle::class])
interface ActivityComponent {

    fun context(): Context
    fun activity(): Activity

}