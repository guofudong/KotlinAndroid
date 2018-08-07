package com.gfd.home.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.common.utils.ToastUtils
import com.gfd.home.R
import com.gfd.home.adapter.SearchDataAdapter
import com.gfd.home.entity.SearchItemData
import com.gfd.home.injection.component.DaggerSearchComponent
import com.gfd.home.injection.module.SearchMoudle
import com.gfd.home.mvp.SearchContract
import com.gfd.home.mvp.presenter.SearchPresenter
import com.gfd.provider.router.RouterPath
import com.github.jdsjlzx.ItemDecoration.DividerDecoration
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_serach.*


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
    private lateinit var mDatas : List<SearchItemData>
    override fun injectComponent() {
        DaggerSearchComponent.builder()
                .activityComponent(mActivityComponent)
                .searchMoudle(SearchMoudle(this))
                .build()
                .inject(this)
        ARouter.getInstance().inject(this)

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_serach
    }

    override fun initView() {
        mVideoList.layoutManager = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
        mDataAdapter = SearchDataAdapter(this@SearchActivity)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mDataAdapter)
        mVideoList.adapter = mLRecyclerViewAdapter
        //添加分割线
        val divider = DividerDecoration.Builder(this)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.colorItemDecoration)
                .build()
        mVideoList.setLoadMoreEnabled(false)
        mVideoList.setPullRefreshEnabled(false)
        mVideoList.addItemDecoration(divider)
    }

    override fun initData() {


    }

    override fun setListener() {
        tvSearchBtn.setOnClickListener {
            if (!isSearch) {
                if (TextUtils.isEmpty(edSearch.text.toString())) {
                    ToastUtils.instance.showToast("搜索内容不能为空")
                    return@setOnClickListener
                } else {
                    isSearch = true
                    mPresenter.search(edSearch.text.toString())
                }
            }
        }
        //立即播放按钮
        mDataAdapter.setOnPlayClickListener(object :SearchDataAdapter.OnPlayClickListener{
            override fun onPlayClick(positon: Int) {
                val data = mDatas[positon]
                ARouter.getInstance().build(RouterPath.Player.PATH_PLAYER_WEB)
                        .withString(RouterPath.Player.KEY_PLAYER, data.videoUrl)
                        .withString(RouterPath.Player.KEY_IMAGE, data.imgUrl)
                        .withString(RouterPath.Player.KEY_NAME, data.name)
                        .navigation()
            }

        })
    }

    override fun showSearchData(datas: List<SearchItemData>) {
        mDatas = datas
        isSearch = false//状态重置
        mDataAdapter.addAll(datas)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }

}