package com.gfd.music.injection.module

import com.gfd.music.mvp.contract.ShortFilmContract
import com.gfd.music.mvp.service.ShortFilmService
import com.gfd.music.mvp.service.impl.ShortFilmServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/8/13 - 13:55
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Module
class ShortFilmMoudle(val view: ShortFilmContract.View) {

    @Provides
    fun provideView(): ShortFilmContract.View {
        return view
    }

    @Provides
    fun provideService(): ShortFilmService {
        return ShortFilmServiceImpl()
    }
}