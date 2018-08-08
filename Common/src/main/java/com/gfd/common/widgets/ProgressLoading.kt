package com.gfd.common.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.gfd.common.R

/**
 * @Author : 郭富东
 * @Date ：2018/8/1 - 16:44
 * @Email：878749089@qq.com
 * @descriptio：加载对话框
 */
class ProgressLoading private constructor(context: Context, them: Int) : Dialog(context, them) {

    companion object {
        private lateinit var mDialog: ProgressLoading
        private var animDrawable: AnimationDrawable? = null

        /**
         * 创建对话框
         */
        fun create(context: Context): ProgressLoading {
            mDialog = ProgressLoading(context, R.style.LightProgressDialog)
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER
            val lp = mDialog.window.attributes
            //设置Dialog背景层的透明度
            lp.dimAmount = 0.2f
            mDialog.window.attributes = lp
            //获取动画
            val loadingView = mDialog.findViewById<ImageView>(R.id.iv_loading)
            animDrawable = loadingView.background as AnimationDrawable
            return mDialog
        }
    }

    /**
     * 显示对话框
     */
    fun showLoading() {
        if (!mDialog.isShowing) {
            mDialog.show()
        }
        animDrawable?.start()
    }

    /**
     * 隐藏对话框
     */
    fun hideLoading() {
        if (mDialog.isShowing) {
            mDialog.dismiss()
        }
        animDrawable?.stop()
    }

}