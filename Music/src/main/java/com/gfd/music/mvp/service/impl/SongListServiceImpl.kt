package com.gfd.music.mvp.service.impl

import com.gfd.music.api.Api
import com.gfd.music.entity.SongItemData
import com.gfd.music.entity.SongItemBean
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
 * @description：
 */
class SongListServiceImpl @Inject constructor() : SongListService {

    override fun getSongList(id: String, offset: Int, callBack: SongListService.GetSongListCallBack) {
        OkGo.get<String>(Api.getIdSongList(id, offset))
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        Logger.e("歌单-歌曲列表：id = $id - $json")
                        val data = ArrayList<SongItemData>()
                        try {
                            val jsonData = Gson().fromJson(json, SongItemBean::class.java).data
                            if (jsonData != null) {
                                val titleData = SongTitleData(jsonData.songListName, "", jsonData.songListDescription)
                                callBack.onTitle(titleData)
                                if (jsonData.songs != null && jsonData.songs.isNotEmpty()) {
                                    jsonData.songs.forEach {
                                        data.add(SongItemData(it.name, it.time, it.id, it.pic, it.url, it.singer))
                                    }
                                }
                            }
                            callBack.onSongList(data)
                        }catch (e:Exception){
                            callBack.onSongList(data)
                        }

                    }
                })
    }

}