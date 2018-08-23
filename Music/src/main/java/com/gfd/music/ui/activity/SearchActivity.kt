package com.gfd.music.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.music.R
import com.gfd.music.adapter.HistoryAdapter
import com.gfd.music.adapter.HotSearchAdapter
import com.gfd.music.entity.SearchData
import com.gfd.music.entity.SongItemData
import com.gfd.music.injection.component.DaggerSearchComponent
import com.gfd.music.injection.module.SearchMoudle
import com.gfd.music.mvp.contract.SearchContract
import com.gfd.music.mvp.preesnter.SearchPresenter
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.music_activity_search.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/23 - 16:43
 * @Email：878749089@qq.com
 * @descriptio：搜索歌曲页面
 */
class SearchActivity:BaseMvpActivity<SearchPresenter>(),SearchContract.View{

    private lateinit var mHotSearchAdapter: HotSearchAdapter
    private lateinit var mHistoryAdapter: HistoryAdapter
    private lateinit var mHotDatas :List<String>
    private lateinit var mHistoryDatas :List<String>
    override fun getLayoutId(): Int {
        return  R.layout.music_activity_search
    }
    override fun injectComponent() {
        DaggerSearchComponent.builder()
                .activityComponent(mActivityComponent)
                .searchMoudle(SearchMoudle(this))
                .build()
                .inject(this)
        setStatusBar()

    }

    override fun initView() {
        initHotSearchList()
        initSearchHistoryList()
        initSearchResultList()
    }

    override fun initData() {
        mPresenter.getHotSearch()
        mPresenter.getSearchHistory(this@SearchActivity)
    }

    /** 初始化热门搜索列表 */
    private fun initHotSearchList() {
        val flexManager = FlexboxLayoutManager(this@SearchActivity)
        flexManager.flexDirection = FlexDirection.ROW
        flexManager.alignItems = AlignItems.STRETCH
        flexManager.flexWrap = FlexWrap.WRAP
        hotSearchList.layoutManager = flexManager
        mHotSearchAdapter = HotSearchAdapter(this@SearchActivity)
        hotSearchList.adapter = mHotSearchAdapter
    }

    /** 初始化搜索历史列表 */
    private fun initSearchHistoryList() {
        searchHistoryList.layoutManager = LinearLayoutManager(this@SearchActivity,LinearLayoutManager.VERTICAL,false)
        mHistoryAdapter = HistoryAdapter(this@SearchActivity)
        searchHistoryList.adapter = mHistoryAdapter

    }

    /** 初始化搜索结果列表*/
    private fun initSearchResultList() {
        searchResultList.layoutManager = LinearLayoutManager(this@SearchActivity,LinearLayoutManager.VERTICAL,false)
        searchResultList.adapter = mHistoryAdapter
    }

    override fun showSearchHistory(datas: List<String>) {
        mHistoryDatas = datas
        mHistoryAdapter.updateData(mHistoryDatas)

    }

    override fun showHotSearch(datas: List<String>) {
        mHotDatas = datas
        mHotSearchAdapter.updateData(mHotDatas)
    }

    override fun showSearchResult(datas: List<SongItemData>) {
    }


}