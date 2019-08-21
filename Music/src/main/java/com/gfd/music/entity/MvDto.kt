package com.gfd.music.entity

import com.google.gson.annotations.SerializedName

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 17:37
 * @Email：878749089@qq.com
 * @description：
 */

data class MvDto(
        @SerializedName("data") val `data`: MvDataList,
        @SerializedName("code") val code: Int,
        @SerializedName("result") val result: String
)

data class MvDataList(
        @SerializedName("list") val list: List<Dto>?
)

data class Dto(
        @SerializedName("desc") val desc: String?,
        @SerializedName("vid") val id: String,
        @SerializedName("title") val name: String,
        @SerializedName("picurl") val pic: String,
        @SerializedName("playcnt") val playCount: Int,
        @SerializedName("singer") val singer: String?,
        @SerializedName("url") val url: String?,
        @SerializedName("pubdate") val pubdate: Long
)