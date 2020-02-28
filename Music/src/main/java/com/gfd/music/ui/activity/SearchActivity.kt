package com.gfd.music.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gfd.common.ext.setOnActionListener
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.common.utils.ImageLoader
import com.gfd.music.R
import com.gfd.music.adapter.HistoryAdapter
import com.gfd.music.adapter.HotSearchAdapter
import com.gfd.music.adapter.SearchAdapter
import com.gfd.music.entity.SearchData
import com.gfd.music.injection.component.DaggerSearchComponent
import com.gfd.music.injection.module.SearchModule
import com.gfd.music.mvp.contract.SearchContract
import com.gfd.music.mvp.preesnter.SearchPresenter
import com.gfd.music.service.MusicPlayService
import com.gfd.music.service.MusicServiceStub
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.music_activity_search.*
import kotlinx.android.synthetic.main.music_layout_play_contral.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/23 - 16:43
 * @Email：878749089@qq.com
 * @description：搜索歌曲页面
 */
class SearchActivity : BaseMvpActivity<SearchPresenter>(), SearchContract.View {

    private lateinit var mHotSearchAdapter: HotSearchAdapter
    private lateinit var mHistoryAdapter: HistoryAdapter
    private lateinit var mSearchAdapter: SearchAdapter
    private lateinit var mHotData: List<String>
    private lateinit var mHistoryData: List<String>
    private var mPlayService: MusicPlayService? = null

    override fun getLayoutId(): Int = R.layout.music_activity_search

    override fun injectComponent() {
        DaggerSearchComponent.builder()
                .activityComponent(mActivityComponent)
                .searchModule(SearchModule(this))
                .build()
                .inject(this)
        setStatusBar()
        bindService(Intent(this@SearchActivity, MusicPlayService::class.java),
                object : ServiceConnection {
                    override fun onServiceDisconnected(name: ComponentName) {

                    }

                    override fun onServiceConnected(name: ComponentName, service: IBinder) {
                        mPlayService = (service as MusicServiceStub).getService()
                    }

                }, Context.BIND_AUTO_CREATE)

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

    override fun setListener() {
        //监听回车键
        edKeyword.setOnActionListener {
            onSearch(::search)
        }
        mSearchAdapter.seOnClickListener(object : com.gfd.common.ui.adapter.BaseAdapter.OnClickListener {
            override fun onClick(view: View, position: Int) {
                val song = mSearchAdapter.getData()[position]
                ImageLoader.loadUrlImage(this@SearchActivity, song.pic, album)
                songName.text = song.name
                songaAtist.text = song.singer
                mPlayService?.playMusic(song.playerUrl)
            }
        })
        mHistoryAdapter.seOnClickListener(object : com.gfd.common.ui.adapter.BaseAdapter.OnClickListener {
            override fun onClick(view: View, position: Int) {
                val keyword = mHistoryData[position]
                search(keyword)
            }
        })
        ivBack.setOnClickListener {
            finish()
        }
    }

    private fun search(keyword: String) {
        hotRootLayout.visibility = View.VISIBLE
        mSearchResultList.visibility = View.GONE
        playContralRoot.visibility = View.GONE
        mPresenter.search(this@SearchActivity, keyword)
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
        searchHistoryList.layoutManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
        mHistoryAdapter = HistoryAdapter(this@SearchActivity)
        searchHistoryList.adapter = mHistoryAdapter
    }

    private fun initSearchResultList() {
        mSearchResultList.layoutManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
        mSearchAdapter = SearchAdapter(this@SearchActivity)
        mSearchResultList.adapter = mSearchAdapter
    }

    override fun showSearchHistory(datas: List<String>) {
        mHistoryData = datas
        mHistoryAdapter.updateData(mHistoryData)

    }

    override fun showHotSearch(datas: List<String>) {
        mHotData = datas
        mHotSearchAdapter.updateData(mHotData)
    }

    override fun showSearchResult(datas: List<SearchData>) {
        if (datas.isNotEmpty()) {
            mSearchResultList.visibility = View.VISIBLE
            hotRootLayout.visibility = View.GONE
            playContralRoot.visibility = View.VISIBLE
            mSearchAdapter.updateData(datas)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayService?.stop()
    }
}