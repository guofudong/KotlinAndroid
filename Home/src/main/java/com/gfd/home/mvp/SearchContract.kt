package com.gfd.home.mvp

import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.home.entity.SearchItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 15:59
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface SearchContract {

    interface View : BaseView {
        fun showSearchData(datas:List<SearchItemData>)
    }

    interface Presenter : BasePresenter {
        fun search(keyWord: String)
    }
}