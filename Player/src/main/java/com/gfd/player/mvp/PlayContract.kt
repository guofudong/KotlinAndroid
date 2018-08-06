package com.gfd.player.mvp

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView

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


    }
}