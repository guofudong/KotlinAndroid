package com.gfd.crosstalk.ui.activity

import android.graphics.Bitmap
import android.graphics.PixelFormat
import android.view.View
import com.gfd.common.ext.config
import com.gfd.common.ext.onDestroy
import com.gfd.common.ui.activity.BaseActivity
import com.gfd.crosstalk.R
import com.orhanobut.logger.Logger
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.crosstalk_webview.*

class WebViewActivity : BaseActivity() {

    private var videoName = ""
    private var videoUrl = ""
    private var mIsPageLoading: Boolean = false

    override fun initView() {
        showLoading()
        window.setFormat(PixelFormat.TRANSLUCENT)
        if (intent != null) {
            videoUrl = intent.getStringExtra("videoUrl")
            videoName = intent.getStringExtra("videoName")
            Logger.e("相声播放地址：$videoUrl")
        }
        setWebView()
    }

    override fun initData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.crosstalk_webview
    }

    override fun setListener() {
    }

    private fun setWebView() {
        window.setFormat(PixelFormat.TRANSLUCENT)
        val webSettings = mWebView.settings
        webSettings.config()
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                if (mIsPageLoading) {
                    return false
                }
                if (url != null && url.startsWith("http")) {
                    mWebView.loadUrl(url)
                    return true
                }
                return true
            }

            override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
                super.onPageStarted(p0, p1, p2)
                mIsPageLoading = true
            }

            override fun onPageFinished(p0: WebView?, url: String?) {
                super.onPageFinished(p0, url)
                mIsPageLoading = false
                showContent()
            }
        }
        mWebView.webChromeClient = object : WebChromeClient() {

            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, videoName)
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
        mWebView.loadUrl(videoUrl)
    }

    override fun onDestroy() {
        if (mWebView != null) {
            //销毁，防止内存泄漏，自定义的扩展方法
            mWebView.onDestroy()
        }
        super.onDestroy()
    }
}