package com.gfd.player.service.impl

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.webkit.JavascriptInterface
import com.gfd.player.entity.Live
import com.gfd.player.entity.LiveDataDto
import com.gfd.player.service.LiveApiService
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import org.jsoup.Jsoup
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/21 - 14:16
 * @Email：878749089@qq.com
 * @description：
 */
@Suppress("DEPRECATION")
class LiveApiServiceImpl @Inject constructor() : LiveApiService {

    private var wb: WebView? = null
    private lateinit var mCallBack: LiveApiService.IGetLiveCallback

    /**
     * 获取电视直播所有的电视台
     * @param context Context
     * @param callback LiveApiService.IGetLiveCallback
     */
    override fun getLiveInfo(context: Context, callback: LiveApiService.IGetLiveCallback) {
        mCallBack = callback
        val assetManager = context.assets
        val inputStream = assetManager.open("live.txt")
        val byteArray = ByteArray(inputStream.available())
        inputStream.read(byteArray)
        inputStream.close()
        val json = String(byteArray)
        val data = Gson().fromJson(json, LiveDataDto::class.java)
        if (data.lives != null) {
            callback.onLive(data.lives)
        }
    }

    /**
     * 获取电视台对应的节目时刻表
     * @param context Context
     * @param url String
     * @param callback LiveApiService.IGetLiveCallback
     */
    override fun getPlayUrl(context: Context, url: String, callback: LiveApiService.IGetLiveCallback) {
        Thread(Runnable {
            val doc = Jsoup.connect(url).get()
            Logger.e(doc.html())
            val playUrl = doc.getElementById("share-input1").attr("value")
            (context as Activity).runOnUiThread {
                callback.onPlayUrl(playUrl)
            }
        }).start()
    }

    /**
     * 设置WebView
     * @param context Context
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(context: Context, url: String) {
        (context as Activity).runOnUiThread {
            wb = WebView(context)
            val settings = wb?.settings
            // 此方法需要启用JavaScript
            settings?.javaScriptEnabled = true
            // 把刚才的接口类注册到名为HTMLOUT的JavaScript接口
            wb?.addJavascriptInterface(MyJavaScriptInterface(), "HTMLOUT")
            // 必须在loadUrl之前设置WebViewClient
            wb?.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    // 这里可以过滤一下url
                    wb?.loadUrl("javascript:window.HTMLOUT.processHTML(document.documentElement.outerHTML);")
                }
            }
            // 开始加载网址
            wb?.loadUrl(url)
        }
    }

    inner class MyJavaScriptInterface {

        @JavascriptInterface
        fun processHTML(html: String) {
            val doc = Jsoup.parse(html)
            val liveData = ArrayList<Live>()
            doc.getElementsByClass("tv-item").forEach {
             //   val name = it.text()
              //  val link = it.attr("_href")
              //  val img = it.selectFirst("img").attr("src")
            }
            mCallBack.onLive(liveData)
            wb = null
        }
    }

}