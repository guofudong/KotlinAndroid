package com.gfd.player.injection.moudle

import com.gfd.player.mvp.PlayContract
import com.gfd.player.service.PlayService
import com.gfd.player.service.PlayServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 14:49
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Module
class PlayMoudle(private val view: PlayContract.View) {

    @Provides
    fun provideView(): PlayContract.View {
        return this.view
    }

    @Provides
    fun providePlayService(service:PlayServiceImpl): PlayService {
        return service
    }
}