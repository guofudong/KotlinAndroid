package com.gfd.player.adapter

import android.content.Context
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.player.R
import com.gfd.player.entity.TimeTableData

/**
 * @Author : 郭富东
 * @Date ：2018/8/21 - 15:44
 * @Email：878749089@qq.com
 * @descriptio：
 */
class TimeTableAdapter(val context: Context):BaseAdapter<TimeTableData>(context){
    override fun getItemLayoutId(): Int {
        return R.layout.player_item_tiems

    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val timeTableData = mDatas[position]
        val tvTime = holder.getView<TextView>(R.id.player_tv_item_times_tiem)
        val tvName = holder.getView<TextView>(R.id.player_tv_item_times_name)
        tvTime.text = timeTableData.time
        tvName.text = timeTableData.name
    }

}