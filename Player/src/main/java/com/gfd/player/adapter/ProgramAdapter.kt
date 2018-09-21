package com.gfd.player.adapter

import android.content.Context
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseMultiAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.player.R
import com.gfd.player.common.Concant
import com.gfd.player.entity.LiveDataDto

/**
 * @Author : 郭富东
 * @Date ：2018/8/21 - 14:35
 * @Email：878749089@qq.com
 * @descriptio：
 */
class ProgramAdapter(val context: Context) : BaseMultiAdapter<LiveDataDto.LiveData>(context) {

    private  var currentSelect : Int = 1

    init {
        addItemType(Concant.ITEM_TYPE_TITLE, R.layout.item_program_live_title)
        addItemType(Concant.ITEM_TYPE_CONTEXT,R.layout.item_program_live)
    }

    override fun onBindItemholder(holder: BaseViewHolder, position: Int) {
        val itemData = mDatas[position]
        if(itemData.getItemType() == Concant.ITEM_TYPE_TITLE){
            bindTitleItem(holder,itemData)
        }else if(itemData.getItemType() == Concant.ITEM_TYPE_CONTEXT){
            bindContentItem(holder,itemData,position)
        }
    }

    private fun bindContentItem(holder: BaseViewHolder, liveData: LiveDataDto.LiveData, position: Int) {
        val tvName = holder.getView<TextView>(R.id.tv_live_item_content)
        tvName.isSelected =(currentSelect == position)
        tvName.text = liveData.name
    }

    private fun bindTitleItem(holder: BaseViewHolder, liveData: LiveDataDto.LiveData) {
        val tvName = holder.getView<TextView>(R.id.tv_live_item_title)
        tvName.text = liveData.name
    }

    fun setSelect(position: Int){
        currentSelect = position
    }
}