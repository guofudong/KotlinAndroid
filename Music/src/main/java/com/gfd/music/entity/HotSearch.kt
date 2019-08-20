package com.gfd.music.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/23 - 17:47
 * @Email：878749089@qq.com
 * @description：
 */

data class HotSearch(
        val error_code: Int,
        val result: List<Result>?
)

data class Result(
        val linktype: Int,
        val linkurl: String,
        val strong: Int,
        val word: String
)
