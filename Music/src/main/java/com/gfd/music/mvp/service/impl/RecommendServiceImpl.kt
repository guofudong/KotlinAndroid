package com.gfd.music.mvp.service.impl

import com.gfd.music.api.Api
import com.gfd.music.common.Concant
import com.gfd.music.entity.BannerData
import com.gfd.music.entity.Banner
import com.gfd.music.entity.SongData
import com.gfd.music.entity.Song
import com.gfd.music.mvp.service.RecommendService
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.orhanobut.logger.Logger
import org.jetbrains.anko.collections.forEachWithIndex
import java.util.*
import javax.inject.Inject


/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:03
 * @Email：878749089@qq.com
 * @descriptio：
 */
class RecommendServiceImpl @Inject constructor() : RecommendService {

    override fun getBanner(callBack: RecommendService.GetRecommendCallBack) {
        OkGo.get<String>(Api.getBanner())
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        Logger.e("推荐轮播图：$json")
                        val banner = Gson().fromJson(json, Banner::class.java)
                        val datas = ArrayList<BannerData>()
                        if (banner.pic != null) {
                            banner.pic.forEach {
                                datas.add(BannerData(it.type, it.mo_type, it.code, it.randpic))
                            }
                        }
                        callBack.onBanner(datas)
                    }
                })
    }

    override fun getSongList(callBack: RecommendService.GetRecommendCallBack) {
        OkGo.get<String>(Api.getRecommend(18))
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        Logger.e("推荐歌曲：$json")
                        val songDatas = Gson().fromJson(json, Song::class.java)
                        val datas = ArrayList<SongData>()
                        if (songDatas.data != null && songDatas.data.size > 0) {
                            datas.add(SongData(Concant.ITEM_TYPE_TITLE, "推荐歌单"))
                            songDatas.data.forEachWithIndex { index, value ->
                                if (index == 6) {
                                    datas.add(SongData(Concant.ITEM_TYPE_TITLE, "最新歌曲"))
                                } else if (index == 12) {
                                    datas.add(SongData(Concant.ITEM_TYPE_TITLE, "主播电台"))
                                }
                                val playCount = if (value.playCount > 99999) {
                                    "${value.playCount / 10000}万"
                                } else {
                                    value.playCount.toString()
                                }
                                datas.add(SongData(Concant.ITEM_TYPE_IMG, "", "", value.title,
                                        value.coverImgUrl, value.coverImgUrl, value.id.toString(), value.title, "",
                                        playCount, ""))
                            }
                        }
                        callBack.onSongList(datas)
                    }
                })
    }

}