package com.gfd.home.mvp.presenter

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
    lateinit var mView : SearchContract.View

    override fun onSearch(datas: List<SearchItemData>) {
        mView.hideLoading()
        mView.showSearchData(datas)
    }


    override fun search(keyWord: String) {
        mView.showLoading()
        mSearchService.search(keyWord,this)
    }

}