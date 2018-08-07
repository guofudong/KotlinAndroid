package com.gfd.home.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 16:21
 * @Email：878749089@qq.com
 * @descriptio：
 */
data class SearchItemData(var name: String, var imgUrl: String, var videoUrl: String,
                          var actor: String, var plot: String,var type:String,var tag:Int){

    companion object {
        const val TAG_MOVIE = 1
        const val TAG_EPISODE = 2
    }
}