package com.gfd.home.api

/**
 * @Author ：郭富东
 * @Date：2019/1/30:13:08
 * @Email：878749089@qq.com
 * @description：首页模块相关API
 */
object Api {

    /**
     * 获取所有城市信息
     * @return String
     */
    fun getUrlCityInfos(): String = "https://api-m.mtime.cn/Showtime/HotCitiesByCinema.api"
    /**
     * 正在热映
     * @param cityId Int ：城市id
     * @return String
     */
    fun getUrlMovieHot(cityId: Int): String = "https://api-m.mtime.cn/Showtime/LocationMovies.api?locationId=$cityId"
    /**
     * 即将上映
     * @param cityId Int
     * @return String
     */
    fun getUrlMovieComingNew(cityId: Int): String = "https://api-m.mtime.cn/Movie/MovieComingNew.api?locationId=$cityId"

    /**
     * 获取影片详情
     * @param cityId Int
     * @param movieId Int
     * @return String
     */
    fun getUrlMovieDetail(cityId: Int, movieId: Int): String = "https://ticket-api-m.mtime.cn/movie/detail.api?locationId=$cityId&movieId=$movieId"

    /**
     * 获取影片预告
     * @param pageIndex Int
     * @param movieId Int
     * @return String
     */
    fun getUrlMovieTrailNotice(pageIndex: Int = 1, movieId: Int): String = "https://api-m.mtime.cn/Movie/Video.api?pageIndex=$pageIndex&movieId=$movieId"
    /**
     * 获取影片评论
     * @param movieId Int
     * @return String
     */
    fun getUrlMovieComment(movieId: Int): String = "https://ticket-api-m.mtime.cn/movie/hotComment.api?movieId=$movieId"

    /**
     * 获取剧照
     * @param movieId Int
     * @return String
     */
    fun getUrlMovieStills(movieId: Int): String = "https://api-m.mtime.cn/Movie/ImageAll.api?movieId=$movieId"

    /**
     * 获取影片演员表
     * @param movieId Int
     * @return String
     */
    fun getUrlMovieActors(movieId: Int): String = "https://api-m.mtime.cn/Movie/MovieCreditsWithTypes.api?movieId=$movieId"

}