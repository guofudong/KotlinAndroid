package com.gfd.music.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.music.R
import com.gfd.music.adapter.MvCommentAdapter
import com.gfd.music.adapter.SimiMvAdapter
import com.gfd.music.entity.CommentData
import com.gfd.music.entity.MvData
import com.gfd.music.entity.MvDetailDto
import com.gfd.music.injection.component.DaggerMvDetailComponent
import com.gfd.music.injection.module.MvDetialMoudle
import com.gfd.music.mvp.contract.MvDetailContract
import com.gfd.music.mvp.preesnter.MvDetailPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_mv_detail.*
import kotlinx.android.synthetic.main.layout_mv_detail_top.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 16:25
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MvDetailActivity:BaseMvpActivity<MvDetailPresenter>(),MvDetailContract.View{

    private lateinit var mMvDetailData:MvDetailDto.DataBean
    private lateinit var mSimiMvAdapter:SimiMvAdapter
    private lateinit var mMvCommentAdapter:MvCommentAdapter
    private lateinit var mvId:String
    override fun injectComponent() {
        DaggerMvDetailComponent.builder()
                .activityComponent(mActivityComponent)
                .mvDetialMoudle(MvDetialMoudle(this))
                .build()
                .inject(this)
    }

    override fun initView() {
        val json = intent.getStringExtra("json")
        mMvDetailData = Gson().fromJson(json, MvDetailDto::class.java).data
        mvId = mMvDetailData.id.toString()
        //相似MV列表
        recommendList.layoutManager = LinearLayoutManager(this@MvDetailActivity,LinearLayoutManager.VERTICAL,false)
        mSimiMvAdapter = SimiMvAdapter(this@MvDetailActivity)
        recommendList.adapter = mSimiMvAdapter
        //mv评论列表
        commentList.layoutManager = LinearLayoutManager(this@MvDetailActivity,LinearLayoutManager.VERTICAL,false)
        mMvCommentAdapter = MvCommentAdapter(this)
        commentList.adapter = mMvCommentAdapter
    }

    override fun initData() {
        mPresenter.getMvComment(mvId)
        mPresenter.getSimiMv(mvId)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_mv_detail
    }

    override fun showMvDetail() {
        //标题
        tvMvTitile.text = if(TextUtils.isEmpty(mMvDetailData.briefDesc)) {
            mMvDetailData.name
        }else{
            mMvDetailData.briefDesc
        }
        //发布时间
        tvPublishTime.text = mMvDetailData.publishTime
        //播放数量
        tvPlayCount.text =  "${mMvDetailData.playCount / 1000}万"
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
        mSimiMvAdapter.updateData(datas)
    }

    override fun showMvComment(datas: List<CommentData>) {
        mMvCommentAdapter.updateData(datas)
    }

}