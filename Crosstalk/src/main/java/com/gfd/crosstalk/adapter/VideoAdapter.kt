package com.gfd.crosstalk.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.gfd.common.common.BaseConstant
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.utils.ImageLoader
import com.gfd.common.utils.JSUtils
import com.gfd.common.utils.JSUtils.excuteJs
import com.gfd.crosstalk.R
import com.gfd.crosstalk.entity.Video
import com.gfd.crosstalk.entity.VideoPlayerData
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.orhanobut.logger.Logger
import com.tencent.smtt.utils.r
import com.tencent.smtt.utils.s
import com.xiao.nicevideoplayer.Clarity
import com.xiao.nicevideoplayer.NiceVideoPlayer
import com.xiao.nicevideoplayer.TxVideoPlayerController
import java.io.FileReader
import java.io.IOException
import java.io.InputStream
import javax.script.Invocable
import javax.script.ScriptEngineManager
import javax.script.ScriptException


/**
 * @Author : 郭富东
 * @Date ：2018/9/15 - 11:20
 * @Email：878749089@qq.com
 * @descriptio：
 */
class VideoAdapter(private val context: Context) : BaseAdapter<Video>(context) {

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val tvCommentCount = holder.getView<TextView>(R.id.tvCommentCount)
        val tvVideoTitle = holder.getView<TextView>(R.id.tvVideoTitle)
        val tvTime = holder.getView<TextView>(R.id.tvTime)
        val videoDuration = holder.getView<TextView>(R.id.crosstalk_video_duration)
        val videoImg = holder.getView<ImageView>(R.id.crosstalk_img_video)
        val data = mDatas[position]
        tvCommentCount.text = "${data.comment_count}评论"
        tvVideoTitle.text = data.name
        tvTime.text = data.datetime
        videoDuration.text = data.video_duration_str
        ImageLoader.loadUrlImage(context, data.large_image_url, videoImg)
        val link = BaseConstant.CROSSTRALK_BASE_URL + data.source_url
    }

    override fun getItemLayoutId(): Int {
        return R.layout.crosstalk_item_video
    }


    //原来的解析地址已不能使用，所有不在调用，改用webview实现
    /*  @Throws(Exception::class)
      private fun excuteJs(link: String): Map<String, String> {
          val stream = context.assets.open("test.js")
          stream.buffered().reader().use {
              val jsStr = it.readText()
              val inv = JSUtils.getJsInvocable(jsStr)
              val hashMap = HashMap<String, String>()
              if (inv != null) {
                  val r = inv.invokeFunction("getR")
                  val param = link + "@" + r.toString()
                  val s = inv.invokeFunction("getS", param)
                  hashMap["r"] = r.toString()
                  hashMap["s"] = s.toString()
              }
              return hashMap
          }
      }*/

}