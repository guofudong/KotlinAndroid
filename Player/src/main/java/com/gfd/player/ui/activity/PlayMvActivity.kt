package com.gfd.player.ui.activity

import android.annotation.SuppressLint
import android.graphics.PixelFormat
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.ext.init
import com.gfd.common.ui.activity.BaseActivity
import com.gfd.player.R
import com.gfd.provider.router.RouterPath
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.player_activity_play_webview.*
import kotlinx.android.synthetic.main.player_activity_play_webview.mWebView
import kotlinx.android.synthetic.main.player_activity_player.*


/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 18:19
 * @Email：878749089@qq.com
 * @description：
 */
@Suppress("DEPRECATION")
@Route(path = RouterPath.Player.PATH_PLAYER_MV)
class PlayMvActivity : BaseActivity() {

    @Autowired(name = RouterPath.Player.KEY_PLAYER)
    @JvmField
    var mvUrl: String? = null

    @Autowired(name = RouterPath.Player.KEY_NAME)
    @JvmField
    var videoName: String? = null

    override fun isSetPaddingTop(): Boolean = false

    override fun getLayoutId(): Int = R.layout.player_activity_play_webview

    override fun initView() {
        setWebView()
        ARouter.getInstance().inject(this)
    }

    override fun initData() {
        mWebView.loadUrl(mvUrl)
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
}