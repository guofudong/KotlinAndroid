package com.gfd.player.service.impl

import com.gfd.common.common.BaseConstant
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
 * @descriptio：
 */
class PlayServiceImpl @Inject constructor() : PlayService {

    override fun getVideoUrl(url: String, callBack: PlayService.GetVideoUrlCallBack) {
        Logger.e("解析地址：${BaseConstant.BASE_URL + url}")
        OkGo.get<String>(BaseConstant.BASE_URL + url)
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        val document = Jsoup.parse(json)
                        val plotText = document.selectFirst("div[class=plot]").text()
                        val src = document.selectFirst("iframe#video").attr("src")
                        Logger.e("解析出来的地址：$src")
                        val split = src.split("url=")
                        val videoUrl: String
                        if (split.size < 3) {
                            videoUrl = src
                        } else {
                            videoUrl = split[2]
                        }
                        Logger.e("播放地址：$videoUrl")
                        callBack.videoUrl(videoUrl, plotText)
                    }
                })
    }

    override fun getWebVideoUrl(url: String, callBack: PlayService.GetVideoUrlCallBack) {
        Logger.e("解析地址：${BaseConstant.BASE_URL + url}")
        OkGo.get<String>(BaseConstant.BASE_URL + url)
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        val document = Jsoup.parse(json)
                        val selectFirst = document.selectFirst("div[class=plot]")
                        val videoDatas = ArrayList<VideoItemData>()
                        if (selectFirst == null) {
                            callBack.videoWebData(videoDatas, "")
                            return
                        }
                        val plotText = selectFirst.text()
                        val lis = document.select("div#playlist1 > ul > li")
                        if (lis != null) {
                            lis.forEach {
                                val a = it.selectFirst("a")
                                val count = a.text()//集数
                                var link = BaseConstant.URL_ANALYZE + a.attr("href")
                                videoDatas.add(VideoItemData(count, link))
                            }
                            val jsonData = Gson().toJson(videoDatas)
                            Logger.e("解析出来的剧集地址：$jsonData")
                        }
                        callBack.videoWebData(videoDatas, plotText)
                    }
                })
    }

}