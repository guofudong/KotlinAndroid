package com.gfd.player.service

import com.gfd.common.common.BaseConstant
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
        OkGo.get<String>(BaseConstant.BASE_URL + url)
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        Logger.e(BaseConstant.BASE_URL + url)
                        val json = response.body().toString()
                        val document = Jsoup.parse(json)
                        val plotText = document.selectFirst("div[class=plot]").text()
                        val src = document.selectFirst("iframe#video").attr("src")
                        val videoUrl = src.split("url=")[2]
                        Logger.e("播放地址：$videoUrl")
                        callBack.videoUrl(videoUrl,plotText)
                    }
                })
    }

}