package com.gfd.player.ui.activity

import android.graphics.PixelFormat
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.ui.activity.BaseActivity
import com.gfd.player.R
import com.gfd.provider.router.RouterPath
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.player_activity_play_webview.*


/**
 * @Author : 郭富东
 * @Date ：2018/8/6 - 18:19
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Route(path = RouterPath.Player.PATH_PLAYER_MV)
class PlayMvActivity : BaseActivity() {

    @Autowired(name = RouterPath.Player.KEY_PLAYER)
    @JvmField
    var mvurl: String? = null

    @Autowired(name = RouterPath.Player.KEY_NAME)
    @JvmField
    var videoName: String? = null


    override fun getLayoutId(): Int {
        return R.layout.player_activity_play_webview
    }

    override fun initView() {
        setWebView()
        ARouter.getInstance().inject(this)
    }

    override fun initData() {
        mWebView.loadUrl(mvurl)
    }

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
        val webSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true // 关键点
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                mWebView.loadUrl(url)
                return true
            }

            override fun onPageFinished(p0: WebView?, p1: String?) {
                super.onPageFinished(p0, p1)
            }
        }
        mWebView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, videoName)
            }
        }
    }
}