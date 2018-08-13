package com.gfd.music.mvp.service.impl

import com.gfd.music.api.Api
import com.gfd.music.common.Concant
import com.gfd.music.entity.BannerData
import com.gfd.music.entity.BannerDto
import com.gfd.music.entity.SongData
import com.gfd.music.entity.SongDto
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
                        val banner = Gson().fromJson(json, BannerDto::class.java)
                        val datas = ArrayList<BannerData>()
                        banner.pic.forEach {
                            datas.add(BannerData(it.type, it.mo_type, it.code, it.randpic))
                        }
                        callBack.onBanner(datas)
                    }
                })
    }

    override fun getSongList(callBack: RecommendService.GetRecommendCallBack) {
        OkGo.get<String>(Api.getRecommend(30))
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        Logger.e("推荐歌曲：$json")
                        val songDatas = Gson().fromJson(json, SongDto::class.java)
                        val datas = ArrayList<SongData>()
                        val song_list = songDatas.content[0].song_list
                        for (i in 0..11) {
                            song_list.removeAt(0)
                        }
                        song_list.forEachWithIndex { index, value ->
                            if (index == 6) {
                                datas.add(SongData(Concant.ITEM_TYPE_TITLE, "最新歌曲"))
                            } else if (index == 12) {
                                datas.add(SongData(Concant.ITEM_TYPE_TITLE, "主播电台"))
                            }
                            datas.add(SongData(Concant.ITEM_TYPE_IMG, "", "", value.recommend_reason,
                                    value.pic_huge, value.pic_premium, value.song_id, value.title, value.url,
                                    value.file_duration,value.artist_id))
                        }
                        datas.reverse()
                        datas.add(SongData(Concant.ITEM_TYPE_TITLE, "推荐歌单"))
                        callBack.onSongList(datas)
                    }
                })
    }

}