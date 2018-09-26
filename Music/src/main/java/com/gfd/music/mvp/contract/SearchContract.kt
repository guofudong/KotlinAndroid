package com.gfd.music.mvp.contract

import android.content.Context
import com.gfd.common.mvp.presenter.BasePresenter
import com.gfd.common.mvp.view.BaseView
import com.gfd.music.entity.SearchData
import com.gfd.music.entity.SongItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:49
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface SearchContract{

    interface View :BaseView{
        fun showSearchHistory(datas:List<String>)
        fun showHotSearch(datas:List<String>)
        fun showSearchResult(datas: List<SearchData>)

    }

    interface Presenter:BasePresenter{
        fun getHotSearch()
        fun getSearchHistory(context: Context)
        fun search(context: Context,keyword:String)
    }
}
