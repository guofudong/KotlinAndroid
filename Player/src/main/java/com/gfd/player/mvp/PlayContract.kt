package com.gfd.player.mvp

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.player.entity.VideoItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 14:33
 * @Email：878749089@qq.com
 * @descriptio：视频播放页面协议
 */
interface PlayContract {

    interface View : BaseView {

        /**
         * 播放视频
         * @param videoUrl String ： 视频地址
         */
        fun playVideo(videoUrl: String)

        /**
         * 播放网页数据集合
         * @param videoUrl List<VideoItemData> ： 视频地址
         */
        fun playWebVideo(datas: List<VideoItemData>)

        /**
         * 显示剧情简介
         * @param plotText String
         */
        fun showVideoPlot(plotText: String)

    }

    interface Presenter : BasePresenter {

        /**
         * 获取视频地址
         * @param url String ： 要解析的地址
         */
        fun getVideoUrl(url: String)

        /**
         * 获取网页视频播放地址
         * @param url String
         */
        fun getWebVideoUrl(url: String)

    }
}