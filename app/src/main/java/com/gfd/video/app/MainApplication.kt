package com.gfd.video.app

import com.gfd.common.common.BaseApplication
import com.tencent.smtt.sdk.QbSdk

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 12:56
 * @Email：878749089@qq.com
 * @description：app-Application
 */
class MainApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        //初始化腾讯X5-WebView
        QbSdk.initX5Environment(this, null)
    }

}