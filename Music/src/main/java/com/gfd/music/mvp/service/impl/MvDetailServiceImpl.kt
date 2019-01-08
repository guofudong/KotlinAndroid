package com.gfd.music.mvp.service.impl

import com.gfd.music.api.Api
import com.gfd.music.entity.*
import com.gfd.music.mvp.service.MvDetailService
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 16:53
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MvDetailServiceImpl @Inject constructor():MvDetailService{

    override fun getSimlMv(mvId:String,callback: MvDetailService.GetMvDetailCallBack) {
      /*  OkGo.get<String>(Api.getSimilarMv(mvId))
                .tag(this)
                .execute(object:StringCallback(){
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        val simiData = Gson().fromJson(json, SimiMvDto::class.java)
                        val mvDatas = ArrayList<MvData>()
                        simiData.mvs.forEach {
                            mvDatas.add(MvData(it.name,it.id,it.cover,"${it.briefDesc}",it.artistName
                            ,it.playCount,it.artistId,it.id,it.artistName,it.duration))
                        }
                        callback.onSimiMv(mvDatas)
                    }

                })*/
    }

    override fun getMvComment(mvId:String,callback: MvDetailService.GetMvDetailCallBack) {
        OkGo.get<String>(Api.getMvComment(mvId))
                .tag(this)
                .execute(object:StringCallback(){
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        val commentData = Gson().fromJson(json, MvCommentDto::class.java)
                        val mvCommentDatas = ArrayList<CommentData>()
                        commentData.hotComments.forEach {
                            mvCommentDatas.add(CommentData(it.user.nickname,it.user.avatarUrl,it.content,
                                    it.likedCount,it.time,it.liked))
                        }
                        callback.onMvCommnet(mvCommentDatas)
                    }
                })
    }

}