package com.gfd.music.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import com.gfd.common.ui.fragment.BaseMvpFragment
import com.gfd.music.R
import com.gfd.music.adapter.MvListAdapter
import com.gfd.music.entity.MvData
import com.gfd.music.injection.component.DaggerShortFilmComponent
import com.gfd.music.injection.module.ShortFilmModule
import com.gfd.music.mvp.contract.ShortFilmContract
import com.gfd.music.mvp.preesnter.ShortFilmPresenter
import com.gfd.music.ui.activity.MvDetailActivity
import com.github.jdsjlzx.ItemDecoration.DividerDecoration
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.xiao.nicevideoplayer.NiceVideoPlayerManager
import kotlinx.android.synthetic.main.music_fragment_short_film.*


/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:57
 * @Email：878749089@qq.com
 * @description：短片Fragment
 */
@Suppress("DEPRECATION")
class ShortFilmFragment : BaseMvpFragment<ShortFilmPresenter>(), ShortFilmContract.View {

    private lateinit var mAdapter: MvListAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private var videoParent: ViewGroup? = null
    private var offset = 0
    private var isLoadMore = false
    private var isSwitchVisible = false
    override fun getLayoutId(): Int = R.layout.music_fragment_short_film

    override fun injectComponent() {
        DaggerShortFilmComponent.builder()
                .activityComponent(mActivityComponent)
                .shortFilmModule(ShortFilmModule(this))
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
            if (offset > 10) {
                offset = 0
            } else {
                isLoadMore = true
                mPresenter.getMvList(++offset, false)
            }
        }
        mAdapter.setOnTitleClickListener { _, data ->
            isSwitchVisible = false
            val intent = Intent(activity, MvDetailActivity::class.java)
            intent.putExtra("json", data)
            startActivity(intent)
        }

    }

    override fun showMvList(data: List<MvData>) {
        if (isLoadMore) {
            isLoadMore = false
            mAdapter.addAll(data)
            mMvRecycler.refreshComplete(0)
        } else {
            mAdapter.updateData(data)
            mMvSwipe.isRefreshing = false
        }
        mLRecyclerViewAdapter.notifyDataSetChanged()

    }

    override fun onResume() {
        super.onResume()
        isSwitchVisible = true
        if (videoParent != null) {
            val parentView = NiceVideoPlayerManager.instance().currentNiceVideoPlayer.parent as ViewGroup
            parentView.removeView(NiceVideoPlayerManager.instance().currentNiceVideoPlayer)
            videoParent?.addView(NiceVideoPlayerManager.instance().currentNiceVideoPlayer)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer()
    }

}