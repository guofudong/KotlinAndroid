package com.gfd.music.mvp.service

import com.gfd.music.entity.SongItemData
import com.gfd.music.entity.SongTitleData

/**
 * @Author : 郭富东
 * @Date ：2018/8/13 - 13:49
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface SongListService {

    fun getSongList(id: String, offset: Int, callBack: GetSongListCallBack)

    interface GetSongListCallBack {
        fun onSongList(datas: List<SongItemData>)
        fun onTitle(titles: SongTitleData)
    }
}