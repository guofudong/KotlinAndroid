package com.gfd.home.service


import com.gfd.home.entity.VideoListData
import io.reactivex.Observable

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 10:44
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface VideoService {

    interface GetVideoCallBack {

        fun onVideoDataSuccess(data: VideoListData)
    }

    fun getVideoList(callBack: GetVideoCallBack)

}