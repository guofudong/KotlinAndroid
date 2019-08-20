package com.gfd.music.mvp.preesnter

import com.gfd.music.entity.CommentData
import com.gfd.music.entity.MvData
import com.gfd.music.entity.MvDetailData
import com.gfd.music.mvp.contract.MvDetailContract
import com.gfd.music.mvp.service.MvDetailService
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:51
 * @Email：878749089@qq.com
 * @description：
 */
class MvDetailPresenter @Inject constructor() : MvDetailContract.Presenter, MvDetailService.GetMvDetailCallBack {

    @Inject
    lateinit var mView: MvDetailContract.View
    @Inject
    lateinit var mService: MvDetailService

    override fun getSimilarMv(mvId: String) {
        mService.getSimilarMv(mvId, this)
    }

    override fun getMvComment(mvId: String) {
        mService.getMvComment(mvId, this)
    }

    override fun getMvDetail(mvId: String) {
        mService.getMvDetail(mvId, this)
    }

    override fun onSimilarMv(data: List<MvData>) {
        mView.showSimilarMv(data)

    }

    override fun onMvComment(data: List<CommentData>) {
        mView.showMvComment(data)
    }

    override fun onMvDetail(data: MvDetailData) {
        mView.showMvDetail(data)
    }

}