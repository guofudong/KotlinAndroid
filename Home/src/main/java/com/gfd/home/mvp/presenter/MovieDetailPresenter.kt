package com.gfd.home.mvp.presenter

import com.gfd.home.entity.MovieData
import com.gfd.home.mvp.MovieDetailContract
import com.gfd.home.service.MovieDetailService
import com.gfd.home.service.MovieListService
import javax.inject.Inject

/**
 * @Author ：郭富东
 * @Date：2019/1/30:13:33
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MovieDetailPresenter @Inject constructor() : MovieDetailContract.Presenter, MovieDetailService.GetMovieDetailCallBack {

    @Inject
    lateinit var mView: MovieDetailContract.View

    @Inject
    lateinit var mService: MovieDetailService

    private var isLoading = false

    override fun getMovieInfo(movieId: Int, isLoading: Boolean) {
        this.isLoading = isLoading
        if (this.isLoading) {
            mView.showLoading()
        }
        mService.getMovieInfo(movieId, this)

    }

    override fun onMovieInfos(datas: List<MovieData>) {
        mView.showMovieInfo(datas)
        if (this.isLoading) {
            mView.hideLoading()
        }
    }

}