package com.gfd.player.adapter

import android.content.Context
import android.widget.Button
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.player.R
import com.gfd.player.entity.VideoItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 13:51
 * @Email：878749089@qq.com
 * @descriptio：
 */
class EpisodeAdapter(context: Context) : BaseAdapter<VideoItemData>(context) {

    private  var currentSelect : Int = 0
    override fun getItemLayoutId(): Int {
        return R.layout.player_item_episode
    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        var episodeText: TextView = holder.getView(R.id.tv_item_episode)
        episodeText.isSelected =(currentSelect == position)
        episodeText.text = mDatas[position].episode
    }

    fun setSelect(position: Int){
        currentSelect = position
    }

}