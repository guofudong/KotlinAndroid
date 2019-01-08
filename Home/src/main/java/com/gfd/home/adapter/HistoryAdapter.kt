package com.gfd.home.adapter

import android.content.Context
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.home.R
import com.google.android.flexbox.FlexboxLayoutManager

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 10:03
 * @Email：878749089@qq.com
 * @descriptio：
 */
class HistoryAdapter(context: Context) : BaseAdapter<String>(context) {

    override fun getItemLayoutId(): Int {
        return R.layout.home_item_search_history
    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        var tvHsitory = holder.getView<TextView>(R.id.tv_item_history)
        tvHsitory.text = mDatas[position]
        val lp = tvHsitory.layoutParams
        if(lp is FlexboxLayoutManager.LayoutParams){
            lp.flexGrow = 1.0f
        }
    }

}