package com.gfd.music.entity

data class SongSearch(
        val `data`: List<SearchItemData>?,
        val code: Int,
        val result: String
)

data class SearchItemData(
        val id: String,
        val lrc: String,
        val name: String,
        val pic: String,
        val singer: String,
        val time: Int,
        val url: String
)