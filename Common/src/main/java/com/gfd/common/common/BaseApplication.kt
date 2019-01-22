package com.gfd.common.common

import android.app.Application
import android.content.Context
import com.gfd.common.BuildConfig
import com.gfd.common.injection.component.DaggerAppComponent
import com.gfd.common.injection.module.AppMoudle
import com.lzy.okgo.OkGo
import com.lzy.okgo.cookie.CookieJarImpl
import com.lzy.okgo.cookie.store.SPCookieStore
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.tencent.bugly.crashreport.CrashReport
import okhttp3.OkHttpClient


/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 9:49
 * @Email：878749089@qq.com
 * @descriptio：
 */
open class BaseApplication : Application() {
    lateinit var appComponent: DaggerAppComponent

    override fun onCreate() {
        super.onCreate()
        initInjection()
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
        context = this
        //初始化腾讯Bugly
        CrashReport.initCrashReport(this, BaseConstant.BUGLY_APPID, true)
    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder()
                .appMoudle(AppMoudle(this))
                .build() as DaggerAppComponent
    }

    companion object {
        lateinit var context: Context
    }
}