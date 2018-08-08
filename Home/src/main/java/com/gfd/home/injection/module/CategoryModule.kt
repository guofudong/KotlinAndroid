package com.gfd.home.injection.module

import com.gfd.home.mvp.CategoryContract
import com.gfd.home.service.CategoryService
import com.gfd.home.service.impl.CateGoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 15:58
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Module
class CategoryModule(val view: CategoryContract.View) {

    @Provides
    fun provideService(service: CateGoryServiceImpl): CategoryService {
        return service
    }

    @Provides
    fun provideView(): CategoryContract.View {
        return view
    }
}