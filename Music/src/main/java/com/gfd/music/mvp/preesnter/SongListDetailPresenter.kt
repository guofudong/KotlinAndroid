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
 * @description：
 */
class SongListDetailPresenter @Inject constructor() : SongListContract.Presenter, SongListService.GetSongListCallBack {

    @Inject
    lateinit var mView: SongListContract.View

    @Inject
    lateinit var mService: SongListService

    override fun getSongList(id: String, offset: Int) {
        mView.showLoading()
        mService.getSongList(id, offset, this)
    }

    override fun onSongList(data: List<SongItemData>) {
        mView.showSongList(data)
        mView.hideLoading()
    }

    override fun onTitle(title: SongTitleData) {
        mView.showHead(title)
    }

}