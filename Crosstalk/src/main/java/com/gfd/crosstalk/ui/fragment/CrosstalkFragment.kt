package com.gfd.crosstalk.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import com.gfd.common.common.BaseApplication
import com.gfd.common.common.BaseConstant
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.ui.fragment.BaseMvpFragment
import com.gfd.crosstalk.R
import com.gfd.crosstalk.adapter.VideoAdapter
import com.gfd.crosstalk.entity.Video
import com.gfd.crosstalk.injection.component.DaggerCrosstalkComponent
import com.gfd.crosstalk.injection.moudle.CrosstalkModule
import com.gfd.crosstalk.mvp.contract.CrosstalkContract
import com.gfd.crosstalk.mvp.presenter.CrosstalkPresenter
import com.gfd.crosstalk.ui.activity.WebViewActivity
import com.github.jdsjlzx.ItemDecoration.DividerDecoration
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.tencent.smtt.sdk.TbsVideo
import com.xiao.nicevideoplayer.NiceVideoPlayerManager
import kotlinx.android.synthetic.main.crosstalk_fragment_crosstalk.*

/**
 * @Author : 郭富东
 * @Date ：2018/9/15 - 10:59
 * @Email：878749089@qq.com
 * @description：
 */
@Suppress("DEPRECATION")
class CrosstalkFragment : BaseMvpFragment<CrosstalkPresenter>(), CrosstalkContract.View {

    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var mAdapter: VideoAdapter
    private var isLoadMore = false
    private var videoParent: ViewGroup? = null
    private var page = 1

    override fun injectComponent() {
        DaggerCrosstalkComponent.builder()
                .activityComponent(mActivityComponent)
                .crosstalkModule(CrosstalkModule(this))
                .build()
                .inject(this)

    }

    override fun isSetStateView(): Boolean = false

    override fun getLayoutId(): Int = R.layout.crosstalk_fragment_crosstalk

    override fun initView() {
        mSwipeCrosstalk.setColorSchemeColors(resources.getColor(R.color.common_red))
        mRecycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mAdapter = VideoAdapter(activity!!)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mAdapter)
        mRecycler.adapter = mLRecyclerViewAdapter
        val divider = DividerDecoration.Builder(activity)
                .setHeight(1.toFloat())
                .setPadding(1.toFloat())
                .setColorResource(R.color.common_divider)
                .build()
        mRecycler.setPullRefreshEnabled(false)
        mRecycler.setLoadMoreEnabled(true)
        mRecycler.addItemDecoration(divider)
    }

    override fun initData() {
        mPresenter.getVideoList()
    }

    override fun setListener() {
        mSwipeCrosstalk.setOnRefreshListener {
            page = 1
            mPresenter.getVideoList(page)
        }
        mRecycler.setOnLoadMoreListener {
            if (page > 10) {
                mRecycler.setNoMore(true)
            }
            isLoadMore = true
            page++
            mPresenter.getVideoList(page)
        }
        mAdapter.seOnClickListener(object : BaseAdapter.OnClickListener {
            override fun onClick(view: View, position: Int) {
                val videoUrl = BaseConstant.CROSSTRALK_BASE_URL + mAdapter.getData()[position].source_url
                val videoName = mAdapter.getData()[position].name
                val intent = Intent(activity, WebViewActivity::class.java)
                intent.putExtra("videoUrl", videoUrl)
                intent.putExtra("videoName", videoName)
                startActivity(intent)
            }
        })
    }

    override fun showVideoList(data: List<Video>) {
        if (isLoadMore) {
            isLoadMore = false
            mAdapter.addAll(data)
            mRecycler.refreshComplete(0)
        } else {
            mAdapter.updateData(data)
            if (mSwipeCrosstalk != null) {
                mSwipeCrosstalk.isRefreshing = false
            }
        }
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
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