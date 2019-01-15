package com.gfd.music.injection.module

import com.gfd.music.mvp.contract.SongListContract
import com.gfd.music.mvp.service.SongListService
import com.gfd.music.mvp.service.impl.SongListServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/8/13 - 13:55
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Module
class SongListDetailMoudle(val view: SongListContract.View) {

    @Provides
    fun provideView(): SongListContract.View {
        return view
    }

    @Provides
    fun provideService(): SongListService {
        return SongListServiceImpl()
    }
}