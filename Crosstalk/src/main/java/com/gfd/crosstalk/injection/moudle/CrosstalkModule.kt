package com.gfd.crosstalk.injection.moudle

import com.gfd.crosstalk.mvp.contract.CrosstalkContract
import com.gfd.crosstalk.mvp.service.CrosstalkService
import com.gfd.crosstalk.mvp.service.impl.CrosstalkServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @Author : 郭富东
 * @Date ：2018/9/15 - 11:38
 * @Email：878749089@qq.com
 * @description：
 */
@Module
class CrosstalkModule(val view :CrosstalkContract.View){

    @Provides
    fun provideView():CrosstalkContract.View{
        return view
    }

    @Provides
    fun provideService():CrosstalkService{
        return CrosstalkServiceImpl()
    }
}