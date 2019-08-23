package com.gfd.player.service.impl

import com.gfd.common.common.BaseConstant
import com.gfd.common.utils.ToastUtils
import com.gfd.player.entity.VideoItemData
import com.gfd.player.service.PlayService
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.orhanobut.logger.Logger
import org.jsoup.Jsoup
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 14:46
 * @Email：878749089@qq.com
 * @description：
 */
class PlayServiceImpl @Inject constructor() : PlayService {

   companion object{
       private const val ERROT_INFO = "解析异常！"
   }

    override fun getVideoUrl(url: String, callBack: PlayService.GetVideoUrlCallBack) {
        Logger.e("解析地址：${BaseConstant.BASE_URL + url}")
        OkGo.get<String>(BaseConstant.BASE_URL + url)
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        try {
                            val json = response.body().toString()
                            val document = Jsoup.parse(json)
                            val e = document.selectFirst("div[class=plot]")
                            val plotText = e?.text() ?: ""
                            val src = document.selectFirst("iframe#video").attr("src")
                            Logger.e("解析出来的地址：$src")
                            val split = src.split("/jx.php?url=")
                            val videoUrl: String
                            videoUrl = if (split.size == 2) split[1] else ""
                            Logger.e("播放地址：$videoUrl")
                            callBack.videoUrl(videoUrl, plotText)
                        } catch (e: Exception) {
                            ToastUtils.instance.showToast(ERROT_INFO)
                        }
                    }
                })
    }

    override fun getWebVideoUrl(url: String, callBack: PlayService.GetVideoUrlCallBack) {
        val analyzeUrl = "${BaseConstant.BASE_URL + url}"
        Logger.e("解析地址：$analyzeUrl")
        OkGo.get<String>(BaseConstant.BASE_URL + url)
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        try {
                            val json = response.body().toString()
                            val document = Jsoup.parse(json)
                            val selectFirst = document.selectFirst("div[class=plot]")
                            val videoData = ArrayList<VideoItemData>()
                            if (selectFirst == null) {
                                callBack.videoWebData(videoData, "")
                                return
                            }
                            val plotText = selectFirst.text()
                            val lis = document.select("div#playlist1 > ul > li")
                            if (lis != null) {
                                lis.forEach {
                                    val a = it.selectFirst("a")
                                    val count = a.text()//集数
                                    val link = BaseConstant.URL_VIDEO_ANALYZE + a.attr("href")
                                    videoData.add(VideoItemData(count,link))
                                }
                                val jsonData = Gson().toJson(videoData)
                                Logger.e("解析出来的剧集地址：$jsonData")
                            }
                            callBack.videoWebData(videoData, plotText)
                        } catch (e: Exception) {
                            ToastUtils.instance.showToast(ERROT_INFO)
                        }
                    }
                })
    }

}