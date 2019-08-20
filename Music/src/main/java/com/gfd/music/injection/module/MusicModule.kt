package com.gfd.music.injection.module

import com.gfd.music.mvp.contract.RecommendContract
import com.gfd.music.mvp.service.RecommendService
import com.gfd.music.mvp.service.impl.RecommendServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:35
 * @Email：878749089@qq.com
 * @description：
 */
@Module
class MusicModule(private val reView: RecommendContract.View) {

    @Provides
    fun provideReView(): RecommendContract.View {
        return reView
    }

    @Provides
    fun privodeReService(reService: RecommendServiceImpl): RecommendService {
        return reService
    }
}