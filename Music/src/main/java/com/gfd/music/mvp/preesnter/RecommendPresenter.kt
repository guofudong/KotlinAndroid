package com.gfd.music.mvp.preesnter

import com.gfd.music.entity.BannerData
import com.gfd.music.entity.RadioData
import com.gfd.music.entity.SongData
import com.gfd.music.mvp.contract.RecommendContract
import com.gfd.music.mvp.service.RecommendService
import com.gfd.music.mvp.service.impl.RecommendServiceImpl
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:54
 * @Email：878749089@qq.com
 * @description：
 */
class RecommendPresenter @Inject constructor() : RecommendContract.Presenter, RecommendService.GetRecommendCallBack {

    @Inject
    lateinit var mView: RecommendContract.View
    @Inject
    lateinit var mService: RecommendServiceImpl

    private var isLoading = false


    override fun getBanner() {
        if (!isLoading) {
            isLoading = true
        }
        if (isLoading) mView.showLoading()
        mService.getBanner(this)

    }

    override fun getSongList(isLoading: Boolean) {
        this.isLoading = isLoading
        if (this.isLoading) mView.showLoading()
        mService.getSongList(this)
    }


    override fun getRadioData() {
        if (!isLoading) {
            isLoading = true
        }
        if (isLoading) mView.showLoading()
        mService.getRadioData(this)
    }

    override fun onBanner(data: List<BannerData>) {
        mView.showBanner(data)
        if (isLoading) {
            isLoading = false
            mView.showContent()
        }
    }

    override fun onSongList(data: List<SongData>) {
        mView.showSongList(data)
        if (isLoading) {
            isLoading = false
            mView.showContent()
        }
    }

    override fun onRadioData(data: List<RadioData>) {
        mView.showRadioData(data)
        if (isLoading) {
            isLoading = false
            mView.showContent()
        }
    }

}