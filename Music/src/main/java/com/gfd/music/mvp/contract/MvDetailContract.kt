package com.gfd.music.mvp.contract

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.music.entity.CommentData
import com.gfd.music.entity.MvData
import com.gfd.music.entity.MvDetailData

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:49
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface MvDetailContract{

    interface View :BaseView{

        fun showMvDetail(data: MvDetailData)
        fun showSimiMv(datas:List<MvData>)
        fun showMvComment(datas: List<CommentData>)

    }

    interface Presenter:BasePresenter{

        fun getSimiMv(mvId:String)
        fun getMvComment(mvId:String)
        fun getMvDetail(mvId:String)

    }
}
