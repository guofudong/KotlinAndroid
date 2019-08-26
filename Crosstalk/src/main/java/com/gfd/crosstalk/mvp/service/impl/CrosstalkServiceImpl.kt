package com.gfd.crosstalk.mvp.service.impl

import com.gfd.common.utils.CodeUtils
import com.gfd.crosstalk.entity.CrosstalkVideoData
import com.gfd.crosstalk.entity.Video
import com.gfd.crosstalk.mvp.service.CrosstalkService
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.orhanobut.logger.Logger
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/9/15 - 11:34
 * @Email：878749089@qq.com
 * @description：
 */
class CrosstalkServiceImpl @Inject constructor() : CrosstalkService {

    companion object {
        private const val PAGE_COUNT = 20
    }

    override fun getVideoList(page: Int, callback: CrosstalkService.IGetVideoListCallback) {
        val url = "https://www.toutiao.com/api/search/content/?aid=24&app_name=web_search&offset=${(page - 1) * PAGE_COUNT}&format=json&keyword=%E7%9B%B8%E5%A3%B0&autoload=true&count=$PAGE_COUNT&&en_qc=1&cur_tab=2&from=video&pd=video&timestamp=1554889280415"
        OkGo.get<String>("https://www.fastmock.site/mock/6f83f4af5228f968862b5958800c653f/kotlin/cookit")
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val cookie = response.body().toString()
                        Logger.e("相声视频cookie：$cookie")
                        getData(url, cookie, callback)
                    }
                })
    }

    private fun getData(url: String, cookie: String, callback: CrosstalkService.IGetVideoListCallback) {
        OkGo.get<String>(url)
                .tag(this)
                .headers("cookie", cookie)
                .execute(object : StringCallback() {
                    val data = ArrayList<Video>()
                    override fun onSuccess(response: Response<String>) {
                        val json = CodeUtils.unicodeToString(response.body().toString())
                        Logger.e("相声视频数据：$json")
                        val videoData = Gson().fromJson(json, CrosstalkVideoData::class.java)
                        if (videoData.data == null) return
                        if (videoData.data.isNotEmpty()) {
                            videoData.data.forEach {
                                if (it.source_url != null) {
                                    val video = Video(
                                            it.title ?: "",
                                            it.create_time ?: "",
                                            it.datetime ?: "",
                                            it.video_duration,
                                            it.comment_count,
                                            it.middle_image_url ?: "",
                                            it.large_image_url ?: "",
                                            it.source_url,
                                            it.video_duration_str ?: "00:00",
                                            "")
                                    data.add(video)
                                }
                            }
                        }
                    }

                    override fun onFinish() {
                        callback.onVideoList(data)
                    }
                })
    }

}