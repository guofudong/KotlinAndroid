package com.gfd.music.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 17:19
 * @Email：878749089@qq.com
 * @description：
 */
data class Song(
        val `data`: List<SData>?,
        val code: Int,
        val result: String
)

data class SData(
        val coverImgUrl: String,
        val creator: String,
        val description: String,
        val id: Long,
        val playCount: Int,
        val songNum: Int,
        val title: String
)