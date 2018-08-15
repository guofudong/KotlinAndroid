package com.gfd.music.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.utils.ImageLoader
import com.gfd.music.R
import com.gfd.music.api.Api
import com.gfd.music.entity.MvData
import com.gfd.music.entity.MvDetailDto
import com.gfd.music.widgets.MyVideoView
import com.gfd.music.widgets.MyVideoViewHelp
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 17:04
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MvListAdapter(val context: Context) : BaseAdapter<MvData>(context) {

    private var listener: OnTitleClickListener? = null
    override fun getItemLayoutId(): Int {
        return R.layout.item_mv_music
    }

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val tvName = holder.getView<TextView>(R.id.tvNameMv)
        val tvCount = holder.getView<TextView>(R.id.tvCountMv)
        val videoView = holder.getView<MyVideoView>(R.id.videoView)
        val mvData = mDatas[position]
        tvName.text = if (mvData.des.isEmpty()) {
            mvData.name
        } else {
            mvData.des
        }
        if (listener != null) {
            tvName.setOnClickListener {
                listener?.onClick(it)
            }
        }
        tvCount.text = "播放次数:${mvData.playCount / 1000}万"
        ImageLoader.loadUrlImage(context, mvData.pic, videoView.coverImageView)
        OkGo.get<String>(Api.getMVDetail(mvData.id))
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val mvDetail = Gson().fromJson(response.body().toString(), MvDetailDto::class.java)
                        mvData.videoUrl = mvDetail.data.brs.`_$720`
                        videoView.setUp(mvData.videoUrl)
                    }

                })

    }

    fun getItemSize(): Int {
        return mDatas.size
    }

    interface OnTitleClickListener {
        fun onClick(view: View)
    }

    fun setOnTitleClickListener(action: (View) -> Unit) {
        this.listener = object : OnTitleClickListener {
            override fun onClick(view: View) {
                action(view)
            }
        }
    }
}