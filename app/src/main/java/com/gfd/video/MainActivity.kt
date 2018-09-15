package com.gfd.video

import android.os.Build
import android.view.View
import android.view.WindowManager
import com.gfd.common.ui.activity.BaseActivity
import com.gfd.common.ui.fragment.BaseFragment
import com.gfd.crosstalk.CrosstalkFragment
import com.gfd.home.ui.fragment.HomeFragment
import com.gfd.music.common.PlayUtils
import com.gfd.music.ui.fragment.MusicFragment
import com.gfd.player.ui.fragment.LiveFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 15:22
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MainActivity : BaseActivity() {

    private val mHomeFragment: HomeFragment by lazy { HomeFragment() }
    private val mMusicFragment: MusicFragment by lazy { MusicFragment() }
    private val mLiveFragment: LiveFragment by lazy { LiveFragment() }
    private val mCrosstalkFragment: CrosstalkFragment by lazy { CrosstalkFragment() }

    private val mStack = Stack<BaseFragment>()

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initFragment()
    }

    /** 初始化fragment*/
    private fun initFragment() {
        val bt = supportFragmentManager.beginTransaction()
        bt.add(R.id.rootLay, mHomeFragment)
        bt.add(R.id.rootLay, mMusicFragment)
        bt.add(R.id.rootLay, mLiveFragment)
        bt.add(R.id.rootLay, mCrosstalkFragment)
        mStack.add(mHomeFragment)
        mStack.add(mMusicFragment)
        mStack.add(mLiveFragment)
        mStack.add(mCrosstalkFragment)
        bt.commit()
    }

    override fun initOperate() {
        setStatusBar()
    }

    /**
     * 切换fragment显示
     * @param position Int
     */
    private fun changeFragment(position: Int) {
        val bt = supportFragmentManager.beginTransaction()
        mStack.forEach {
            bt.hide(it)
        }
        bt.show(mStack[position])
        bt.commit()
    }

    override fun initData() {
    }

    override fun setListener() {
        bottomBar.setOnTabSelectListener {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏
            PlayUtils.release()
            when (it) {
                R.id.tab_home -> {//首页
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    }
                    changeFragment(0)
                }
                R.id.tab_music -> {//音乐
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    }
                    changeFragment(1)
                }
                R.id.tab_play -> {//直播
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    }
                    changeFragment(2)
                }
                R.id.tab_mine -> {//相声
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    }
                    changeFragment(3)
                }
            }
        }
    }

    override fun onBackPressed() {
        if (mLiveFragment.onBackPressed()) {
            return
        }
        if (PlayUtils.onBackPressd()) {
            return
        }

        super.onBackPressed()
    }

}

