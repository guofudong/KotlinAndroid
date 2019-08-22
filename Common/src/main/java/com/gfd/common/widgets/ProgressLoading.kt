package com.gfd.common.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.widget.ImageView
import com.gfd.common.R

/**
 * @Author : 郭富东
 * @Date ：2018/8/1 - 16:44
 * @Email：878749089@qq.com
 * @descriptio：加载对话框
 */
class ProgressLoading constructor(context: Context) : Dialog(context,R.style.LightProgressDialog) {

    private var animDrawable: AnimationDrawable? = null

    init {
        setContentView(R.layout.progress_dialog)
        setCancelable(true)
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //获取动画
        val loadingView = findViewById<ImageView>(R.id.iv_loading)
        animDrawable = loadingView.background as AnimationDrawable
    }

    /**
     * 显示对话框
     */
    fun showLoading() {
        if (!this.isShowing) {
            this.show()
        }
        animDrawable?.start()
    }

    /**
     * 隐藏对话框
     */
    fun hideLoading() {
        if (this.isShowing) {
            this.dismiss()
        }
        animDrawable?.stop()
    }
}