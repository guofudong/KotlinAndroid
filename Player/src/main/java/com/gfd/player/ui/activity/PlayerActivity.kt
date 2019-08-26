package com.gfd.player.ui.activity

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.PixelFormat
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.ext.init
import com.gfd.common.ext.onDestroy
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.player.R
import com.gfd.player.entity.VideoItemData
import com.gfd.player.injection.component.DaggerPlayComponent
import com.gfd.player.injection.moudle.PlayModule
import com.gfd.player.mvp.contract.PlayContract
import com.gfd.player.mvp.presenter.PlayPresenter
import com.gfd.provider.router.RouterPath
import kotlinx.android.synthetic.main.player_activity_player.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/4 - 16:37
 * @Email：878749089@qq.com
 * @description：视频播放页面-由于视频源问题改为WebView播放
 */
@Suppress("DEPRECATION")
@Route(path = RouterPath.Player.PATH_PLAYER)
class PlayerActivity : BaseMvpActivity<PlayPresenter>(), PlayContract.View {

    @Autowired(name = RouterPath.Player.KEY_PLAYER)
    @JvmField
    var analyzeUrl: String? = null

    @Autowired(name = RouterPath.Player.KEY_IMAGE)
    @JvmField
    var videoImg: String? = null

    @Autowired(name = RouterPath.Player.KEY_NAME)
    @JvmField
    var videoName: String? = null

    override fun isSetPaddingTop(): Boolean = false

    override fun isSetStateView(): Boolean = true

    override fun getLayoutId(): Int = R.layout.player_activity_player

    override fun injectComponent() {
        DaggerPlayComponent.builder()
                .activityComponent(mActivityComponent)
                .playModule(PlayModule(this))
                .build()
                .inject(this)
        ARouter.getInstance().inject(this)

    }

    override fun initView() {
        setWebView()
    }

    override fun initData() {
        if (analyzeUrl != null) {
            mPresenter.getVideoUrl(analyzeUrl!!)
        }
    }

    override fun playVideo(videoUrl: String) {
        mWebView.loadUrl(videoUrl)
    }

    override fun playWebVideo(data: List<VideoItemData>) {}

    override fun showVideoPlot(plotText: String) {
        tvPlot.text = plotText
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        //去掉qq浏览器的推广
        window.decorView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            val outView = ArrayList<View>()
            window.decorView.findViewsWithText(outView, "QQ浏览器", View.FIND_VIEWS_WITH_TEXT)
            if (outView.size > 0) {
                outView[0].visibility = View.GONE
            }
        }
        window.setFormat(PixelFormat.TRANSLUCENT)
        mWebView.init(videoName)
    }

    override fun onDestroy() {
        super.onDestroy()
        mWebView.onDestroy()
    }

    override fun onBackPressed() {
        if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            finish()
        } else {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }
}