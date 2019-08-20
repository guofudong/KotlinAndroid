package com.gfd.player.mvp.presenter

import android.content.Context
import com.gfd.player.entity.Live
import com.gfd.player.entity.LiveDataDto
import com.gfd.player.entity.TimeTableData
import com.gfd.player.mvp.contract.LiveContract
import com.gfd.player.service.LiveApiService
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/20 - 16:28
 * @Email：878749089@qq.com
 * @description：
 */
class LivePresenter @Inject constructor(): LiveContract.Presenter,LiveApiService.IGetLiveCallback{
    @Inject
    lateinit var mView : LiveContract.View

    @Inject
    lateinit var mService:LiveApiService

    override fun getLiveInfo(context: Context) {
        mView.showLoading()
        mService.getLiveInfo(context,this)
    }

    override fun onLive(data: List<Live>) {
        mView.showLiveInfo(data)
        mView.hideLoading()
    }

    override fun getPlayUrl(context: Context, url: String) {
        mView.showLoading()
        mService.getPlayUrl(context,url,this)
    }

    override fun onTimeTables(data: List<TimeTableData>) {
    }

    override fun onPlayUrl(url: String) {
        mView.showVideo(url)
        mView.hideLoading()
    }
}