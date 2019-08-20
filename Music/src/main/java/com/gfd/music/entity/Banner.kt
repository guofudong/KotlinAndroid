package com.gfd.music.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:20
 * @Email：878749089@qq.com
 * @description：
 */
data class Banner(
        val error_code: Int,
        val pic: List<Pic>?
)

data class Pic(
        val code: String,
        val ipad_desc: String,
        val is_publish: String,
        val mo_type: Int,
        val randpic: String,
        val randpic_2: String,
        val randpic_desc: String,
        val randpic_ios5: String,
        val randpic_ipad: String,
        val randpic_iphone6: String,
        val randpic_qq: String,
        val special_type: Int,
        val type: Int
)