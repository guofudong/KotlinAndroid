package com.gfd.player.adapter

import android.content.Context
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.utils.ImageLoader
import com.gfd.player.R
import com.gfd.player.entity.LiveDataDto
import de.hdodenhof.circleimageview.CircleImageView

/**
 * @Author : 郭富东
 * @Date ：2018/8/21 - 14:35
 * @Email：878749089@qq.com
 * @descriptio：
 */
class ProgramAdapter(val context: Context) : BaseAdapter<LiveDataDto.LiveData>(context) {

    private var oldSelectPosition = -1
    private var currentSelectPosition = 0

    override fun getItemLayoutId(): Int {
        return R.layout.item_program_live

    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        if(currentSelectPosition == position){
            holder.itemView.setBackgroundResource(R.drawable.sp_item_select)
        }else{
            holder.itemView.setBackgroundResource(R.drawable.sp_item_normal)
        }
        val liveData = mDatas[position]
        val tvName = holder.getView<TextView>(R.id.player_tv_item_program)
        val ivPic = holder.getView<CircleImageView>(R.id.player_iv_item_program)
        tvName.text = liveData.name
        ImageLoader.loadUrlImage(context, liveData.icon, ivPic)
    }

    fun refreshItem(position: Int) {
        if (currentSelectPosition != -1) {
            oldSelectPosition = currentSelectPosition
        }
        currentSelectPosition = position
        if (oldSelectPosition != -1) {
            notifyItemChanged(oldSelectPosition)
        }
        notifyItemChanged(currentSelectPosition)
    }

}