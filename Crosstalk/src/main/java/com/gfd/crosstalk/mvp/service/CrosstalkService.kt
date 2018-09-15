package com.gfd.crosstalk.mvp.service

import com.gfd.crosstalk.entity.Video

/**
 * @Author : 郭富东
 * @Date ：2018/9/15 - 11:31
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface CrosstalkService {
    fun getVideoList(page: Int,callback: IGetVideoListCallback)

    interface IGetVideoListCallback{
        fun onVideoList(datas:List<Video>)
    }
}