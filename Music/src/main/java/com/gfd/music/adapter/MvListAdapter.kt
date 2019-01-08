package com.gfd.music.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.utils.ImageLoader
import com.gfd.music.R
import com.gfd.music.entity.MvData
import com.gfd.provider.router.RouterPath
import com.orhanobut.logger.Logger

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 17:04
 * @Email：878749089@qq.com
 * @descriptio：
 */

class MvListAdapter(val context: Context) : BaseAdapter<MvData>(context) {

    private var listener: OnTitleClickListener? = null
    override fun getItemLayoutId(): Int {
        return R.layout.music_item_mv_music
    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val tvName = holder.getView<TextView>(R.id.tvNameMv)
        val tvCount = holder.getView<TextView>(R.id.tvCountMv)
        val videoPic = holder.getView<ImageView>(R.id.videoPic)
        val imgPlayer = holder.getView<ImageView>(R.id.imgPlayer)
        val mvData = mDatas[position]
        val des: String = if (mvData.des.isEmpty()) {
            mvData.name
        } else {
            mvData.des
        }
        ImageLoader.loadUrlImage(context, mvData.pic, videoPic)
        tvName.text = des
        tvCount.text = "播放次数:${mvData.playCount / 1000}万"
        if (listener != null) {
            tvName.setOnClickListener {
                listener?.onClick(it, "")
            }
        }
        imgPlayer.setOnClickListener {
            //播放视频按钮
            val videoUrl = mvData.videoUrl
            Logger.e("视频播放地址:$videoUrl")
            ARouter.getInstance().build(RouterPath.Player.PATH_PLAYER_MV)
                    .withString(RouterPath.Player.KEY_PLAYER, videoUrl)
                    .withString(RouterPath.Player.KEY_IMAGE, mvData.pic)
                    .withString(RouterPath.Player.KEY_NAME, mvData.des)
                    .navigation()
        }
    }

    fun getItemSize(): Int {
        return mDatas.size
    }

    interface OnTitleClickListener {
        fun onClick(view: View, data: String)
    }

    fun setOnTitleClickListener(action: (View, String) -> Unit) {
        this.listener = object : OnTitleClickListener {
            override fun onClick(view: View, data: String) {
                action(view, data)
            }
        }
    }
}