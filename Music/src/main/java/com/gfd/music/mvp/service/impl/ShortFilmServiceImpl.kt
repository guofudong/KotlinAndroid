package com.gfd.music.mvp.service.impl

import com.gfd.music.api.Api
import com.gfd.music.entity.MvData
import com.gfd.music.entity.MvDto
import com.gfd.music.mvp.service.ShortFilmService
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.orhanobut.logger.Logger
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 17:32
 * @Email：878749089@qq.com
 * @descriptio：
 */
class ShortFilmServiceImpl @Inject constructor() : ShortFilmService {

    override fun getMvList(offset: Int, callBack: ShortFilmService.GetMvCallBack, size: Int) {
        OkGo.get<String>(Api.getRecommendMV(offset))
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        Logger.e("mv数据:$json")
                        val mvDto = Gson().fromJson(json, MvDto::class.java)
                        val mvDatas = ArrayList<MvData>()
                        if(mvDto.data != null){
                            mvDto.data.forEach {
                                mvDatas.add(MvData(it.name, it.id, it.pic, it.desc
                                        ?: "", it.singer,
                                        it.playCount, videoUrl = it.url))
                            }
                            callBack.onMvData(mvDatas)
                        }
                    }
                })
    }

}