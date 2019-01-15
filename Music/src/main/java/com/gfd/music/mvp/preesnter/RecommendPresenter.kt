package com.gfd.music.mvp.preesnter

import com.gfd.music.entity.BannerData
import com.gfd.music.entity.SongData
import com.gfd.music.mvp.contract.RecommendContract
import com.gfd.music.mvp.service.RecommendService
import com.gfd.music.mvp.service.impl.RecommendServiceImpl
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:54
 * @Email：878749089@qq.com
 * @descriptio：
 */
class RecommendPresenter @Inject constructor() : RecommendContract.Presenter, RecommendService.GetRecommendCallBack {

    @Inject
    lateinit var mView: RecommendContract.View
    @Inject
    lateinit var mService: RecommendServiceImpl

    override fun getBanner() {
        mService.getBanner(this)

    }

    override fun getSongList(isLoading: Boolean) {
        if (isLoading) {
            mView.showLoading()
        }
        mService.getSongList(this)
    }

    override fun onBanner(datas: List<BannerData>) {
        mView.showBanner(datas)
    }

    override fun onSongList(datas: List<SongData>) {
        mView.showSongList(datas)
        mView.hideLoading()
    }

}