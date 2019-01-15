package com.gfd.music.mvp.contract

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.music.entity.SongItemData
import com.gfd.music.entity.SongTitleData

/**
 * @Author : 郭富东
 * @Date ：2018/8/13 - 11:47
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface SongListContract {

    interface View : BaseView {
        fun showSongList(datas: List<SongItemData>)
        fun showHead(datas: SongTitleData)

    }

    interface Presenter : BasePresenter {
        fun getSongList(id: String, offset: Int)
    }
}