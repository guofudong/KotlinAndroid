package com.gfd.music.mvp.preesnter

import android.content.Context
import com.gfd.common.common.BaseApplication.Companion.context
import com.gfd.music.entity.SongItemData
import com.gfd.music.mvp.contract.SearchContract
import com.gfd.music.mvp.service.SearchService
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:51
 * @Email：878749089@qq.com
 * @descriptio：
 */
class SearchPresenter @Inject constructor():SearchContract.Presenter,SearchService.IGetSearchCallBack{

    override fun onDeleteHistory() {
    }

    @Inject
    lateinit var mView:SearchContract.View

    @Inject
    lateinit var mService:SearchService

    override fun getHotSearch() {
        mView.showLoading()
        mService.getHotSearch(this)
    }

    override fun getSearchHistory(context: Context) {
        mService.getSearchHistory(context,this)
    }

    override fun search(context: Context,keyword: String) {
        mService.search(context,keyword,this)
    }

    override fun onSearchHistory(datas: List<String>) {
        mView.showSearchHistory(datas)
    }

    override fun onSearchResult(html: String) {
        mView.showSearchResult(html)
    }

    override fun onHotSearch(datas: List<String>) {
        mView.showHotSearch(datas)
        mView.hideLoading()
    }

}