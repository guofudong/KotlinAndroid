package com.gfd.music.entity

import com.gfd.common.ui.adapter.MultiItemEntity

/**
 * @Author : 郭富东
 * @Date ：2018/8/9 - 17:03
 * @Email：878749089@qq.com
 * @descriptio：
 */
data class SongData(var type: Int, var itemTitle: String, var name: String, var recommend_reason: String, var pic_big: String, var pic_premium: String,
                    var song_id: String, var title: String, var url: String, var file_duration: String) : MultiItemEntity {

    override fun getItemType(): Int {
        return type
    }

    constructor(type: Int, itemTitle: String) : this(type, itemTitle, "", "", ""
            , "", "", "", "", "")

}