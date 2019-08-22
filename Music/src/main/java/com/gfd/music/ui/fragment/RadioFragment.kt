package com.gfd.music.ui.fragment

import android.view.LayoutInflater
import com.gfd.common.ext.gridInit
import com.gfd.common.ext.player
import com.gfd.common.ui.fragment.BaseMvpFragment
import com.gfd.music.R
import com.gfd.music.adapter.RadioAdapter
import com.gfd.music.common.Constant
import com.gfd.music.entity.BannerData
import com.gfd.music.entity.RadioData
import com.gfd.music.entity.SongData
import com.gfd.music.injection.component.DaggerRadioComponent
import com.gfd.music.injection.module.MusicModule
import com.gfd.music.mvp.contract.RecommendContract
import com.gfd.music.mvp.preesnter.RecommendPresenter
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.music_fragment_recommend.*
import java.util.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:48
 * @Email：878749089@qq.com
 * @description：电台Fragment
 */
class RadioFragment : BaseMvpFragment<RecommendPresenter>(), RecommendContract.View {


    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var mRadioAdapter: RadioAdapter
    private lateinit var mBanner: Banner
    private lateinit var mRadioData: List<RadioData>

    override fun getLayoutId(): Int = R.layout.music_fragment_radio

    override fun injectComponent() {
        DaggerRadioComponent.builder()
                .activityComponent(mActivityComponent)
                .musicModule(MusicModule(this))
                .build()
                .inject(this)

    }

    override fun initView() {
        mRadioAdapter = RadioAdapter(activity!!)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mRadioAdapter)
        mRecyclerView.gridInit(context = activity!!, adapter = mLRecyclerViewAdapter)
        //添加head
        val headView = LayoutInflater.from(context).inflate(R.layout.music_layout_recommend_head_radio, null, false)
        val footerView = LayoutInflater.from(context).inflate(R.layout.music_layout_recommend_footer_radio, null, false)
        mBanner = headView.findViewById(R.id.mBannerRecommend)
        mLRecyclerViewAdapter.addHeaderView(headView)
        mLRecyclerViewAdapter.addFooterView(footerView)
    }

    override fun initData() {
        mPresenter.getBanner()
        mPresenter.getRadioData()
    }

    override fun setListener() {
        mLRecyclerViewAdapter.setOnItemClickListener { _, _ ->
            /*  val radioData = mRadioData[position]
              val type = radioData.getItemType()
              if (Constant.ITEM_TYPE_IMG == type || type == Constant.ITEM_TYPE_LIST) {//点击内容
                  val intent = Intent(activity, SongListDetailActivity::class.java)
                  intent.putExtra("id", radioData.id.toString())
                  intent.putExtra("pic_big", radioData.logo)
                  intent.putExtra("file_duration", radioData.count)
                  intent.putExtra("color", radioData.color)
                  startActivity(intent)
              }*/
        }
    }

    override fun showBanner(bannerData: List<BannerData>) {
        val bannerImages = ArrayList<String>()
        for (bannerUrl in bannerData) {
            bannerImages.add(bannerUrl.randpic)
        }
        mBanner.player(null, bannerImages)
    }

    override fun showSongList(songDatas: List<SongData>) {

    }

    override fun showRadioData(radioDatas: List<RadioData>) {
        mRadioData = radioDatas
        mLRecyclerViewAdapter.setSpanSizeLookup { _, position ->
            val type = mRadioData[position].getItemType()
            if (type == Constant.ITEM_TYPE_TITLE || type == Constant.ITEM_TYPE_LIST) {
                3
            } else {
                1
            }
        }
        mRadioAdapter.updateData(mRadioData)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }
}