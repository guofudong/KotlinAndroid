package com.gfd.home.service

import com.gfd.home.entity.MovieData

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 15:51
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface MovieListService {

    fun getMovieList(cityId: Int, type: Int,callBack: GetMovieListCallBack)

    interface GetMovieListCallBack {
        fun onMovieList(datas: List<MovieData>)
    }
}