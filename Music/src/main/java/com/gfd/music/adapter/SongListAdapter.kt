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
 * @descriptio：
 */
class SongListAdapter(context: Context) : BaseAdapter<SongItemData>(context) {

    override fun getItemLayoutId(): Int {
        return R.layout.item_songlist

    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val songItemData = mDatas[position]
        val tvNum = holder.getView<TextView>(R.id.tv_item_num)
        val tvName = holder.getView<TextView>(R.id.tv_item_name)
        val tvAutor = holder.getView<TextView>(R.id.tv_item_autor)
        tvNum.text = (position + 1).toString()
        tvName.text = songItemData.name
        tvAutor.text = songItemData.autor
    }
}
