package com.gfd.music.mvp.preesnter

import com.gfd.music.entity.SongItemData
import com.gfd.music.entity.SongTitleData
import com.gfd.music.mvp.contract.SongListContract
import com.gfd.music.mvp.service.SongListService
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:51
 * @Email：878749089@qq.com
 * @descriptio：
 */
class SongListPresenter @Inject constructor() : SongListContract.Presenter, SongListService.GetSongListCallBack {

    @Inject
    lateinit var mView: SongListContract.View

    @Inject
    lateinit var mService: SongListService

    override fun getSongList(id: String) {
        mView.showLoading()
        mService.getSongList(id, this)
    }

    override fun onSongList(datas: List<SongItemData>) {
        mView.showSongList(datas)
        mView.hideLoading()
    }

    override fun onTitle(titles: SongTitleData) {
        mView.showHead(titles)
    }

}