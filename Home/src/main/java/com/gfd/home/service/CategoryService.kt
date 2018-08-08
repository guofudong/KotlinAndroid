package com.gfd.home.service

import com.gfd.home.entity.VideoItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 15:51
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface CategoryService {

    fun getCategoryVideos(url: String,page:Int,state:Int,callBack: GetCategoryVideoCallBack)

    interface GetCategoryVideoCallBack {
        fun onCategoryVideos(datas: List<VideoItemData>,state:Int)
    }
}