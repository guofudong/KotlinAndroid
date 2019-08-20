package com.gfd.player.entity

import com.gfd.common.ui.adapter.MultiItemEntity

/**
 * @Author : 郭富东
 * @Date ：2018/8/22 - 17:16
 * @Email：878749089@qq.com
 * @description：
 */
data class LiveDataDto(
        val lives: List<Live>?
)

data class Live(
        val icon: String,
        val isTitle: Boolean,
        val live: String,
        val name: String
) : MultiItemEntity {
    override fun getItemType(): Int {
        return if (isTitle) 0 else 1
    }

}