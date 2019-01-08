package com.gfd.home.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.utils.ImageLoader
import com.gfd.home.R
import com.gfd.home.entity.SearchItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 16:49
 * @Email：878749089@qq.com
 * @descriptio：
 */
class SearchDataAdapter(var context: Context) : BaseAdapter<SearchItemData>(context) {
    override fun getItemLayoutId(): Int {
        return R.layout.home_item_search

    }

    private  var playClickListener: OnPlayClickListener ? = null

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val data = mDatas[position]
        var tvVideoName = holder.getView<TextView>(R.id.tv_item_videoName)
        var tvVideoActor = holder.getView<TextView>(R.id.tv_item_videoActor)
        var tvVideoPlot = holder.getView<TextView>(R.id.tv_item_videoPlot)
        var tvVideoType = holder.getView<TextView>(R.id.tv_item_videoType)
        var imgVideo = holder.getView<ImageView>(R.id.img_item_video)
        tvVideoActor.text = data.actor
        tvVideoName.text = data.name
        tvVideoPlot.text = data.plot
        tvVideoType.text = data.type
        ImageLoader.loadUrlImage(context, data.imgUrl, imgVideo)
        holder.getView<View>(R.id.btn_item_play).setOnClickListener {
            if(playClickListener!= null){
                playClickListener!!.onPlayClick(position)
            }
        }
    }

    interface OnPlayClickListener{
        fun onPlayClick(positon:Int)
    }

    fun setOnPlayClickListener(playListener:OnPlayClickListener){
        this.playClickListener = playListener
    }

}