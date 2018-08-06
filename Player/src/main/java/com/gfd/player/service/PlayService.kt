package com.gfd.player.service

/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 14:41
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface PlayService {
    fun getVideoUrl(url: String,callBack: GetVideoUrlCallBack)

    interface GetVideoUrlCallBack {
        fun videoUrl(url: String, plotText: String)
    }
}