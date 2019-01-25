package com.gfd.music.ui.activity

import android.graphics.PixelFormat
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gfd.common.ext.config
import com.gfd.common.ext.onDestroy
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.music.R
import com.gfd.music.adapter.MvCommentAdapter
import com.gfd.music.adapter.MvTagAdapter
import com.gfd.music.adapter.SimiMvAdapter
import com.gfd.music.entity.CommentData
import com.gfd.music.entity.MvData
import com.gfd.music.entity.MvDetailData
import com.gfd.music.injection.component.DaggerMvDetailComponent
import com.gfd.music.injection.module.MvDetialMoudle
import com.gfd.music.mvp.contract.MvDetailContract
import com.gfd.music.mvp.preesnter.MvDetailPresenter
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import com.xiao.nicevideoplayer.NiceTextureView
import com.xiao.nicevideoplayer.NiceVideoPlayerManager
import kotlinx.android.synthetic.main.music_activity_mv_detail.*
import kotlinx.android.synthetic.main.music_layout_mv_detail_top.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 16:25
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MvDetailActivity : BaseMvpActivity<MvDetailPresenter>(), MvDetailContract.View {

    private lateinit var mSimiMvAdapter: SimiMvAdapter
    private lateinit var mMvCommentAdapter: MvCommentAdapter
    private lateinit var mSimiMvDatas: List<MvData>
    private lateinit var mMvTagAdapter: MvTagAdapter
    private lateinit var niceTextureView: NiceTextureView
    private lateinit var mvId: String
    private lateinit var mvName: String
    private val tagDatas = arrayOf("慕涵盛华", "Kotlin-Android", "简书", "微信公众号", "Android行动派")

    override fun injectComponent() {
        DaggerMvDetailComponent.builder()
                .activityComponent(mActivityComponent)
                .mvDetialMoudle(MvDetialMoudle(this))
                .build()
                .inject(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.music_activity_mv_detail
    }

    override fun initView() {
        window.setFormat(PixelFormat.TRANSLUCENT)
        setWebView()
        loading.visibility = View.VISIBLE
        content.visibility = View.GONE
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
        //原来mvAPI已不能使用，这里改用webview播放mv
        //val parentView = NiceVideoPlayerManager.instance().currentNiceVideoPlayer.parent as ViewGroup
        //parentView.removeView(NiceVideoPlayerManager.instance().currentNiceVideoPlayer)
        //videoContainer.addView(NiceVideoPlayerManager.instance().currentNiceVideoPlayer)
        if (intent != null) {
            val mvUrl = intent.getStringExtra("mvUrl")
            mvId = intent.getStringExtra("mvId") ?: "1"
            mvName = intent.getStringExtra("mvName") ?: ""
            mWebView.loadUrl(mvUrl)
        }
    }

    override fun initData() {
        //原来的API已不能使用，这个用假数据代替
        mPresenter.getMvComment(mvId)
        //原来的API已不能使用，这个使用搜索相同的名字来代替相似的MV
        mPresenter.getSimiMv(mvName)
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
        mSimiMvAdapter.seOnClickListener(object : BaseAdapter.OnClickListener {
            override fun onClick(view: View, position: Int) {


            }

        })
    }

    override fun showMvDetail(data: MvDetailData) {
        //发布时间
        tvPublishTime.text = data.publishTime
        //播放数量
        tvPlayCount.text = "${data.playCount.toInt() / 1000}万"
        //标题
        tvMvTitile.text = data.name
        //原来的API已经不能使用，以下几个数据使用的是假数据
        //点赞数量
        tvMvDetailGood.text = "5569"
        //评论数量
        tvMvDetailComment.text = "4123"
        //分享数量
        tvMvDetailShare.text = "1135"
        //收藏数量
        tvMvDetailCollent.text = "2568"
    }

    override fun showSimiMv(datas: List<MvData>) {
        mSimiMvDatas = datas
        mSimiMvAdapter.updateData(datas)
        loading.visibility = View.GONE
        content.visibility = View.VISIBLE
    }

    override fun showMvComment(datas: List<CommentData>) {
        mMvCommentAdapter.updateData(datas)
        val a = 1

    }

    private fun setWebView() {
        window.setFormat(PixelFormat.TRANSLUCENT)
        val webSettings = mWebView.settings
        webSettings.config()
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                mWebView.loadUrl(url)
                return true
            }

        }
        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, "")
            }
        }
        //去掉qq浏览器的推广
        window.decorView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            val outView = ArrayList<View>()
            window.decorView.findViewsWithText(outView, "QQ浏览器", View.FIND_VIEWS_WITH_TEXT)
            if (outView.size > 0) {
                outView[0].visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        if (mWebView != null) {
            //销毁，防止内存泄漏，自定义的扩展方法
            mWebView.onDestroy()
        }
        super.onDestroy()
    }

}