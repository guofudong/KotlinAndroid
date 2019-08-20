package com.gfd.music.mvp.preesnter

import com.gfd.music.entity.MvData
import com.gfd.music.mvp.contract.ShortFilmContract
import com.gfd.music.mvp.service.ShortFilmService
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:57
 * @Email：878749089@qq.com
 * @description：
 */
class ShortFilmPresenter @Inject constructor() : ShortFilmContract.Presenter,
        ShortFilmService.GetMvCallBack {

    @Inject
    lateinit var mView: ShortFilmContract.View

    private var isLoading: Boolean = false

    @Inject
    lateinit var mService: ShortFilmService

    override fun getMvList(offset: Int, isLoading: Boolean) {
        this.isLoading = isLoading
        if(isLoading){
            mView.showLoading()
        }
        mService.getMvList(offset, this)
    }

    override fun onMvData(data: List<MvData>) {
        mView.showMvList(data)
        if(isLoading){
            mView.hideLoading()
        }
    }

}