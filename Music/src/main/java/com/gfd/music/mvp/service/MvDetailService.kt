package com.gfd.music.mvp.service

import com.gfd.music.entity.CommentData
import com.gfd.music.entity.MvData
import com.gfd.music.entity.MvDetailData

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 16:34
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface MvDetailService {

    fun getSimlMv(mvId: String, callback: MvDetailService.GetMvDetailCallBack)
    fun getMvComment(mvId: String, callback: MvDetailService.GetMvDetailCallBack)
    fun getMvDetail(mvId: String, callback: MvDetailService.GetMvDetailCallBack)

    interface GetMvDetailCallBack {
        fun onSimiMv(datas: List<MvData>)
        fun onMvCommnet(datas: List<CommentData>)
        fun onMvDetail(datas: MvDetailData)
    }
}