package com.gfd.home.service

import com.gfd.home.entity.MovieData

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 15:51
 * @Email：878749089@qq.com
 * @description：
 */
interface MovieDetailService {

    fun getMovieInfo(movieId: Int, callBack: GetMovieDetailCallBack)

    interface GetMovieDetailCallBack {
        fun onMovieInfo(data: List<MovieData>)
    }
}