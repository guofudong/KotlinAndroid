package com.gfd.video.ui

import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.support.annotation.RequiresApi
import android.view.View
import com.gfd.common.ui.activity.BaseActivity
import com.gfd.video.R

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 10:28
 * @Email：878749089@qq.com
 * @description：app-启动页面
 */
class SplashActivity : BaseActivity() {

    companion object {
        private const val TIME_COUNTDOWN: Long = 3 * 1000
    }

    override fun isSetPaddingTop(): Boolean = false

    override fun initView() {}

    override fun initData() {
        //开启倒计时
        object : CountDownTimer(1000, TIME_COUNTDOWN) {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onFinish() {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onTick(p0: Long) {
            }

        }.start()
    }

    override fun getLayoutId(): Int {
        hideBottomUIMenu()
        return R.layout.app_activity_splash
    }

    /**
     * 隐藏底部虚拟键并全屏
     */
    private fun hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT in 12..18) {
            val view = this.window.decorView
            view.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

            decorView.systemUiVisibility = uiOptions
        }
    }

}
