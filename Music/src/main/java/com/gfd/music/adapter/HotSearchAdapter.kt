package com.gfd.music.adapter

import android.content.Context
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.music.R
import com.google.android.flexbox.FlexboxLayoutManager

/**
 * @Author : 郭富东
 * @Date ：2018/8/23 - 17:24
 * @Email：878749089@qq.com
 * @description：
 */
class HotSearchAdapter(context: Context) : BaseAdapter<String>(context) {

    override fun getItemLayoutId(): Int = R.layout.music_item_search_hot

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        holder.setText(R.id.tv_item_history, mData[position])
        val lp = holder.getView<TextView>(R.id.tv_item_history).layoutParams
        if (lp is FlexboxLayoutManager.LayoutParams) {
            lp.flexGrow = 1.0f
        }
    }

}