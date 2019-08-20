package com.gfd.home.injection.module

import com.gfd.home.mvp.MovieListContract
import com.gfd.home.service.MovieListService
import com.gfd.home.service.impl.MovieListServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 15:58
 * @Email：878749089@qq.com
 * @description：Dagger2-Module
 */
@Module
class MovieListModule(val view: MovieListContract.View) {

    @Provides
    fun provideService(service: MovieListServiceImpl): MovieListService {
        return service
    }

    @Provides
    fun provideView(): MovieListContract.View {
        return view
    }
}