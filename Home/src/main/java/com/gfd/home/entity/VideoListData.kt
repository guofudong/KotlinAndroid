package com.gfd.home.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 10:59
 * @Email：878749089@qq.com
 * @descriptio：
 */
data class VideoListData(
        val bannerUrls: List<BinnerData>,//轮播图
        val videoList: List<VideoItemData>
)