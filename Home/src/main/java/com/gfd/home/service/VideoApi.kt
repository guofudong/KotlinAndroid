package com.gfd.home.service

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.GET

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 11:08
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface VideoApi{

    @GET("")
    fun getVideoList():Observable<ResponseBody>
}