package com.gfd.home.entity

/**
 * @Author ：郭富东
 * @Date：2019/1/30:13:30
 * @Email：878749089@qq.com
 * @description：电影数据实体类
 */
data class MovieData(var movieId: Int, var movieName: String, var director: String, var starring: String,
                     var is3D: Boolean, var isIMAX: Boolean, var isHot: Boolean, var movieType: String, var img: String,
                     var wantedCount: Int, val fraction: Double)