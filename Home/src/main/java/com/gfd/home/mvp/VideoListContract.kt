package com.gfd.home.mvp

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.home.entity.VideoListData

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 18:05
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface VideoListContract {

    interface View : BaseView {
        /**
         * 显示视频列表
         * @param data VideoListData ：数据集合
         */
        fun showVideoList(data: VideoListData)
    }

    interface Presenter : BasePresenter {
        fun getVideoList(isLoading:Boolean)
    }
}