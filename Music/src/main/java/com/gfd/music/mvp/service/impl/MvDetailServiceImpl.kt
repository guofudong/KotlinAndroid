package com.gfd.music.mvp.service.impl

import com.gfd.music.api.Api
import com.gfd.music.entity.*
import com.gfd.music.mvp.service.MvDetailService
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import org.json.JSONObject
import java.lang.Exception
import java.lang.Long
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 16:53
 * @Email：878749089@qq.com
 * @description：
 */
class MvDetailServiceImpl @Inject constructor() : MvDetailService {


    override fun getMvDetail(mvId: String, callback: MvDetailService.GetMvDetailCallBack) {
        OkGo.get<String>(Api.getMvDetail(mvId))
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        try {
                            val json = response.body().toString()
                            val obj = JSONObject(json).getJSONObject("data").getJSONObject(mvId)
                            val mvDetailData = MvDetailData(
                                    obj.getString("vid"),
                                    obj.getString("name"),
                                    obj.getJSONArray("singers").getJSONObject(0).getString("name"),
                                    obj.getInt("playcnt"),
                                    getDataFormTimeStamp(obj.getLong("pubdate") * 1000),
                                    obj.getJSONObject("url").getJSONArray("mp4").getJSONObject(4).getJSONArray("freeflow_url").getString(0),
                                    obj.getString("cover_pic")
                            )
                            callback.onMvDetail(mvDetailData)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })

    }

    private fun getDataFormTimeStamp(timeStamp: kotlin.Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date(timeStamp))
    }

    override fun getSimilarMv(mvId: String, callback: MvDetailService.GetMvDetailCallBack) {
        OkGo.get<String>(Api.getSimilarMv(mvId))
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        try {
                            val json = response.body().toString()
                            val mvDto = Gson().fromJson(json, MvDto::class.java)
                            val mvData = ArrayList<MvData>()
                            if (mvDto.data.list != null) {
                                mvDto.data.list.forEach {
                                    mvData.add(MvData(it.name, it.id, it.pic, it.desc
                                            ?: "", it.singer ?: "",
                                            it.playCount, duration = it.pubdate * 1000, videoUrl = it . url ?: ""))
                                }
                            }
                            callback.onSimilarMv(mvData)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                })
    }

    override fun getMvComment(mvId: String, callback: MvDetailService.GetMvDetailCallBack) {
        OkGo.get<String>(Api.getMvComment(mvId))
                .tag(this)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                     try {
                         val json = response.body().toString()
                         val commentData = Gson().fromJson(json, MvComment::class.java).data.commentlist
                         val mvCommentData = ArrayList<CommentData>()
                         commentData.forEach {
                             mvCommentData.add(CommentData(it.nick, it.avatarurl, it.rootcommentcontent,
                                     it.permission, it.time.toLong() * 1000, it.is_medal == 1))
                         }

                         callback.onMvComment(mvCommentData)
                     }catch (e:Exception){
                         e.printStackTrace()
                     }
                    }
                })
    }

}