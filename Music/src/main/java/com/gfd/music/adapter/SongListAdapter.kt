package com.gfd.music.adapter

import android.content.Context
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.music.R
import com.gfd.music.entity.SongItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/13 - 14:02
 * @Email：878749089@qq.com
 * @description：
 */
class SongListAdapter(context: Context) : BaseAdapter<SongItemData>(context) {

    override fun getItemLayoutId(): Int = R.layout.music_item_songlist

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val songItemData = mData[position]
        holder.apply {
            setText(R.id.tv_item_num, (position + 1).toString())
            setText(R.id.tv_item_name, songItemData.name)
            setText(R.id.tv_item_autor, songItemData.autor)
        }
    }
}
