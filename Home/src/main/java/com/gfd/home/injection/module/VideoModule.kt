package com.gfd.home.injection.module

import com.gfd.home.mvp.VideoListContract
import com.gfd.home.service.VideoService
import com.gfd.home.service.impl.VideoServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 10:38
 * @Email：878749089@qq.com
 * @descriptio：视频列表Module
 */
@Module
class VideoModule(private val view: VideoListContract.View) {

    @Provides
    fun provideVideoService(videoService: VideoServiceImpl): VideoService {
        return videoService
    }

    @Provides
    fun provideView(): VideoListContract.View {
        return this.view
    }


}