package com.gfd.home.adapter

import android.content.Context
import android.view.View
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.home.R
import com.gfd.home.entity.SearchItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 16:49
 * @Email：878749089@qq.com
 * @description：视频搜素结果列表适配器
 */
class SearchDataAdapter(var context: Context) : BaseAdapter<SearchItemData>(context) {
    override fun getItemLayoutId(): Int = R.layout.home_item_search

    private var playClickListener: ((position: Int) -> Unit)? = null

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        //设置数据
        val data = mData[position]
        holder.apply {
            setText(R.id.tv_item_videoName, data.name)
            setText(R.id.tv_item_videoActor, data.actor)
            setText(R.id.tv_item_videoPlot, data.plot)
            setText(R.id.tv_item_videoType, data.type)
            setImageUrl(R.id.img_item_video, data.imgUrl)
            //设置播放按钮点击监听
            getView<View>(R.id.btn_item_play).setOnClickListener {
                playClickListener?.invoke(position)
            }
        }
    }

    /**
     * 设置播放按钮监听
     * @param playListener Function1<[@kotlin.ParameterName] Int, Unit>：回调方法
     */
    fun setOnPlayClickListener(playListener: (position: Int) -> Unit) {
        this.playClickListener = playListener
    }

}