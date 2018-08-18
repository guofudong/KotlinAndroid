package com.gfd.music.adapter

import android.content.Context
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.music.R
import com.google.android.flexbox.FlexboxLayoutManager

/**
 * @Author : 郭富东
 * @Date ：2018/8/18 - 11:01
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MvTagAdapter(val context: Context):BaseAdapter<String>(context){
    override fun getItemLayoutId(): Int {
        return R.layout.item_mvdetail_tag

    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val tvTag = holder.getView<TextView>(R.id.tv_item_tag)
        tvTag.text = mDatas[position]
        val lp = tvTag.layoutParams
        if(lp is FlexboxLayoutManager.LayoutParams){
            lp.flexGrow = 1.0f
        }
    }

}