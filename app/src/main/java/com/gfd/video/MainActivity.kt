package com.gfd.video

import com.gfd.common.ui.activity.BaseActivity
import com.gfd.home.ui.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*


/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 15:22
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.rootLay, HomeFragment())
        transaction.commit()
        bottomBar.setOnTabSelectListener {
            if (it == R.id.tab_home) {//首页

            } else if (it == R.id.tab_music) {//音乐

            } else if (it == R.id.tab_play) {//直播

            } else if (it == R.id.tab_mine) {//我的

            }

        }
    }

    override fun initData() {
    }

}

