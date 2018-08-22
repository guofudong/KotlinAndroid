package com.gfd.player.injection.moudle

import com.gfd.player.mvp.contract.LiveContract
import com.gfd.player.mvp.contract.PlayContract
import com.gfd.player.service.LiveApiService
import com.gfd.player.service.PlayService
import com.gfd.player.service.impl.LiveApiServiceImpl
import com.gfd.player.service.impl.PlayServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 14:49
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Module
class LiveMoudle(private val view: LiveContract.View) {

    @Provides
    fun provideView(): LiveContract.View {
        return this.view
    }

    @Provides
    fun providePlayService(): LiveApiService {
        return LiveApiServiceImpl()
    }
}