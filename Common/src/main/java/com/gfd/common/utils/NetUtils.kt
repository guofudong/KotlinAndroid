package com.gfd.common.utils

import android.content.Context
import android.net.ConnectivityManager
import com.gfd.common.common.BaseApplication.Companion.context

/**
 * @Author : 郭富东
 * @Date ：2018/9/25 - 14:32
 * @Email：878749089@qq.com
 * @descriptio：网络工具类
 */
class NetUtils {


    companion object {
        /**
         * 当前网络是否可用
         */
        val NETWORK_ENABLE: Boolean
            get() {
                val connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val info = connManager.activeNetworkInfo ?: return false
                return info.isConnected
            }
    }


}

