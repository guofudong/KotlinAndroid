package com.gfd.music.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 17:09
 * @Email：878749089@qq.com
 * @description：
 */
data class SimiMV(
        val `data`: MData,
        val code: Int,
        val result: String
)

data class MData(
        val videoCount: Int,
        val videos: List<Video>
)

data class Video(
        val alg: String,
        val aliaName: Any,
        val coverUrl: String,
        val creator: List<Creator>,
        val durationms: Int,
        val markTypes: List<Int>,
        val playTime: Int,
        val title: String,
        val transName: Any,
        val type: Int,
        val vid: String
)

data class Creator(
        val userId: Int,
        val userName: String
)