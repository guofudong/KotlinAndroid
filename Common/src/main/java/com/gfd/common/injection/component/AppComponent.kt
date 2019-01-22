package com.gfd.common.injection.component

import android.content.Context
import com.gfd.common.injection.module.AppMoudle
import dagger.Component
import javax.inject.Singleton

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 9:50
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Singleton
@Component(modules = [AppMoudle::class])
interface AppComponent{

    fun context():Context

}