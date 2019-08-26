package com.gfd.player.ui.activity

import android.graphics.PixelFormat
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.ext.config
import com.gfd.common.ext.init
import com.gfd.common.ext.onDestroy
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.common.ui.adapter.BaseAdapter
import com.gfd.common.utils.ToastUtils
import com.gfd.common.widgets.SpacesItemDecoration
import com.gfd.player.R
import com.gfd.player.adapter.EpisodeAdapter
import com.gfd.player.entity.VideoItemData
import com.gfd.player.injection.component.DaggerPlayComponent
import com.gfd.player.injection.moudle.PlayModule
import com.gfd.player.mvp.contract.PlayContract
import com.gfd.player.mvp.presenter.PlayPresenter
import com.gfd.provider.router.RouterPath
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.player_activity_play_webview.*


/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 18:19
 * @Email：878749089@qq.com
 * @description：
 */
@Suppress("DEPRECATION")
@Route(path = RouterPath.Player.PATH_PLAYER_WEB)
class PlayWebActivity : BaseMvpActivity<PlayPresenter>(), PlayContract.View {

    @Autowired(name = RouterPath.Player.KEY_PLAYER)
    @JvmField
    var analyzeUrl: String? = null

    @Autowired(name = RouterPath.Player.KEY_NAME)
    @JvmField
    var videoName: String? = null

    /** 剧集列表列数*/
    private val SPAN_EPISODE = 6
    private lateinit var episodeAdapter: EpisodeAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var mData: List<VideoItemData>
    private lateinit var mTvPlot: TextView

    override fun isSetPaddingTop(): Boolean = false

    override fun isSetStateView(): Boolean = true

    override fun getLayoutId(): Int = R.layout.player_activity_play_webview

    override fun injectComponent() {
        DaggerPlayComponent.builder()
                .activityComponent(mActivityComponent)
                .playModule(PlayModule(this))
                .build()
                .inject(this)

    }

    override fun initView() {
        showLoading()
        window.setFormat(PixelFormat.TRANSLUCENT)
        setWebView()
        ARouter.getInstance().inject(this)
        //设置剧集列表属性
        episodeList.layoutManager = GridLayoutManager(this@PlayWebActivity, SPAN_EPISODE)
        //设置分割线
        val spacing = resources.getDimensionPixelSize(R.dimen.dp_4)
        episodeList.addItemDecoration(SpacesItemDecoration.newInstance(
                spacing, spacing, SPAN_EPISODE, resources.getColor(R.color.colorItemDecoration)))
        episodeAdapter = EpisodeAdapter(this@PlayWebActivity)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(episodeAdapter)
        episodeList.adapter = mLRecyclerViewAdapter
        episodeList.setLoadMoreEnabled(false)
        episodeList.setPullRefreshEnabled(false)
        val headView = LayoutInflater.from(this@PlayWebActivity).inflate(R.layout.player_head_playweb, null, false)
        mTvPlot = headView.findViewById(R.id.tvPlot)
        mLRecyclerViewAdapter.addHeaderView(headView)
    }

    private fun setWebView() {
        window.setFormat(PixelFormat.TRANSLUCENT)
        val webSettings = mWebView.settings
        webSettings.config()
        mWebView.init(videoName)
        //去掉qq浏览器的推广
        window.decorView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            val outView = ArrayList<View>()
            window.decorView.findViewsWithText(outView, "QQ浏览器", View.FIND_VIEWS_WITH_TEXT)
            if (outView.size > 0) {
                outView[0].visibility = View.GONE
            }
        }
    }

    override fun initData() {
        analyzeUrl?.let { mPresenter.getWebVideoUrl(it) }
    }

    override fun setListener() {
        episodeAdapter.seOnClickListener(object : BaseAdapter.OnClickListener {
            override fun onClick(view: View, position: Int) {
                episodeAdapter.setSelect(position)
                mLRecyclerViewAdapter.notifyDataSetChanged()
                mWebView.loadUrl(mData[position].videoUrl)
            }
        })
    }


    override fun playVideo(videoUrl: String) {}

    override fun playWebVideo(data: List<VideoItemData>) {
        if (data.isEmpty()) {
            ToastUtils.instance.showToast("视频还没上映,请等待")
            finish()
        } else {
            mData = data
            mWebView.loadUrl(data[0].videoUrl)
            episodeAdapter.addAll(mData)
        }
    }

    override fun showVideoPlot(plotText: String) {
        mTvPlot.text = plotText
    }

    override fun onDestroy() {
        if (mWebView != null) {
            //销毁，防止内存泄漏，自定义的扩展方法
            mWebView.onDestroy()
        }
        super.onDestroy()
    }
}