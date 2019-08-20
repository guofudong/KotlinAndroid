package com.gfd.home.ui.activity

import com.gfd.common.ext.listInit
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.home.R
import com.gfd.home.adapter.MovieListAdapter
import com.gfd.home.common.Constant
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
 * @description：首页-top分类-电影列表页面
 */
class MovieListActivity : BaseMvpActivity<MovieListPresenter>(), MovieListContract.View {

    private lateinit var mMovieListAdapter: MovieListAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private var movieType = Constant.TYPE_MOVIE_01
    //默认为北京
    private var cityId = 290

    override fun injectComponent() {
        DaggerMovieListComponent.builder().activityComponent(mActivityComponent)
                .movieListModule(MovieListModule(this))
                .build().inject(this)
    }

    override fun getLayoutId(): Int = R.layout.home_activity_movie_list


    override fun initView() {
        if (intent != null) {
            val title = intent.getStringExtra("title")
            homeMovieListTitle.text = title
            movieType = intent.getIntExtra("movieType", Constant.TYPE_MOVIE_01)
        }
        mMovieListAdapter = MovieListAdapter(this)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mMovieListAdapter)
        homeMovieList.listInit(this, mLRecyclerViewAdapter)
    }

    override fun initData() {
        mPresenter.getMovieList(cityId, movieType, true)
    }

    override fun setListener() {
        mLRecyclerViewAdapter.setOnItemClickListener { _, position ->
            //val movieId = mMovieListAdapter.getData()[position].movieId
        }
    }

    override fun showMovies(data: List<MovieData>) {
        mMovieListAdapter.updateData(data)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }
}


