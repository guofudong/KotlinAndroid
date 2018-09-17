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
 * @descriptio：
 */
class CrosstalkServiceImpl @Inject constructor(): CrosstalkService {

    private val PAGE_COUNT = 12

    //https://www.toutiao.com/search_content/?offset=&format=json&keyword=%E7%9B%B8%E5%A3%B0%E6%BC%94%E4%B9%89&autoload=true&count=20&cur_tab=2&from=video

    override fun getVideoList(page: Int, callback: CrosstalkService.IGetVideoListCallback){

        val url = "https://www.toutiao.com/search_content/?offset=${(page - 1) * PAGE_COUNT}&format=json&keyword=%E7%9B%B8%E5%A3%B0%E6%BC%94%E4%B9%89&autoload=true&count=$PAGE_COUNT&cur_tab=2&from=video"
        OkGo.get<String>(url)
                .tag(this)
                .execute(object :StringCallback(){
                    override fun onSuccess(response: Response<String>) {
                        val json = CodeUtils.unicodeToString(response.body().toString())
                        Logger.e("相声视频数据：$json")
                        val videoData = Gson().fromJson(json,CrosstalkVideoData::class.java)
                        //获取Cookie
                        OkGo.get<String>("http://toutiao.iiilab.com")
                                .execute(object :StringCallback(){
                                    override fun onSuccess(response: Response<String>) {
                                        val  headers = response.headers()
                                        var cookie = headers.get("Set-Cookie").toString().split(";")[0].split("=")[1]
                                        Logger.e("cookie = $cookie")
                                        cookie = "iii_Session=f2gstd4ovdl4ait39i4aqco6f6;PHPSESSIID=$cookie"
                                        val datas = ArrayList<Video>()
                                        videoData.data.forEach {
                                            val video = Video(
                                                    it.title,
                                                    it.create_time,
                                                    it.datetime,
                                                    it.video_duration,
                                                    it.comment_count,
                                                    it.middle_image_url,
                                                    it.large_image_url,
                                                    it.source_url,
                                                    it.video_duration_str,
                                                    cookie)
                                            datas.add(video)
                                        }
                                        callback.onVideoList(datas)
                                    }
                                })

                    }
                })

    }


}