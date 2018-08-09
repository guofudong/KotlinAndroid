package com.gfd.home.mvp.presenter

import com.gfd.home.entity.VideoListData
import com.gfd.home.mvp.VideoListContract
import com.gfd.home.service.VideoService
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 18:16
 * @Email：878749089@qq.com
 * @descriptio：
 */
class VedioPresenter @Inject constructor() : VideoListContract.Presenter,VideoService.GetVideoCallBack {

    @Inject
    lateinit var videoService: VideoService
    private var isLoading = false

    @Inject
    lateinit var mView : VideoListContract.View

    override fun getVideoList(isLoading:Boolean) {
        this.isLoading = isLoading
        if(isLoading) mView.showLoading()
        videoService.getVideoList(this)
    }

    override fun onVideoDataSuccess(data: VideoListData) {
        mView.showVideoList(data)
        if(isLoading){
            mView.hideLoading()
        }
    }

}