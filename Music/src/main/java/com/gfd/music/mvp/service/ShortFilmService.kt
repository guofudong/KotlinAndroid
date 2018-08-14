package com.gfd.music.mvp.service

import com.gfd.music.entity.MvData

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 17:29
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface ShortFilmService {

    fun getMvList(offset: Int, callBack: GetMvCallBack, size: Int = 12)

    interface GetMvCallBack {
        fun onMvData(datas: List<MvData>)
    }
}