package com.gfd.crosstalk.mvp.contract

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.crosstalk.entity.Video

/**
 * @Author : 郭富东
 * @Date ：2018/9/15 - 11:01
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface CrosstalkContract {

    interface View : BaseView {
        fun showVideoList(datas:List<Video>)
    }

    interface Presenter: BasePresenter {
        fun getVideoList(page:Int = 1,isLoading:Boolean = false)
    }
}