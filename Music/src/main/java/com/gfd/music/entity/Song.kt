package com.gfd.music.entity

import com.google.gson.annotations.SerializedName

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 17:19
 * @Email：878749089@qq.com
 * @description：
 */
data class Song(
        val `data`: List<SData>?,
        val code: Int,
        val msg: String
)

data class SData(
        @SerializedName("coverImgUrl") val coverImgUrl: String,
        @SerializedName("description") val description: String,
        @SerializedName("id") val id: Long,
        @SerializedName("playCount") val playCount: Int,
        @SerializedName("trackCount") val songNum: Int,
        @SerializedName("name") val title: String
)

