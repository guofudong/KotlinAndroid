package com.gfd.player.adapter

import android.content.Context
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseMultiAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.player.R
import com.gfd.player.common.Conant
import com.gfd.player.entity.Live

/**
 * @Author : 郭富东
 * @Date ：2018/8/21 - 14:35
 * @Email：878749089@qq.com
 * @description：
 */
class ProgramAdapter(val context: Context) : BaseMultiAdapter<Live>(context) {

    private var currentSelect: Int = 1

    init {
        addItemType(Conant.ITEM_TYPE_TITLE, R.layout.player_item_program_live_title)
        addItemType(Conant.ITEM_TYPE_CONTEXT, R.layout.player_item_program_live)
    }

    override fun onBindItemholder(holder: BaseViewHolder, position: Int) {
        val itemData = mDatas[position]
        if (itemData.getItemType() == Conant.ITEM_TYPE_TITLE) {
            bindTitleItem(holder, itemData)
        } else if (itemData.getItemType() == Conant.ITEM_TYPE_CONTEXT) {
            bindContentItem(holder, itemData, position)
        }
    }

    private fun bindContentItem(holder: BaseViewHolder, liveData: Live, position: Int) {
        holder.apply {
            setText(R.id.tv_live_item_content, liveData.name)
            getView<TextView>(R.id.tv_live_item_content).isSelected = (currentSelect == position)
        }
    }

    private fun bindTitleItem(holder: BaseViewHolder, liveData: Live) {
        holder.setText(R.id.tv_live_item_title, liveData.name)
    }

    fun setSelect(position: Int) {
        currentSelect = position
    }

    fun getCurrentSelect(): Int {
        return currentSelect
    }
}