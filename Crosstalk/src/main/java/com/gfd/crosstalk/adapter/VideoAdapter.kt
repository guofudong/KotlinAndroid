package com.gfd.crosstalk.adapter

import android.content.Context
import android.view.View
import android.widget.TextView
import com.gfd.common.common.BaseConstant
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.utils.ImageLoader
import com.gfd.common.utils.JSUtils
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
class VideoAdapter(val context: Context) : BaseAdapter<Video>(context) {

    override fun onBindView(holder: BaseViewHolder, position: Int) {
        val tvCommentCount = holder.getView<TextView>(R.id.tvCommentCount)
        val tvVideoTitle = holder.getView<TextView>(R.id.tvVideoTitle)
        val tvTime = holder.getView<TextView>(R.id.tvTime)
        val videoView = holder.getView<NiceVideoPlayer>(R.id.videoView)
        setVideoView(videoView,holder.itemView)
        val data = mDatas[position]
        tvCommentCount.text = "${data.comment_count}评论"
        tvVideoTitle.text = data.name
        tvTime.text = data.datetime
        //设置视频播放器
        val controller = TxVideoPlayerController(context)
        videoView.setController(controller)
        controller.setTitle("")
        controller.setLenght(data.video_duration.toLong())
        ImageLoader.loadUrlImage(context, data.large_image_url, controller.imageView())
        val link = BaseConstant.CROSSTRALK_BASE_URL + data.source_url
        val map = excuteJs(link)
        OkGo.post<String>(BaseConstant.CROSSTRALK_URL_ANALYSIS)
                .headers("Origin", "http://toutiao.iiilab.com")
                .headers("Cookie", "_ga=GA1.2.1595067136.1536918184; _gid=GA1.2.1986337126.1536918184; iii_Session=9md298fdbbead8ugotksgrb2h2; PHPSESSIID=577069153695; _gat=1")
                .headers("Referer", "http://toutiao.iiilab.com/")
                .params("link",link )
                .params("r", map["r"])
                .params("s", map["s"])
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        Logger.e("视频播放地址数据：$json")
                        val playerData = Gson().fromJson(json,VideoPlayerData::class.java)
                        controller.setClarity(getClarites(playerData),0)
                    }
                })

    }

    override fun getItemLayoutId(): Int {
        return R.layout.crosstalk_item_video
    }
    private fun getClarites(playerData: VideoPlayerData): MutableList<Clarity> {
        val datas = ArrayList<Clarity>()
        playerData.data.video.link.forEachIndexed { index, linkBean ->
            datas.add(Clarity("高清$index", "480P", linkBean.url))
        }
        return datas
    }


    private fun setVideoView(videoView: NiceVideoPlayer, itemView: View) {
        val params = videoView.layoutParams
        params.width = itemView.resources.displayMetrics.widthPixels // 宽度为屏幕宽度
        params.height = (params.width * 9f / 16f).toInt()    // 高度为宽度的9/16
        videoView.layoutParams = params
    }

    @Throws(Exception::class)
    private fun excuteJs(link: String): Map<String, String> {
        val stream= context.assets.open("test.js")
        stream.buffered().reader().use {
            val jsStr = it.readText()
            val inv = JSUtils.getJsInvocable(jsStr)
            val r = inv.invokeFunction("getR")
            val param = link + "@" + r.toString()
            val s = inv.invokeFunction("getS", param)
            val hashMap = HashMap<String, String>()
            hashMap["r"] = r.toString()
            hashMap["s"] = s.toString()
            return hashMap
        }
    }

}