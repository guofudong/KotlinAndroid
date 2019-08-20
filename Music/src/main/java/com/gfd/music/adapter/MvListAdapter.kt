package com.gfd.music.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.music.R
import com.gfd.music.entity.MvData
import com.gfd.music.ui.activity.MvDetailActivity
import com.orhanobut.logger.Logger

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 17:04
 * @Email：878749089@qq.com
 * @description：MV列表适配器
 */

class MvListAdapter(val context: Context) : BaseAdapter<MvData>(context) {

    private var listener: ((View, String) -> Unit)? = null
    override fun getItemLayoutId(): Int = R.layout.music_item_mv_music

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val mvData = mData[position]
        holder.apply {
            setImageUrl(R.id.videoPic, mvData.pic)
            setText(R.id.tvCountMv, "播放次数:${mvData.playCount / 1000}万")
            setText(R.id.tvNameMv, if (mvData.des.isEmpty()) mvData.name else mvData.des)
            getView<View>(R.id.imgPlayer).setOnClickListener {
                //播放视频按钮
                toPlayActivity(mvData)
            }
            getView<View>(R.id.tvNameMv).setOnClickListener {
                listener?.invoke(it, "")
            }
        }
    }

    /**
     * 跳转到视频播放页面
     * @param mvData MvData
     */
    private fun toPlayActivity(mvData: MvData) {
        val videoUrl = mvData.videoUrl
        Logger.e("视频播放地址:$videoUrl")
        val intent = Intent(context, MvDetailActivity::class.java)
        intent.putExtra("mvUrl", videoUrl)
        intent.putExtra("mvId", mvData.id)
        intent.putExtra("mvName", mvData.name)
        context.startActivity(intent)
    }

    fun getItemSize(): Int = mData.size

    /**
     * 设置name点击监听
     * @param action Function2<View, String, Unit>
     */
    fun setOnTitleClickListener(action: (View, String) -> Unit) {
        this.listener = action
    }
}