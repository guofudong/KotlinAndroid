package com.gfd.home.service.impl

import com.gfd.home.api.Api
import com.gfd.home.common.Constant
import com.gfd.home.entity.MovieData
import com.gfd.home.entity.MovieEntity
import com.gfd.home.service.MovieListService
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.orhanobut.logger.Logger
import javax.inject.Inject

/**
 * @Author ：郭富东
 * @Date：2019/1/30:13:43
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MovieListServiceImpl @Inject constructor() : MovieListService {

    override fun getMovieList(cityId: Int, type: Int, callBack: MovieListService.GetMovieListCallBack) {
        OkGo.get<String>(Api.getUrlMovieHot(cityId))
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body()
                        Logger.e("电影数据：$json")
                        val movieDatas = Gson().fromJson(json, MovieEntity::class.java).ms
                        var datas = ArrayList<MovieData>()
                        if (movieDatas.isNotEmpty()) {
                            movieDatas.forEach {
                                if (type == Constant.TYPE_MOVIE_01) {
                                    if (it.r > 0) {
                                        datas.add(MovieData(it.movieId, it.t, it.dn, it.aN1
                                                , it.isIs3D, it.isIsIMAX, it.isIsHot, it.movieType, it.img, it.wantedCount, it.r))
                                    }
                                } else if (type == Constant.TYPE_MOVIE_02) {
                                    if (it.r <= 0) {
                                        datas.add(MovieData(it.movieId, it.t, it.dn, it.aN1
                                                , it.isIs3D, it.isIsIMAX, it.isIsHot, it.movieType, it.img, it.wantedCount, 0.0))
                                    }
                                }

                            }
                            callBack.onMovieList(datas)
                        }
                    }

                })
    }

}