package com.gfd.video

import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.common.BaseApplication

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 12:56
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MainApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)
    }


}