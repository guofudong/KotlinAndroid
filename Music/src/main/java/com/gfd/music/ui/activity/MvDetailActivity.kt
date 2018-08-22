package com.gfd.music.ui.activity

import android.graphics.SurfaceTexture
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Gravity
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.music.R
import com.gfd.music.adapter.MvCommentAdapter
import com.gfd.music.adapter.MvTagAdapter
import com.gfd.music.adapter.SimiMvAdapter
import com.gfd.music.entity.CommentData
import com.gfd.music.entity.MvData
import com.gfd.music.entity.MvDetailDto
import com.gfd.music.injection.component.DaggerMvDetailComponent
import com.gfd.music.injection.module.MvDetialMoudle
import com.gfd.music.mvp.contract.MvDetailContract
import com.gfd.music.mvp.preesnter.MvDetailPresenter
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.gson.Gson
import com.xiao.nicevideoplayer.NiceTextureView
import com.xiao.nicevideoplayer.NiceVideoPlayerManager
import kotlinx.android.synthetic.main.activity_mv_detail.*
import kotlinx.android.synthetic.main.layout_mv_detail_top.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 16:25
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MvDetailActivity : BaseMvpActivity<MvDetailPresenter>(), MvDetailContract.View {

    private lateinit var mMvDetailData: MvDetailDto.DataBean
    private lateinit var mSimiMvAdapter: SimiMvAdapter
    private lateinit var mMvCommentAdapter: MvCommentAdapter
    private lateinit var mSimiMvDatas: List<MvData>
    private lateinit var mMvTagAdapter: MvTagAdapter
    private lateinit var niceTextureView :NiceTextureView
    private lateinit var mvId: String
    private val tagDatas = arrayOf("慕涵盛华", "Kotlin-Android", "简书", "微信公众号", "Android行动派")

    override fun injectComponent() {
        DaggerMvDetailComponent.builder()
                .activityComponent(mActivityComponent)
                .mvDetialMoudle(MvDetialMoudle(this))
                .build()
                .inject(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_mv_detail
    }

    override fun initView() {
        loading.visibility = View.VISIBLE
        content.visibility = View.GONE
        val json = intent.getStringExtra("json")
        mMvDetailData = Gson().fromJson(json, MvDetailDto::class.java).data
        mvId = mMvDetailData.id.toString()
        //相似MV列表
        recommendList.layoutManager = LinearLayoutManager(this@MvDetailActivity, LinearLayoutManager.VERTICAL, false)
        mSimiMvAdapter = SimiMvAdapter(this@MvDetailActivity)
        recommendList.adapter = mSimiMvAdapter
        //mv评论列表
        commentList.layoutManager = LinearLayoutManager(this@MvDetailActivity, LinearLayoutManager.VERTICAL, false)
        mMvCommentAdapter = MvCommentAdapter(this)
        commentList.adapter = mMvCommentAdapter
        //mv标签列表
        val flexManager = FlexboxLayoutManager(this@MvDetailActivity)
        flexManager.flexDirection = FlexDirection.ROW
        flexManager.alignItems = AlignItems.STRETCH
        flexManager.flexWrap = FlexWrap.WRAP
        mvTagList.layoutManager = flexManager
        mMvTagAdapter = MvTagAdapter(this@MvDetailActivity)
        mMvTagAdapter.updateData(tagDatas.toList())
        mvTagList.adapter = mMvTagAdapter
        val parentView = NiceVideoPlayerManager.instance().currentNiceVideoPlayer.parent as ViewGroup
        parentView.removeView(NiceVideoPlayerManager.instance().currentNiceVideoPlayer)
        videoContainer.addView(NiceVideoPlayerManager.instance().currentNiceVideoPlayer)
    }

    override fun initData() {
        mPresenter.getMvComment(mvId)
        mPresenter.getSimiMv(mvId)
        mPresenter.getMvDetail(mvId)
    }

    override fun setListener() {
        mNestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
            if (topLayout.visibility == View.GONE && scrollY >= mvDetailTop.height) {
                topLayout.visibility = View.VISIBLE
            } else if (topLayout.visibility == View.VISIBLE && scrollY < mvDetailTop.height) {
                topLayout.visibility = View.GONE
            }
        })
        mSimiMvAdapter.seOnClickListener(object:BaseAdapter.OnClickListener{
            override fun onClick(view: View, position: Int) {


            }

        })
    }

    override fun showMvDetail() {
        //标题
        tvMvTitile.text = if (TextUtils.isEmpty(mMvDetailData.briefDesc)) {
            mMvDetailData.name
        } else {
            mMvDetailData.briefDesc
        }
        //发布时间
        tvPublishTime.text = mMvDetailData.publishTime
        //播放数量
        tvPlayCount.text = "${mMvDetailData.playCount / 1000}万"
        //点赞数量
        tvMvDetailGood.text = mMvDetailData.likeCount.toString()
        //评论数量
        tvMvDetailComment.text = mMvDetailData.commentCount.toString()
        //分享数量
        tvMvDetailShare.text = mMvDetailData.shareCount.toString()
        //收藏数量
        tvMvDetailCollent.text = mMvDetailData.subCount.toString()

    }

    override fun showSimiMv(datas: List<MvData>) {
        mSimiMvDatas = datas
        mSimiMvAdapter.updateData(datas)
        loading.visibility = View.GONE
        content.visibility = View.VISIBLE
    }

    override fun showMvComment(datas: List<CommentData>) {
        mMvCommentAdapter.updateData(datas)
    }

    override fun onDestroy() {
        super.onDestroy()
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer()
    }

}