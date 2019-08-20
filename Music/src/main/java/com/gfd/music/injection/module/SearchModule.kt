package com.gfd.music.injection.module

import com.gfd.music.mvp.contract.SearchContract
import com.gfd.music.mvp.service.SearchService
import com.gfd.music.mvp.service.impl.SearchServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:35
 * @Email：878749089@qq.com
 * @description：
 */
@Module
class SearchModule(private val view: SearchContract.View) {

    @Provides
    fun provideView(): SearchContract.View {
        return view
    }

    @Provides
    fun privodeService(service: SearchServiceImpl): SearchService {
        return service
    }
}