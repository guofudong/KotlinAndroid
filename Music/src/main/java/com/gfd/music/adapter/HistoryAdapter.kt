package com.gfd.music.adapter

import android.content.Context
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.music.R

/**
 * @Author : 郭富东
 * @Date ：2018/8/23 - 17:29
 * @Email：878749089@qq.com
 * @descriptio：
 */
class HistoryAdapter(val context: Context):BaseAdapter<String>(context){
    override fun getItemLayoutId(): Int {
        return R.layout.music_item_search_history
    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
    }

}