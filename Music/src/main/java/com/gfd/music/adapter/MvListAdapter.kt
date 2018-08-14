package com.gfd.music.adapter

import android.content.Context
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.utils.ImageLoader
import com.gfd.music.R
import com.gfd.music.entity.MvData
import org.song.videoplayer.DemoQSVideoView

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 17:04
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MvListAdapter(val context: Context) : BaseAdapter<MvData>(context) {

    override fun getItemLayoutId(): Int {
        return R.layout.item_mv_music
    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val tvName = holder.getView<TextView>(R.id.tvNameMv)
        val tvCount = holder.getView<TextView>(R.id.tvCountMv)
        val videoView = holder.getView<DemoQSVideoView>(R.id.videoView)
        val mvData = mDatas[position]
        tvName.text = mvData.name
        tvCount.text = "播放次数:${mvData.playCount / 1000}万"
        ImageLoader.loadUrlImage(context, mvData.pic, videoView.coverImageView)
    }

    fun getItemSize():Int{
        return mDatas.size
    }

}