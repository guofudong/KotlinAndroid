package com.gfd.video

import android.content.Intent
import android.os.CountDownTimer
import android.view.Window
import android.view.WindowManager
import com.gfd.common.ui.activity.BaseActivity


/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 10:28
 * @Email：878749089@qq.com
 * @descriptio：
 */
class SplashActivity : BaseActivity() {

    private val TIME_COUNTDOEN: Long = 3 * 1000
    override fun initView() {

    }

    override fun initData() {
        object : CountDownTimer(1000, TIME_COUNTDOEN) {
            override fun onFinish() {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left)
                finish()
            }

            override fun onTick(p0: Long) {
            }

        }.start()
    }

    override fun getLayoutId(): Int {
        //去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //去除状态栏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        return R.layout.activity_splash
    }

}
