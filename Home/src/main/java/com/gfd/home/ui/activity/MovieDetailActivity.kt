package com.gfd.home.ui.activity

import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.home.R
import com.gfd.home.entity.MovieData
import com.gfd.home.mvp.MovieDetailContract
import com.gfd.home.mvp.presenter.MovieDetailPresenter

/**
 * @Author ：郭富东
 * @Date：2019/1/31:14:36
 * @Email：878749089@qq.com
 * @description：电影详情页面
 */
class MovieDetailActivity : BaseMvpActivity<MovieDetailPresenter>(), MovieDetailContract.View {

    override fun injectComponent() {}

    override fun getLayoutId(): Int = R.layout.home_activity_movie_detail

    override fun initView() {}

    override fun initData() {}

    override fun showMovieInfo(data: List<MovieData>) {}
}