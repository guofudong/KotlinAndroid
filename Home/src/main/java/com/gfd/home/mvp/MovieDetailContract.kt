package com.gfd.home.mvp

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.home.entity.MovieData
import com.gfd.home.entity.VideoItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 14:55
 * @Email：878749089@qq.com
 * @description：电影详情-MVP-Contract
 */
interface MovieDetailContract {

    interface View : BaseView {
        fun showMovieInfo(data: List<MovieData>)
    }

    interface Presenter : BasePresenter {
        fun getMovieInfo(movieId: Int, isLoading: Boolean)
    }
}