package com.gfd.crosstalk.ui.activity

import android.graphics.PixelFormat
import android.view.View
import com.gfd.common.ext.config
import com.gfd.common.ext.init
import com.gfd.common.ext.onDestroy
import com.gfd.common.ui.activity.BaseActivity
import com.gfd.crosstalk.R
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.crosstalk_webview.*

class WebViewActivity : BaseActivity() {

    private var videoName = ""
    private var videoUrl = ""

    override fun getLayoutId(): Int = R.layout.crosstalk_webview

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

    override fun setListener() {
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
            if (outView.size > 0) outView[0].visibility = View.GONE
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