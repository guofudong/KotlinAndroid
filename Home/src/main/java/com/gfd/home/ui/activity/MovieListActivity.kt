package com.gfd.home.ui.activity

import com.gfd.common.ext.listInit
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.home.R
import com.gfd.home.adapter.MovieListAdapter
import com.gfd.home.common.Concant
import com.gfd.home.entity.MovieData
import com.gfd.home.injection.component.DaggerMovieListComponent
import com.gfd.home.injection.module.MovieListModule
import com.gfd.home.mvp.MovieListContract
import com.gfd.home.mvp.presenter.MovieListPresenter
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import kotlinx.android.synthetic.main.home_activity_movie_list.*

/**
 * @Author ：郭富东
 * @Date：2019/1/30:13:27
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MovieListActivity : BaseMvpActivity<MovieListPresenter>(), MovieListContract.View {

    private lateinit var mMovieListAdapter: MovieListAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private var movieType = Concant.TYPE_MOVIE_01
    //默认为北京
    private var cityId = 290

    override fun injectComponent() {
        DaggerMovieListComponent.builder().activityComponent(mActivityComponent)
                .movieListModule(MovieListModule(this))
                .build().inject(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.home_activity_movie_list
    }


    override fun initView() {
        if (intent != null) {
            val title = intent.getStringExtra("title")
            homeMovieListTitle.text = title
            movieType = intent.getIntExtra("movieType", Concant.TYPE_MOVIE_01)
        }
        mMovieListAdapter = MovieListAdapter(this)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mMovieListAdapter)
        homeMovieList.listInit(this, mLRecyclerViewAdapter)
    }

    override fun initData() {
        mPresenter.getMovieList(cityId, movieType, true)
    }

    override fun setListener() {
        mLRecyclerViewAdapter.setOnItemClickListener { view, position ->
            val movieId = mMovieListAdapter.getDatas()[position].movieId
        }
    }

    override fun showMovies(datas: List<MovieData>) {
        mMovieListAdapter.updateData(datas)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }
}