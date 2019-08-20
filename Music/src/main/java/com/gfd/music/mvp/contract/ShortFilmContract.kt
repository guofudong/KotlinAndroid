package com.gfd.music.mvp.contract

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.music.entity.MvData

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:56
 * @Email：878749089@qq.com
 * @description：
 */
interface ShortFilmContract {
    interface View : BaseView {

        fun showMvList(data: List<MvData>)

    }

    interface Presenter : BasePresenter {
        fun getMvList(offset: Int, isLoading: Boolean)
    }
}