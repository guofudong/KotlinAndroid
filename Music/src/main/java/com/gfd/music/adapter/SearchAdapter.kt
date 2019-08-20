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
 * @description：音乐搜索结构列表适配器
 */
class SearchAdapter(val context: Context) : BaseAdapter<SearchData>(context) {
    override fun getItemLayoutId(): Int = R.layout.musis_item_search_result

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val searchData = mData[position]
        holder.apply {
            setText(R.id.tvSongName, searchData.name)
            setText(R.id.tvSongArtist, searchData.singer)
            setImageUrl(R.id.ivItemSearch, searchData.pic)
        }
    }

}