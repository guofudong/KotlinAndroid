package com.gfd.common.injection.module

import android.app.Activity
import android.support.v4.app.FragmentActivity
import com.gfd.common.injection.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 9:55
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Module
class ActivityMoudle(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun provideActivity(): Activity {
        return this.activity
    }
}