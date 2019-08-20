package com.gfd.crosstalk.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.utils.ImageLoader
import com.gfd.crosstalk.R
import com.gfd.crosstalk.entity.Video


/**
 * @Author : 郭富东
 * @Date ：2018/9/15 - 11:20
 * @Email：878749089@qq.com
 * @description：
 */
class VideoAdapter(private val context: Context) : BaseAdapter<Video>(context) {

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val data = mData[position]
        holder.apply {
            setText(R.id.tvCommentCount, "${data.comment_count}评论")
            setText(R.id.tvVideoTitle, data.name)
            setText(R.id.tvTime, data.datetime)
            setText(R.id.crosstalk_video_duration, data.video_duration_str)
            setImageUrl(R.id.crosstalk_img_video, data.large_image_url)
        }
    }

    override fun getItemLayoutId(): Int = R.layout.crosstalk_item_video

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