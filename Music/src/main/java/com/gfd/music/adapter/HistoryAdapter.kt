package com.gfd.music.adapter

import android.content.Context
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.music.R

/**
 * @Author : 郭富东
 * @Date ：2018/8/23 - 17:29
 * @Email：878749089@qq.com
 * @description：
 */
class HistoryAdapter(val context: Context) : BaseAdapter<String>(context) {
    override fun getItemLayoutId(): Int = R.layout.musis_item_history_search

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        holder.setText(R.id.music_tv_search_history, mData[position])
    }

}