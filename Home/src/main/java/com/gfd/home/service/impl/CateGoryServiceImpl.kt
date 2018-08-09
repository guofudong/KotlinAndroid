package com.gfd.home.service.impl

import com.gfd.common.utils.encode
import com.gfd.home.entity.VideoItemData
import com.gfd.home.service.CategoryService
import com.google.gson.Gson
import com.kotlin.base.utils.AppPrefsUtils
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.orhanobut.logger.Logger
import org.jsoup.Jsoup
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 15:54
 * @Email：878749089@qq.com
 * @descriptio：
 */
class CateGoryServiceImpl @Inject constructor() : CategoryService {

    override fun getCategoryVideos(url: String, page: Int, state: Int, callBack: CategoryService.GetCategoryVideoCallBack) {
        Logger.e("解析地址：${url + page}")
        OkGo.get<String>(url + page)
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        val document = Jsoup.parse(json)
                        val videoDatas = ArrayList<VideoItemData>()
                        document.select("div[class=item] > ul > div").forEach {
                            val a = it.selectFirst("a")
                            var videoName = a.attr("title")
                            var imgUrl = a.attr("src")
                            var link = a.attr("href")
                            if (!link.contains("vod")) {
                                link = link.substring(1, link.length)
                            } else {
                                link = "/$link"
                            }
                            videoDatas.add(VideoItemData(videoName = videoName, videoImg = imgUrl, videoLink = link))
                        }
                        var jsonData = Gson().toJson(videoDatas)
                        AppPrefsUtils.putString(encode(url), jsonData)
                        Logger.e("解析结果集：$jsonData")
                        callBack.onCategoryVideos(videoDatas, state)
                    }
                })
    }

}