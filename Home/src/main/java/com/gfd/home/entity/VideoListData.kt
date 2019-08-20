package com.gfd.home.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 10:59
 * @Email：878749089@qq.com
 * @description：首页-top-轮播图数据
 */
data class VideoListData(
        val bannerUrls: List<BannerData>,//轮播图
        val videoList: List<VideoItemData>
)