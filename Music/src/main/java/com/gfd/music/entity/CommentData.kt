package com.gfd.music.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 16:31
 * @Email：878749089@qq.com
 * @descriptio：
 */
data class CommentData(var userName: String, var userPic: String, var content: String,
                       var likedCount: Int, var time: Long, var liked: Boolean)