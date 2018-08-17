package com.gfd.music.common

import com.xiao.nicevideoplayer.NiceVideoPlayerManager

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 10:55
 * @Email：878749089@qq.com
 * @descriptio：
 */
object PlayUtils{

    fun onBackPressd():Boolean{
       return NiceVideoPlayerManager.instance().onBackPressd()
    }

}