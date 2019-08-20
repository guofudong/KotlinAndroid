package com.gfd.music.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/15 - 15:41
 * @Email：878749089@qq.com
 * @description：
 */
data class MvDetail(
        val `data`: Data?,
        val code: Int,
        val result: String
)

data class Data(
        val desc: String,
        val id: String,
        val name: String,
        val pic: String,
        val playCount: Int,
        val publishTime: String,
        val singer: String,
        val url: String
)