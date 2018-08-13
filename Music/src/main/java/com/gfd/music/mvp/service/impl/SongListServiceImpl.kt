package com.gfd.music.mvp.service.impl

import com.gfd.music.api.Api
import com.gfd.music.entity.SongItemData
import com.gfd.music.entity.SongItemDto
import com.gfd.music.entity.SongTitleData
import com.gfd.music.mvp.service.SongListService
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.orhanobut.logger.Logger
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/13 - 13:50
 * @Email：878749089@qq.com
 * @descriptio：
 */
class SongListServiceImpl @Inject constructor() : SongListService {

    override fun getSongList(id: String, callBack: SongListService.GetSongListCallBack) {
        OkGo.get<String>(Api.getIdSongList(id))
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        Logger.e("歌单-歌曲列表：$json")
                        val jsonData = Gson().fromJson(json, SongItemDto::class.java)
                        val imgUrl = if (jsonData.pic_700 == null) {
                            if (jsonData.pic_500 == null) {
                                if (jsonData.pic_300 == null) {
                                    ""
                                } else {
                                    jsonData.pic_300
                                }
                            } else {
                                jsonData.pic_500
                            }
                        } else {
                            jsonData.pic_700
                        }
                        val titleData = SongTitleData(jsonData.title,jsonData.tag,jsonData.desc)
                        val datas = ArrayList<SongItemData>()
                        jsonData.content.forEach {
                            datas.add(SongItemData(it.title, it.author, it.song_id))
                        }
                        callBack.onSongList(datas)
                        callBack.onTitle(titleData)
                    }
                })
    }

}