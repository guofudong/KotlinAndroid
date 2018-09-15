package com.gfd.crosstalk.mvp.presenter

import com.gfd.crosstalk.entity.Video
import com.gfd.crosstalk.mvp.contract.CrosstalkContract
import com.gfd.crosstalk.mvp.service.CrosstalkService
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/9/15 - 11:03
 * @Email：878749089@qq.com
 * @descriptio：
 */
class CrosstalkPresenter @Inject constructor():CrosstalkContract.Presenter,CrosstalkService.IGetVideoListCallback {

    private  var isLoading: Boolean = false
    @Inject
    lateinit var mView:CrosstalkContract.View
    @Inject
    lateinit var mService:CrosstalkService

    override fun getVideoList(page: Int, isLoading: Boolean) {
        this.isLoading = isLoading
        if(isLoading){
            mView.showLoading()
        }
        mService.getVideoList(page,this)
    }

    override fun onVideoList(datas: List<Video>) {
        mView.showVideoList(datas)
        if(isLoading){
            mView.hideLoading()
        }
    }
}