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
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.xiao.nicevideoplayer.Clarity
import com.xiao.nicevideoplayer.NiceVideoPlayer
import com.xiao.nicevideoplayer.TxVideoPlayerController

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
        val videoView = holder.getView<NiceVideoPlayer>(R.id.videoView)
        setVideoView(videoView, holder.itemView)
        val controller = TxVideoPlayerController(context)
        videoView.setController(controller)
        val mvData = mDatas[position]
        val des: String = if (mvData.des.isEmpty()) {
            mvData.name
        } else {
            mvData.des
        }
        controller.setTitle("")
        ImageLoader.loadUrlImage(context, mvData.pic, controller.imageView())
        tvName.text = des
        tvCount.text = "播放次数:${mvData.playCount / 1000}万"
        OkGo.get<String>(Api.getMVDetail(mvData.id))
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        val mvData = Gson().fromJson(json, MvDetailDto::class.java)
                        controller.setLenght(mvData.data.duration)
                        controller.setClarity(getClarites(mvData), 2)
                        if (listener != null) {
                            tvName.setOnClickListener {
                                listener?.onClick(it,json)
                            }
                        }
                    }
                })

    }

    private fun getClarites(mvData: MvDetailDto): MutableList<Clarity> {
        val datas = ArrayList<Clarity>()
        datas.add(Clarity("标清", "270P", mvData.data.brs.`_$240`))
        datas.add(Clarity("高清", "480P", mvData.data.brs.`_$480`))
        datas.add(Clarity("超清", "720P", mvData.data.brs.`_$720`))
        datas.add(Clarity("蓝光", "1080P", mvData.data.brs.`_$1080`))
        return datas

    }

    private fun setVideoView(videoView: NiceVideoPlayer, itemView: View) {
        val params = videoView.layoutParams
        params.width = itemView.resources.displayMetrics.widthPixels // 宽度为屏幕宽度
        params.height = (params.width * 9f / 16f).toInt()    // 高度为宽度的9/16
        videoView.layoutParams = params
    }

    fun getItemSize(): Int {
        return mDatas.size
    }

    interface OnTitleClickListener {
        fun onClick(view: View,data:String)
    }

    fun setOnTitleClickListener(action: (View,String) -> Unit) {
        this.listener = object : OnTitleClickListener {
            override fun onClick(view: View,data:String) {
                action(view,data)
            }
        }
    }
}