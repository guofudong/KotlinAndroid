package com.gfd.music.mvp.preesnter

import android.content.Context
import com.gfd.common.common.BaseApplication.Companion.context
import com.gfd.music.entity.SearchData
import com.gfd.music.entity.SongItemData
import com.gfd.music.mvp.contract.SearchContract
import com.gfd.music.mvp.service.SearchService
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:51
 * @Email：878749089@qq.com
 * @description：
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

    override fun onSearchHistory(data: List<String>) {
        mView.showSearchHistory(data)
    }

    override fun onSearchResult(data: List<SearchData>) {
        mView.showSearchResult(data)
    }

    override fun onHotSearch(data: List<String>) {
        mView.showHotSearch(data)
        mView.hideLoading()
    }

}