package com.gfd.home.mvp.presenter

import android.content.Context
import com.gfd.home.entity.SearchItemData
import com.gfd.home.mvp.SearchContract
import com.gfd.home.service.SearchService
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 16:27
 * @Email：878749089@qq.com
 * @descriptio：
 */
class SearchPresenter @Inject constructor() : SearchContract.Presenter, SearchService.SearchCallBack {

    @Inject
    lateinit var mSearchService: SearchService

    @Inject
    lateinit var mView: SearchContract.View

    override fun onSearch(datas: List<SearchItemData>) {
        mView.hideLoading()
        mView.showSearchData(datas)
    }

    override fun search(context: Context,keyWord: String) {
        mView.showLoading()
        mSearchService.search(context,keyWord, this)
    }

    override fun getSearchHistory(context: Context) {
        mView.showLoading()
        mSearchService.getSearchHistory(context,this)
    }

    override fun deleteHistory(context: Context) {
        mSearchService.deleteHistory(context,this)
    }
    override fun onHistory(history: List<String>) {
        mView.hideLoading()
        mView.showSearchHistory(history)
    }

}