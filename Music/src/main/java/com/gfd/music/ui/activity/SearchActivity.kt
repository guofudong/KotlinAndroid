package com.gfd.music.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.common.utils.ImageLoader
import com.gfd.common.utils.ToastUtils
import com.gfd.music.R
import com.gfd.music.adapter.HistoryAdapter
import com.gfd.music.adapter.HotSearchAdapter
import com.gfd.music.adapter.SearchAdapter
import com.gfd.music.entity.SearchData
import com.gfd.music.injection.component.DaggerSearchComponent
import com.gfd.music.injection.module.SearchMoudle
import com.gfd.music.mvp.contract.SearchContract
import com.gfd.music.mvp.preesnter.SearchPresenter
import com.gfd.music.service.MusicPlayService
import com.gfd.music.service.MusicServiceStub
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.layout_play_contral.*
import kotlinx.android.synthetic.main.music_activity_search.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/23 - 16:43
 * @Email：878749089@qq.com
 * @descriptio：搜索歌曲页面
 */
class SearchActivity : BaseMvpActivity<SearchPresenter>(), SearchContract.View {

    private lateinit var mHotSearchAdapter: HotSearchAdapter
    private lateinit var mHistoryAdapter: HistoryAdapter
    private lateinit var mSearchAdapter: SearchAdapter
    private lateinit var mHotDatas: List<String>
    private lateinit var mHistoryDatas: List<String>
    private var mPlayService: MusicPlayService? = null
    override fun getLayoutId(): Int {
        return R.layout.music_activity_search
    }

    override fun injectComponent() {
        DaggerSearchComponent.builder()
                .activityComponent(mActivityComponent)
                .searchMoudle(SearchMoudle(this))
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
        edKeyword.setOnEditorActionListener { _, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEND || (keyEvent != null && keyEvent.keyCode == KeyEvent.KEYCODE_ENTER)) {
                searchSong(edKeyword.text.toString().trim())
                true
            }
            false
        }
        mSearchAdapter.seOnClickListener(object : com.gfd.common.ui.adapter.BaseAdapter.OnClickListener {
            override fun onClick(view: View, position: Int) {
                val song = mSearchAdapter.getDatas()[position]
                ImageLoader.loadUrlImage(this@SearchActivity, song.pic, album)
                songName.text = song.name
                songaAtist.text = song.artist
                mPlayService?.playMusic(song.url_music)
            }

        })
        mHistoryAdapter.seOnClickListener(object : com.gfd.common.ui.adapter.BaseAdapter.OnClickListener {
            override fun onClick(view: View, position: Int) {
                val keyword = mHistoryDatas[position]
                searchSong(keyword)
            }

        })
    }

    private fun searchSong(keyword:String) {
        if (TextUtils.isEmpty(keyword)) {
            ToastUtils.instance.showToast("搜索内容不能为空")
            return
        } else {
            //隐藏键盘
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(edKeyword.windowToken, 0)
            serach(keyword)
        }
    }

    private fun serach(keyword: String) {
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
        mHistoryDatas = datas
        mHistoryAdapter.updateData(mHistoryDatas)

    }

    override fun showHotSearch(datas: List<String>) {
        mHotDatas = datas
        mHotSearchAdapter.updateData(mHotDatas)
    }

    override fun showSearchResult(datas: List<SearchData>) {
        if (!datas.isEmpty()) {
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