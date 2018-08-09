package com.gfd.home.mvp

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.home.entity.VideoItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 14:55
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface CategoryContract {

    interface View : BaseView {

        fun showVideos(datas: List<VideoItemData>,state:Int)

    }

    interface Presenter : BasePresenter {
        fun getCategoryVideos(url: String,page:Int,state:Int,isLoading:Boolean)
    }
}