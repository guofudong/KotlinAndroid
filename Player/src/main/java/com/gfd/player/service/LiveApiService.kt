package com.gfd.player.service


import android.content.Context
import com.gfd.player.entity.Live
import com.gfd.player.entity.LiveDataDto
import com.gfd.player.entity.TimeTableData

/**
 * @Author : 郭富东
 * @Date ：2018/8/20 - 15:52
 * @Email：878749089@qq.com
 * @description：
 */
interface LiveApiService{

    fun getLiveInfo(context: Context,callback: IGetLiveCallback)
    fun getPlayUrl(context: Context,url:String,callback: IGetLiveCallback)

    interface IGetLiveCallback{
        fun onLive(data:List<Live>)
        fun onTimeTables(data: List<TimeTableData>)
        fun onPlayUrl(url:String)
    }
}