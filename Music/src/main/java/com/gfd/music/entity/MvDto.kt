package com.gfd.music.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 17:37
 * @Email：878749089@qq.com
 * @description：
 */

data class MvDto(
        val `data`: List<Dto>?,
        val code: Int,
        val result: String
)

data class Dto(
        val desc: String?,
        val id: String,
        val name: String,
        val pic: String,
        val playCount: Int,
        val singer: String,
        val url: String
)