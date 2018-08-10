package com.gfd.music.ui.fragment

import android.view.LayoutInflater
import android.widget.TextView
import com.gfd.common.ext.gridInit
import com.gfd.common.ext.player
import com.gfd.common.ui.fragment.BaseMvpFragment
import com.gfd.music.R
import com.gfd.music.adapter.RecommendAdapter
import com.gfd.music.common.Concant
import com.gfd.music.entity.BannerData
import com.gfd.music.entity.SongData
import com.gfd.music.injection.component.DaggerMusicComponent
import com.gfd.music.injection.module.MusicMoudle
import com.gfd.music.mvp.contract.RecommendContract
import com.gfd.music.mvp.preesnter.RecommendPresenter
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.fragment_recommend.*
import java.util.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:48
 * @Email：878749089@qq.com
 * @descriptio：推荐Fragment
 */
class RecommendFragment : BaseMvpFragment<RecommendPresenter>(), RecommendContract.View {


    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var mSongAdapter: RecommendAdapter
    private lateinit var mBanner: Banner
    override fun getLayoutId(): Int {
        return R.layout.fragment_recommend
    }

    override fun injectComponent() {
        DaggerMusicComponent.builder()
                .activityComponent(mActivityComponent)
                .musicMoudle(MusicMoudle(this))
                .build()
                .inject(this)

    }

    override fun initView() {
        mSongAdapter = RecommendAdapter(activity!!)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mSongAdapter)
        mRecyclerView.gridInit(context = activity!!, adapter = mLRecyclerViewAdapter)
        //添加head
        val headView = LayoutInflater.from(context).inflate(R.layout.layout_recommend_head, null, false)
        mBanner = headView.findViewById(R.id.mBannerRecommend)
        val tvDay = headView.findViewById<TextView>(R.id.tv_head_day)
        tvDay.text = String.format("%td", Date(System.currentTimeMillis()))
        mLRecyclerViewAdapter.addHeaderView(headView)
    }

    override fun initData() {
        mPresenter.getBanner()
        mPresenter.getSongList()
    }

    override fun setListener() {

    }

    override fun showBanner(bannerData: List<BannerData>) {
        val bannerImages = ArrayList<String>()
        for (bannerUrl in bannerData) {
            bannerImages.add(bannerUrl.randpic)
        }
        mBanner.player(null, bannerImages)
    }

    override fun showSongList(songDatas: List<SongData>) {
        mLRecyclerViewAdapter.setSpanSizeLookup { _, position ->
            val type = songDatas[position].getItemType()
            if (type == Concant.ITEM_TYPE_TITLE) {
                3
            } else {
                1
            }
        }
        mSongAdapter.updateData(songDatas)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }
}