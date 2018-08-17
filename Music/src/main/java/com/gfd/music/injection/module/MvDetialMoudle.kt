package com.gfd.music.injection.module

import com.gfd.music.mvp.contract.MvDetailContract
import com.gfd.music.mvp.service.MvDetailService
import com.gfd.music.mvp.service.impl.MvDetailServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 16:56
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Module
class MvDetialMoudle(val view:MvDetailContract.View){

    @Provides
    fun provideView():MvDetailContract.View{
        return view
    }

    @Provides
    fun provideService():MvDetailService{
        return MvDetailServiceImpl()
    }
}