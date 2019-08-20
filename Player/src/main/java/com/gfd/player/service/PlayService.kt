package com.gfd.player.service

import com.gfd.player.entity.VideoItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 14:41
 * @Email：878749089@qq.com
 * @description：
 */
interface PlayService {
    fun getVideoUrl(url: String,callBack: GetVideoUrlCallBack)

    fun getWebVideoUrl(url: String,callBack: GetVideoUrlCallBack)

    interface GetVideoUrlCallBack {
        fun videoUrl(url: String, plotText: String)
        fun videoWebData(data: List<VideoItemData>, plotText: String)
    }
}