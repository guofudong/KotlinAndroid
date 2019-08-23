@file:Suppress("UNCHECKED_CAST")

package com.gfd.common.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.annotation.DrawableRes
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gfd.common.dsl.EditorActionDSL
import com.gfd.common.utils.SoftKeyboardUtils
import com.tencent.smtt.sdk.WebChromeClient

/**
 * @Author ：郭富东
 * @Date：2019/6/28:10:27
 * @Email：878749089@qq.com
 * @description：View相关扩展
 */


/** 重复点击延时时间*/
const val DELAY_TIME = 2000L


/***
 * 带延迟过滤的点击事件View扩展
 * @param time Long 延迟时间，默认2000毫秒
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.clickWithTrigger(time: Long = DELAY_TIME, block: (T) -> Unit) {
    triggerDelay = time
    setOnClickListener {
        if (clickEnable()) {
            block(it as T)
        }
    }
}

private var <T : View> T.triggerLastTime: Long
    //该数字是View中定义好的不能更改其值，否则抛出异常：The key must be an application-specific resource id.
    get() = if (getTag(1123460103) != null) getTag(1123460103) as Long else -(DELAY_TIME + 1)
    set(value) {
        setTag(1123460103, value)
    }

private var <T : View> T.triggerDelay: Long
    get() = if (getTag(1123461123) != null) getTag(1123461123) as Long else DELAY_TIME
    set(value) {
        setTag(1123461123, value)
    }

private fun <T : View> T.clickEnable(): Boolean {
    var flag = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - triggerLastTime >= triggerDelay) {
        flag = true
    }
    triggerLastTime = currentClickTime
    return flag
}

//ImageView扩展

/**
 * 加载网络图片
 * @receiver ImageView
 * @param context Context
 * @param url String
 */
fun ImageView.loadImage(context: Context, url: String) {
    Glide.with(context).load(url).into(this)
}

/**
 *  加载网络图片
 * @receiver ImageView
 * @param context Context
 * @param url String
 * @param error Int
 */
fun ImageView.loadImage(context: Context, url: String, @DrawableRes error: Int) {
    Glide.with(context).load(url).error(error).into(this)
}

/**
 * 扩展WebView相关设置
 * @receiver WebView
 */
fun WebView.init(): WebView {
    this.apply {
        this.settings.apply {
            this.javaScriptEnabled = true
            this.useWideViewPort = true
            this.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            this.loadWithOverviewMode = true
        }
        this.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.url.toString())
                }
                return true
            }
        }
    }
    return this
}

/**
 * 获取EditText的文本
 * @receiver EditText
 * @return String
 */
fun EditText.text(): String {
    return this.text.toString()
}

/**
 * EditText按键监听
 * @receiver EditText
 * @param dsl EditorActionDSL.() -> Unit：DSL接收者
 */
fun EditText.setOnActionListener(dsl: EditorActionDSL.() -> Unit) {
    val action = EditorActionDSL().apply(dsl)
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            SoftKeyboardUtils.closeInoutDecorView(context as Activity)
            action.onSearch?.invoke(this.text())
            this.setText("")
        }
        false
    }
}

/**
 * 视频播放页面-WebView初始化方法
 * @receiver com.tencent.smtt.sdk.WebView
 * @param videoName String
 */
@SuppressLint("SetJavaScriptEnabled")
fun com.tencent.smtt.sdk.WebView.init(videoName: String?) {
    val webSettings = this.settings
    webSettings.javaScriptEnabled = true
    webSettings.useWideViewPort = true // 关键点
    this.webViewClient = object : com.tencent.smtt.sdk.WebViewClient() {
        override fun shouldOverrideUrlLoading(view: com.tencent.smtt.sdk.WebView, url: String): Boolean {
            this@init.loadUrl(url)
            return true
        }
    }
    this.webChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(view: com.tencent.smtt.sdk.WebView, title: String) {
            super.onReceivedTitle(view, videoName)
        }
    }
}