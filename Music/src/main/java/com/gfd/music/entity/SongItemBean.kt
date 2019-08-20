package com.gfd.music.entity

/**
 * @Author : 郭富东
 * @Date ：2018/8/13 - 14:27
 * @Email：878749089@qq.com
 * @description：
 */
data class SongItemBean(
        val `data`: ItemData?,
        val code: Int,
        val result: String
)

data class ItemData(
        val songListCount: Int,
        val songListDescription: String,
        val songListId: String,
        val songListName: String,
        val songListPic: String,
        val songListPlayCount: Int,
        val songListUserId: Int,
        val songs: List<SongBean>?
)

data class SongBean(
        val id: String,
        val lrc: String,
        val name: String,
        val pic: String,
        val singer: String,
        val time: Int,
        val url: String
)