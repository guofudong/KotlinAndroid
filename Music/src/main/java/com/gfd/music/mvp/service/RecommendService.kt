package com.gfd.music.mvp.service

import com.gfd.music.entity.BannerData
import com.gfd.music.entity.RadioData
import com.gfd.music.entity.SongData

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:01
 * @Email：878749089@qq.com
 * @description：
 */
interface RecommendService {

    fun getBanner(callBack: GetRecommendCallBack)

    fun getSongList(callBack: GetRecommendCallBack)
    fun getRadioData(callBack: GetRecommendCallBack)

    interface GetRecommendCallBack {
        fun onBanner(data: List<BannerData>)
        fun onSongList(data: List<SongData>)
        fun onRadioData(data: List<RadioData>)
    }
}