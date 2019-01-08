package com.gfd.home.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.utils.ToastUtils
import com.gfd.home.R
import com.gfd.home.adapter.HistoryAdapter
import com.gfd.home.adapter.SearchDataAdapter
import com.gfd.home.entity.SearchItemData
import com.gfd.home.injection.component.DaggerSearchComponent
import com.gfd.home.injection.module.SearchMoudle
import com.gfd.home.mvp.SearchContract
import com.gfd.home.mvp.presenter.SearchPresenter
import com.gfd.provider.router.RouterPath
import com.github.jdsjlzx.ItemDecoration.DividerDecoration
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.home_activity_serach.*
import kotlinx.android.synthetic.main.home_layout_search_empty.*
import kotlinx.android.synthetic.main.home_layout_serach_history.*


/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 15:28
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Route(path = RouterPath.Home.PATH_SERACH)
class SearchActivity : BaseMvpActivity<SearchPresenter>(), SearchContract.View {

    private var isSearch = false
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var mDataAdapter: SearchDataAdapter
    private lateinit var mDatas: List<SearchItemData>
    private lateinit var mHistoryDatas: List<String>
    private lateinit var mHistoryAdapter: HistoryAdapter

    override fun injectComponent() {
        DaggerSearchComponent.builder()
                .activityComponent(mActivityComponent)
                .searchMoudle(SearchMoudle(this))
                .build()
                .inject(this)
        ARouter.getInstance().inject(this)

    }

    override fun getLayoutId(): Int {
        return R.layout.home_activity_serach
    }

    override fun initView() {
        setSearchList()
        setHistoryList()
    }


    override fun initData() {
        mPresenter.getSearchHistory(this@SearchActivity)
    }

    override fun setListener() {
        tvSearchBtn.setOnClickListener {
            search()
        }
        //立即播放按钮
        mDataAdapter.setOnPlayClickListener(object : SearchDataAdapter.OnPlayClickListener {
            override fun onPlayClick(positon: Int) {
                val data = mDatas[positon]
                ARouter.getInstance().build(RouterPath.Player.PATH_PLAYER_WEB)
                        .withString(RouterPath.Player.KEY_PLAYER, data.videoUrl)
                        .withString(RouterPath.Player.KEY_IMAGE, data.imgUrl)
                        .withString(RouterPath.Player.KEY_NAME, data.name)
                        .navigation()
            }

        })
        mHistoryAdapter.seOnClickListener(object : BaseAdapter.OnClickListener {
            override fun onClick(view: View, position: Int) {
                serach(mHistoryDatas[position])
            }
        })
        //删除历史搜索记录
        historyDelete.setOnClickListener {
            mPresenter.deleteHistory(this)
        }
        //监听回车键
        edSearch.setOnEditorActionListener { _, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.keyCode && KeyEvent.ACTION_DOWN == keyEvent.action)) {
                search()
            }
            true
        }

    }

    /** 搜索*/
    private fun search() {
        if (!isSearch) {
            if (TextUtils.isEmpty(edSearch.text.toString())) {
                ToastUtils.instance.showToast("搜索内容不能为空")
                return
            } else {
                serach(edSearch.text.toString())
            }
        }
    }

    /**
     * 以关键字进行搜索
     * @param keyWord String
     */
    private fun serach(keyWord: String) {
        isSearch = true
        rootHistory.visibility = View.GONE
        rootSearchEmpy.visibility = View.GONE
        mVideoList.visibility = View.VISIBLE
        mPresenter.search(this, keyWord)
    }

    /**设置搜索历史展示list*/
    private fun setHistoryList() {
        val flexManager = FlexboxLayoutManager(this@SearchActivity)
        flexManager.flexDirection = FlexDirection.ROW
        flexManager.alignItems = AlignItems.STRETCH
        flexManager.flexWrap = FlexWrap.WRAP
        historyList.layoutManager = flexManager
        mHistoryAdapter = HistoryAdapter(this@SearchActivity)
        historyList.adapter = mHistoryAdapter
    }

    /** 设置搜索结果展示List */
    private fun setSearchList() {
        mVideoList.layoutManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
        mDataAdapter = SearchDataAdapter(this@SearchActivity)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mDataAdapter)
        mVideoList.adapter = mLRecyclerViewAdapter
        //添加分割线
        val divider = DividerDecoration.Builder(this)
                .setHeight(R.dimen.home_default_divider_height)
                .setPadding(R.dimen.home_default_divider_padding)
                .setColorResource(R.color.colorItemDecoration)
                .build()
        mVideoList.setLoadMoreEnabled(false)
        mVideoList.setPullRefreshEnabled(false)
        mVideoList.addItemDecoration(divider)
    }

    override fun showSearchData(datas: List<SearchItemData>) {
        mDatas = datas
        isSearch = false//状态重置
        if (mDatas.isEmpty()) {
            rootSearchEmpy.visibility = View.VISIBLE
            mVideoList.visibility = View.GONE
        } else {
            mDataAdapter.updateData(datas)
            mLRecyclerViewAdapter.notifyDataSetChanged()
        }

    }

    override fun showSearchHistory(historys: List<String>) {
        mHistoryDatas = historys
        if (mHistoryDatas.isEmpty()) {
            rootHistory.visibility = View.GONE
        } else {
            rootHistory.visibility = View.VISIBLE
        }
        mHistoryAdapter.updateData(historys)
    }

}