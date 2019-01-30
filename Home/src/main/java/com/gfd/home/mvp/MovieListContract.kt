package com.gfd.home.mvp

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.home.entity.MovieData
import com.gfd.home.entity.VideoItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 14:55
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface MovieListContract {

    interface View : BaseView {
        fun showMovies(datas: List<MovieData>)
    }

    interface Presenter : BasePresenter {
        fun getMovieList(cityId: Int, type: Int, isLoading: Boolean)
    }
}