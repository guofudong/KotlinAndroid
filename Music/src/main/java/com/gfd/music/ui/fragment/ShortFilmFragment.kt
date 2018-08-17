package com.gfd.music.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.gfd.common.ui.adapter.BaseViewHolder
import com.gfd.common.ui.fragment.BaseMvpFragment
import com.gfd.music.R
import com.gfd.music.adapter.MvListAdapter
import com.gfd.music.entity.MvData
import com.gfd.music.injection.component.DaggerShortFilmComponent
import com.gfd.music.injection.module.ShortFilmMoudle
import com.gfd.music.mvp.contract.ShortFilmContract
import com.gfd.music.mvp.preesnter.ShortFilmPresenter
import com.gfd.music.ui.activity.MvDetailActivity
import com.github.jdsjlzx.ItemDecoration.DividerDecoration
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.xiao.nicevideoplayer.NiceVideoPlayer
import com.xiao.nicevideoplayer.NiceVideoPlayerManager
import kotlinx.android.synthetic.main.fragment_short_film.*


/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:57
 * @Email：878749089@qq.com
 * @descriptio：短片Fragment
 */
class ShortFilmFragment : BaseMvpFragment<ShortFilmPresenter>(), ShortFilmContract.View{

    private lateinit var mAdapter: MvListAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var mMvDatas: List<MvData>
    private var offset = 0
    private var isLoadMore = false
    override fun getLayoutId(): Int {
        return R.layout.fragment_short_film
    }

    override fun injectComponent() {
        DaggerShortFilmComponent.builder()
                .activityComponent(mActivityComponent)
                .shortFilmMoudle(ShortFilmMoudle(this))
                .build()
                .inject(this)

    }

    override fun initView() {
        mMvSwipe.setColorSchemeColors(resources.getColor(R.color.common_red))
        mMvRecycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mAdapter = MvListAdapter(activity!!)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mAdapter)
        mMvRecycler.adapter = mLRecyclerViewAdapter
        val divider = DividerDecoration.Builder(activity)
                .setHeight(1.toFloat())
                .setPadding(1.toFloat())
                .setColorResource(R.color.common_divider)
                .build()
        mMvRecycler.setPullRefreshEnabled(false)
        mMvRecycler.setLoadMoreEnabled(true)
        mMvRecycler.addItemDecoration(divider)
    }

    override fun initData() {
        mPresenter.getMvList(offset, true)
    }

    override fun setListener() {
        mMvSwipe.setOnRefreshListener {
            offset = 0
            mPresenter.getMvList(offset, false)
        }
        mMvRecycler.setOnLoadMoreListener {
            if (mMvDatas.size > 12) {
                mMvRecycler.setNoMore(true)
            } else {
                isLoadMore = true
                mPresenter.getMvList(mAdapter.getItemSize(), false)
            }
        }
        mAdapter.setOnTitleClickListener { _, data ->
            val intent = Intent(activity, MvDetailActivity::class.java)
            intent.putExtra("json",data)
            startActivity(intent)
        }

    }

    override fun showMvList(datas: List<MvData>) {
        if (isLoadMore) {
            isLoadMore = false
            mAdapter.addAll(datas)
            mMvRecycler.refreshComplete(0)
        } else {
            mAdapter.updateData(datas)
            mMvSwipe.isRefreshing = false
        }
        mMvDatas = mAdapter.getDatas()
        mLRecyclerViewAdapter.notifyDataSetChanged()

    }

    override fun onStop() {
        super.onStop()
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer()
    }

}