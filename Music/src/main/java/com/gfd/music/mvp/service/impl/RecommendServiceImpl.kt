package com.gfd.music.mvp.service.impl

import com.gfd.music.api.Api
import com.gfd.music.common.Concant
import com.gfd.music.entity.*
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

    override fun getRadioData(callBack: RecommendService.GetRecommendCallBack) {
        OkGo.get<String>(Api.getRadioData())
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        Logger.e("热门电台：$json")
                        val radio1 = Gson().fromJson(json, Radio::class.java)
                        val datas = ArrayList<RadioData>()
                        if (radio1.data != null) {
                            val radioData = radio1.data.djRadios
                            if (radioData.isNotEmpty()) {
                                datas.add(RadioData(Concant.ITEM_TYPE_TITLE, "", "今日优选", "", ""))
                                radioData.forEachWithIndex { index, value ->
                                    if (index <= 3) {
                                        datas.add(RadioData(Concant.ITEM_TYPE_LIST, value.picUrl, value.name, value.dj.nickname, value.subCount.toString(),
                                                value.dj.avatarUrl, id = value.id))
                                    } else {
                                        if (index == 4) {
                                            datas.add(RadioData(Concant.ITEM_TYPE_TITLE, "", "电台推荐", "", ""))
                                        }
                                        if (index == 7) {
                                            datas.add(RadioData(Concant.ITEM_TYPE_TITLE, "", "情感调频", "", ""))
                                        }
                                        datas.add(RadioData(Concant.ITEM_TYPE_IMG, value.picUrl, value.name, value.dj.nickname, value.subCount.toString(), id = value.id))
                                    }
                                }
                            }
                        }
                        OkGo.get<String>(Api.getRadioData2())
                                .tag(this)
                                .execute(object : StringCallback() {
                                    override fun onSuccess(response: Response<String>) {
                                        val json = response.body().toString()
                                        Logger.e("热门电台：$json")
                                        val radio2 = Gson().fromJson(json, Radio::class.java)
                                        if (radio2.data != null) {
                                            val radioData2 = radio2.data.djRadios
                                            if (radioData2.isNotEmpty()) {
                                                datas.add(RadioData(Concant.ITEM_TYPE_TITLE, "", "音乐故事", "", ""))
                                                radioData2.forEachWithIndex { index, value ->
                                                    if (index < 9) {
                                                        if (index == 3) {
                                                            datas.add(RadioData(Concant.ITEM_TYPE_TITLE, "", "二次元", "", ""))
                                                        }
                                                        if (index == 6) {
                                                            datas.add(RadioData(Concant.ITEM_TYPE_TITLE, "", "3D|电子", "", ""))
                                                        }
                                                        datas.add(RadioData(Concant.ITEM_TYPE_IMG, value.picUrl, value.name, value.dj.nickname, value.subCount.toString(), id = value.id))
                                                    }
                                                }
                                            }
                                        }
                                        callBack.onRadioData(datas)
                                    }

                                })
                        callBack.onRadioData(datas)
                    }
                })
    }

}