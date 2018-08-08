package com.gfd.home.mvp

import android.content.Context
import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.home.entity.SearchItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 15:59
 * @Email：878749089@qq.com
 * @descriptio：搜索页面协议
 */
interface SearchContract {

    interface View : BaseView {
        fun showSearchData(datas: List<SearchItemData>)

        fun showSearchHistory(historys: List<String>)

    }

    interface Presenter : BasePresenter {

        fun search(context: Context, keyWord: String)

        fun deleteHistory(context: Context)

        fun getSearchHistory(context: Context)
    }
}