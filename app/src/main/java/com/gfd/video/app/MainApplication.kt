package com.gfd.video.app

import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.common.BaseApplication
import com.gfd.video.BuildConfig
import com.tencent.smtt.sdk.QbSdk

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 12:56
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MainApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            // 打印日志
            ARouter.openLog()
            //开启调试模式
            ARouter.openDebug()
        }
        ARouter.init(this)
        QbSdk.initX5Environment(this,null)
    }


}