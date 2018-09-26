package com.gfd.music.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.utils.ImageLoader
import com.gfd.music.R
import com.gfd.music.entity.SearchData

/**
 * @Author : 郭富东
 * @Date ：2018/8/23 - 17:29
 * @Email：878749089@qq.com
 * @descriptio：
 */
class SearchAdapter(val context: Context):BaseAdapter<SearchData>(context){
    override fun getItemLayoutId(): Int {
        return R.layout.musis_item_search_result
    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val searchData = mDatas[position]
        val tvSongName = holder.getView<TextView>(R.id.tvSongName)
        val tvSongArtist = holder.getView<TextView>(R.id.tvSongArtist)
        val ivSongPic = holder.getView<ImageView>(R.id.ivItemSearch)
        ImageLoader.loadUrlImage(context,searchData.pic,ivSongPic)
        tvSongArtist.text = searchData.artist
        tvSongName.text = searchData.name
    }

}