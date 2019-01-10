package com.gfd.home.service.impl

import android.text.TextUtils
import com.gfd.common.common.BaseConstant
import com.gfd.home.common.Concant
import com.gfd.home.entity.BinnerData
import com.gfd.home.entity.VideoData
import com.gfd.home.entity.VideoItemData
import com.gfd.home.entity.VideoListData
import com.gfd.home.service.VideoService
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
 * @Date ：2018/8/3 - 11:05
 * @Email：878749089@qq.com
 * @descriptio：
 */
class VideoServiceImpl @Inject constructor() : VideoService {
    override fun getVideoList(callBack: VideoService.GetVideoCallBack) {
        val json = AppPrefsUtils.getString(Concant.KEY_JSON)
        if (!TextUtils.isEmpty(json)) {
            callBack.onVideoDataSuccess(Gson().fromJson(json, VideoListData::class.java))
        } else {
            OkGo.get<String>(BaseConstant.BASE_URL)
                    .tag(this)
                    .execute(object : StringCallback() {
                        override fun onSuccess(response: Response<String>) {
                            val json = response.body().toString()
                            val document = Jsoup.parse(json)
                            //轮播图
                            val e1 = document.select("div.hy-video-slide")
                            var binners: MutableList<BinnerData> = ArrayList()
                            for (element in e1) {
                                val subE = element.selectFirst("a[href]")
                                val title = subE.attr("title")
                                var link = subE.attr("href")
                                val style = subE.attr("style")
                                val img = style.substring(style.indexOf("url") + 4, style.indexOf(")"))
                                link = if (link[0] == '/') link else "/$link"
                                Logger.e("轮播图数据：link = $link")
                                binners.add(BinnerData(title, img, link))
                            }
                            //取出最后两个
                            if(binners.size >= 3){
                                binners.removeAt(binners.size - 1)
                                binners.removeAt(binners.size - 1)
                                binners.removeAt(binners.size - 1)
                            }
                            //影视列表中的分类 新片，电视剧，综艺等
                            var types: MutableList<String> = ArrayList()
                            for (typeElement in document.select("h3[class=margin-0]")) {
                                types.add(typeElement.text())
                            }

                            var videoList: MutableList<VideoItemData> = ArrayList()
                            //新片抢先看
                            val newVideoElement = document.selectFirst("div[class=hy-video-list cleafix]")
                                    .select("div[class=item] > div")

                            val newItem = VideoItemData(Concant.CATEGORY_NEW)
                            newItem.type = Concant.ITEM_TYPE_TITLE
                            newItem.title = types[0]
                            videoList.add(newItem)
                            for (element in newVideoElement) {
                                val subE = element.selectFirst("a[href]")
                                val tag = element.selectFirst("span[class=score]").text()
                                val name = subE.attr("title")
                                val href = subE.attr("href")
                                val link = href.substring(1, href.length)
                                val img = subE.attr("src")
                                videoList.add(VideoItemData(tag, Concant.ITEM_TYPE_IMG, name, img, link, types[0]))
                            }

                            val titleTypes = arrayOf(Concant.CATEGORY_MOVIE, Concant.CATEGORY_DINASHI, Concant.CATEGORY_ZONGYI, 0)
                            //其他分类
                            var index = 1
                            for (element in document.select("div[class=hy-layout clearfix]")) {
                                var itemDatas: MutableList<VideoData> = ArrayList()
                                for (element in element.select("div[class=clearfix] > div")) {
                                    val subE = element.selectFirst("a[href]")
                                    val tagElement = element.selectFirst("span[class=score]")
                                    val tag = if (tagElement == null) "" else tagElement.text()
                                    val title = subE.attr("title")
                                    val href = subE.attr("href")
                                    val link = "/$href"
                                    val img = subE.attr("src")
                                    itemDatas.add(VideoData(img, title, tag, link))
                                }
                                if (itemDatas.size > 3) {
                                    //添加标题
                                    val itemTitle = VideoItemData(titleTypes[index - 1])
                                    itemTitle.type = Concant.ITEM_TYPE_TITLE
                                    itemTitle.title = types[index++]
                                    videoList.add(itemTitle)
                                    //添加内容
                                    itemDatas.forEach {
                                        videoList.add(VideoItemData(it.type, Concant.ITEM_TYPE_IMG, it.name, it.videoIcon,
                                                it.playUrl))
                                    }
                                    videoList.removeAt(videoList.size - 1)
                                }
                            }
                            val data = VideoListData(binners, videoList)
                            val jsonData = Gson().toJson(data)
                            AppPrefsUtils.putString(Concant.KEY_JSON, jsonData)
                            Logger.e(jsonData)
                            callBack.onVideoDataSuccess(data)
                        }
                    })
        }

    }

}
