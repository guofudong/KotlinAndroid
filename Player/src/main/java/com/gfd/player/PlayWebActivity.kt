package com.gfd.player

import android.graphics.PixelFormat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.widgets.SpacesItemDecoration
import com.gfd.player.adapter.EpisodeAdapter
import com.gfd.player.entity.VideoItemData
import com.gfd.player.injection.component.DaggerPlayComponent
import com.gfd.player.injection.moudle.PlayMoudle
import com.gfd.player.mvp.PlayContract
import com.gfd.player.mvp.presenter.PlayPresenter
import com.gfd.provider.router.RouterPath
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_play_webview.*


/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 18:19
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Route(path = RouterPath.Player.PATH_PLAYER_WEB)
class PlayWebActivity : BaseMvpActivity<PlayPresenter>(), PlayContract.View {

    @Autowired(name = RouterPath.Player.KEY_PLAYER)
    @JvmField
    var analyzeUrl: String? = null
    /** 剧集列表列数*/
    private val SPAN_EPISODE = 6
    private lateinit var episodeAdapter : EpisodeAdapter
    private lateinit var mLRecyclerViewAdapter : LRecyclerViewAdapter
    private lateinit var mDatas : List<VideoItemData>
    private lateinit var mTvPlot :TextView


    override fun getLayoutId(): Int {
        return R.layout.activity_play_webview
    }

    override fun injectComponent() {
        DaggerPlayComponent.builder()
                .activityComponent(mActivityComponent)
                .playMoudle(PlayMoudle(this))
                .build()
                .inject(this)

    }

    override fun initView() {
        setWebView()
        ARouter.getInstance().inject(this)
        //设置剧集列表属性
        episodeList.layoutManager = GridLayoutManager(this@PlayWebActivity,SPAN_EPISODE)
        //设置分割线
        val spacing = resources.getDimensionPixelSize(R.dimen.dp_4)
        episodeList.addItemDecoration(SpacesItemDecoration.newInstance(
                spacing, spacing, SPAN_EPISODE, resources.getColor(R.color.colorItemDecoration)))
        episodeAdapter = EpisodeAdapter(this@PlayWebActivity)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(episodeAdapter)
        episodeList.adapter = mLRecyclerViewAdapter
        episodeList.setLoadMoreEnabled(false)
        episodeList.setPullRefreshEnabled(false)
        var headView = LayoutInflater.from(this@PlayWebActivity).inflate(R.layout.head_playweb,null,false)
        mTvPlot = headView.findViewById<TextView>(R.id.tvPlot)
        mLRecyclerViewAdapter.addHeaderView(headView)
    }

    private fun setWebView() {
        window.setFormat(PixelFormat.TRANSLUCENT)
        val webSettings = mWebView.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true // 关键点
        webSettings.allowFileAccess = true // 允许访问文件
        webSettings.setSupportZoom(true) // 支持缩放
        webSettings.loadWithOverviewMode = true
    }

    override fun initData() {
        analyzeUrl?.let { mPresenter.getWebVideoUrl(it) }
    }

    override fun setListener() {
        episodeAdapter.seOnClickListener(object :BaseAdapter.OnClickListener{
            override fun onClick(view: View, positon: Int) {
                episodeAdapter.setSelect(positon)
                mLRecyclerViewAdapter.notifyDataSetChanged()
                mWebView.loadUrl(mDatas[positon].videoUrl)
            }
        })
    }


    override fun playVideo(videoUrl: String) {
    }

    override fun playWebVideo(datas: List<VideoItemData>) {
        mDatas = datas
        mWebView.loadUrl(datas[0].videoUrl)
        episodeAdapter.addAll(mDatas)
    }

    override fun showVideoPlot(plotText: String) {
        mTvPlot.text = plotText
    }
}