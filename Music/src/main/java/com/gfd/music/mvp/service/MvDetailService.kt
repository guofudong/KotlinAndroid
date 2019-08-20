package com.gfd.music.mvp.service

import com.gfd.music.entity.CommentData
import com.gfd.music.entity.MvData
import com.gfd.music.entity.MvDetailData

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 16:34
 * @Email：878749089@qq.com
 * @description：
 */
interface MvDetailService {
    fun getSimilarMv(mvId: String, callback: GetMvDetailCallBack)
    fun getMvComment(mvId: String, callback: GetMvDetailCallBack)
    fun getMvDetail(mvId: String, callback: GetMvDetailCallBack)

    interface GetMvDetailCallBack {
        fun onSimilarMv(data: List<MvData>)
        fun onMvComment(data: List<CommentData>)
        fun onMvDetail(data: MvDetailData)
    }
}