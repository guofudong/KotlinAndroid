package com.gfd.home.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 10:54
 * @Email：878749089@qq.com
 * @descriptio：影视数据类
 */
data class VideoData(
        val videoIcon: String,//影视图片
        val name: String,//影视名字
        val type: String,//影视类型
        val playUrl: String //播放url
)