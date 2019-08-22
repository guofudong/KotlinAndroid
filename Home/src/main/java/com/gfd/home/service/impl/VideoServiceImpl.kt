package com.gfd.home.service.impl

import android.text.TextUtils
import com.gfd.common.common.BaseConstant
import com.gfd.home.common.Constant
import com.gfd.home.entity.BannerData
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
 * @description：解析数据源：http://www.vip-free.com
 */
class VideoServiceImpl @Inject constructor() : VideoService {
    override fun getVideoList(callBack: VideoService.GetVideoCallBack) {
        val json = AppPrefsUtils.getString(Constant.KEY_JSON)
        if (!TextUtils.isEmpty(json)) {
            callBack.onVideoDataSuccess(Gson().fromJson(json, VideoListData::class.java))
        } else {
            OkGo.get<String>(BaseConstant.BASE_URL)
                    .tag(this)
                    .execute(object : StringCallback() {
                        override fun onSuccess(response: Response<String>) {
                            val jsonStr = response.body().toString()
                            Logger.e("首页数据：$jsonStr")
                            val document = Jsoup.parse(jsonStr)
                            //轮播图
                            val e1 = document.select("div.hy-video-slide")
                            val banners: MutableList<BannerData> = ArrayList()
                            for (element in e1) {
                                val subE = element.selectFirst("a[href]")
                                val title = subE.attr("title")
                                Logger.e("轮播图数据：title = $title")
                                if (!title.contains("伦理") && !title.contains("老司机")) {
                                    var link = subE.attr("href")
                                    val style = subE.attr("style")
                                    val img = style.substring(style.indexOf("url") + 4, style.indexOf(")"))
                                    link = if (link[0] == '/') link else "/$link"
                                    Logger.e("轮播图数据：link = $link")
                                    banners.add(BannerData(title, img, link))
                                }
                            }
                            //影视列表中的分类 新片，电视剧，综艺等
                            val types: MutableList<String> = ArrayList()
                            for (typeElement in document.select("h3[class=margin-0]")) {
                                val tag = typeElement.text()
                                types.add(tag)
                                Logger.e("分类标签：$tag")
                            }

                            var videoList: MutableList<VideoItemData> = ArrayList()
                            //新片抢先看数据源已不存在
                           /* //新片抢先看
                            val newVideoElement = document.selectFirst("div[class=hy-video-list cleafix]")
                                    .select("div[class=item] > div")

                            val newItem = VideoItemData(Constant.CATEGORY_NEW)
                            newItem.type = Constant.ITEM_TYPE_TITLE
                            newItem.title = types[0]
                            videoList.add(newItem)
                            for (element in newVideoElement) {
                                val subE = element.selectFirst("a[href]")
                                val tag = element.selectFirst("span[class=score]").text()
                                val name = subE.attr("title")
                                val href = subE.attr("href")
                                val link = href.substring(1, href.length)
                                val img = subE.attr("src")
                                videoList.add(VideoItemData(tag, Constant.ITEM_TYPE_IMG, name, img, link, types[0]))
                            }*/

                            val titleTypes = arrayOf(Constant.CATEGORY_MOVIE, Constant.CATEGORY_DANISH, Constant.CATEGORY_ZINGY, 0)
                            //其他分类
                            var index = 0
                            for (elementParent in document.select("div[class=hy-layout clearfix]")) {
                                val itemData: MutableList<VideoData> = ArrayList()
                                for (element in elementParent.select("div[class=clearfix] > div")) {
                                    val subE = element.selectFirst("a[href]")
                                    val tagElement = element.selectFirst("span[class=score]")
                                    val tag = if (tagElement == null) "" else tagElement.text()
                                    val title = subE.attr("title")
                                    val href = subE.attr("href")
                                    val link = "/$href"
                                    val img = subE.attr("src")
                                    itemData.add(VideoData(img, title, tag, link))
                                }
                                if (itemData.size >= 3) {
                                    //添加标题
                                    val itemTitle = VideoItemData(titleTypes[index])
                                    itemTitle.type = Constant.ITEM_TYPE_TITLE
                                    itemTitle.title = types[++index]
                                    videoList.add(itemTitle)
                                    //添加内容
                                    itemData.forEach {
                                        videoList.add(VideoItemData(it.type, Constant.ITEM_TYPE_IMG, it.name, it.videoIcon,
                                                it.playUrl))
                                    }
                                    videoList.removeAt(videoList.size - 1)
                                }
                            }
                            val data = VideoListData(banners, videoList)
                            val jsonData = Gson().toJson(data)
                            AppPrefsUtils.putString(Constant.KEY_JSON, jsonData)
                            Logger.e(jsonData)
                            callBack.onVideoDataSuccess(data)
                        }
                    })
        }

    }

}
