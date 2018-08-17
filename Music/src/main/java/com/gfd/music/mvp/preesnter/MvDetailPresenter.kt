package com.gfd.music.mvp.preesnter

import com.gfd.music.entity.CommentData
import com.gfd.music.entity.MvData
import com.gfd.music.mvp.contract.MvDetailContract
import com.gfd.music.mvp.contract.RadioContract
import com.gfd.music.mvp.service.MvDetailService
import com.gfd.music.mvp.service.impl.MvDetailServiceImpl
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:51
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MvDetailPresenter @Inject constructor():MvDetailContract.Presenter,MvDetailService.GetMvDetailCallBack{

    @Inject
    lateinit var mView:MvDetailContract.View
    @Inject
    lateinit var mService: MvDetailService

    override fun getSimiMv(mvId:String) {
        mService.getSimlMv(mvId,this)
    }

    override fun getMvComment(mvId:String) {
        mView.showLoading()
        mService.getMvComment(mvId,this)
    }

    override fun onSimiMv(datas: List<MvData>) {
        mView.showSimiMv(datas)

    }

    override fun onMvCommnet(datas: List<CommentData>) {
        mView.showMvComment(datas)
        mView.hideLoading()
    }

}