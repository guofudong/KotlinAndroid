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
class MvDetailServiceImpl @Inject constructor() : MvDetailService {


    override fun getMvDetail(mvId: String, callback: MvDetailService.GetMvDetailCallBack) {
        OkGo.get<String>(Api.getMvDetail(mvId))
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        val mvDetail = Gson().fromJson(json, MvDetail::class.java).data
                        if (mvDetail != null) {
                            val mvDetailData = MvDetailData(
                                    mvDetail.id,
                                    mvDetail.name,
                                    mvDetail.singer,
                                    mvDetail.playCount,
                                    mvDetail.publishTime,
                                    mvDetail.url,
                                    mvDetail.pic
                            )
                            callback.onMvDetail(mvDetailData)
                        }
                    }
                })

    }

    override fun getSimlMv(mvId: String, callback: MvDetailService.GetMvDetailCallBack) {
        OkGo.get<String>(Api.getSimilarMv(mvId))
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        val simiData = Gson().fromJson(json, SimiMv::class.java).data
                        val mvDatas = ArrayList<MvData>()
                        if (simiData != null && simiData.videos != null) {
                            simiData.videos.forEach {
                                mvDatas.add(MvData(it.title, it.vid, it.coverUrl, it.title, it.creator[0].userName, 0
                                        , duration = it.durationms))
                            }
                        }
                        callback.onSimiMv(mvDatas)
                    }
                })
    }

    override fun getMvComment(mvId: String, callback: MvDetailService.GetMvDetailCallBack) {
        //api已不能使用，这里用假数据电梯
        /*OkGo.get<String>(Api.getMvComment(mvId))
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        val commentData = Gson().fromJson(json, MvComment::class.java)
                        val mvCommentDatas = ArrayList<CommentData>()
                        commentData.hotComments.forEach {
                            mvCommentDatas.add(CommentData(it.user.nickname, it.user.avatarUrl, it.content,
                                    it.likedCount, it.time, it.liked))
                        }
                        callback.onMvCommnet(mvCommentDatas)
                    }
                })*/
        val mvCommentDatas = ArrayList<CommentData>()
        for (i in 0..6) {
            mvCommentDatas.add(CommentData("Android行动派", "", "关注微信公众号Android行动派获取更多资源", 12,
                    System.currentTimeMillis() - 1000 * 12, false))
        }
        callback.onMvCommnet(mvCommentDatas)
    }

}