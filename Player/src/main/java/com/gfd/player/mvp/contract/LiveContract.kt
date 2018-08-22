package com.gfd.player.mvp.contract

import android.content.Context
import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.player.entity.LiveDataDto
import com.gfd.player.entity.TimeTableData

/**
 * @Author : 郭富东
 * @Date ：2018/8/20 - 16:25
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface LiveContract {

    interface View : BaseView {

        fun showLiveInfo(datas: List<LiveDataDto.LiveData>)
        fun showTimeTable(datas: List<TimeTableData>)
        fun showVideo(url: String)

    }

    interface Presenter : BasePresenter {
        fun getLiveInfo(context: Context)
        fun getPlayUrl(context:Context,url: String)
    }
}