package com.gfd.common.injection.module

import android.content.Context
import com.gfd.common.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 9:47
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Module
class AppMoudle(private val context:BaseApplication){

    @Singleton
    @Provides
    fun provideContext(): Context {
        return this.context
    }
}