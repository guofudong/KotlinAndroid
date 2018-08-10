package com.gfd.music.mvp.service

import com.gfd.music.entity.BannerData
import com.gfd.music.entity.SongData

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:01
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface RecommendService {

    fun getBanner(callBack: GetRecommendCallBack)

    fun getSongList(callBack: GetRecommendCallBack)

    interface GetRecommendCallBack {
        fun onBanner(datas: List<BannerData>)
        fun onSongList(datas: List<SongData>)
    }
}