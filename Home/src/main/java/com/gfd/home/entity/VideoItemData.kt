package com.gfd.home.entity

import com.gfd.common.ui.adapter.MultiItemEntity
import com.gfd.home.common.Concant

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 11:02
 * @Email：878749089@qq.com
 * @descriptio：首页视频列表item数据实体类
 */
data class VideoItemData(var tagName: String = "",//视频标签
                         var type: Int = Concant.ITEM_TYPE_IMG,//item类型
                         var videoName: String,//视频的名字
                         var videoImg: String,//视频图片
                         var videoLink: String,//视频链接
                         var title: String = "",//标题
                         var titleType: Int = -1
) : MultiItemEntity {

    override fun getItemType(): Int {
        return this.type
    }

    constructor(titleType:Int) : this("", 0, "", "", "", "",titleType)

}