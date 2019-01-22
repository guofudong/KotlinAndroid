package com.gfd.video

import android.content.Intent
import android.os.Build
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import com.gfd.common.ui.activity.BaseActivity
import com.gfd.common.ui.fragment.BaseFragment
import com.gfd.provider.router.component.Router
import com.gfd.provider.router.service.CrosstalkService
import com.gfd.provider.router.service.HomeService
import com.gfd.provider.router.service.LiveService
import com.gfd.provider.router.service.MusicService
import kotlinx.android.synthetic.main.app_activity_main.*
import java.util.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 15:22
 * @Email：878749089@qq.com
 * @descriptio：应用主页面：主要加载4个业务模块对应的Fragment
 */
class MainActivity : BaseActivity() {

    private lateinit var mHomeFragment: BaseFragment
    private lateinit var mMusicFragment: BaseFragment
    private lateinit var mLiveFragment: BaseFragment
    private lateinit var mCrosstalkFragment: BaseFragment
    private val STATUSBAR_POSITION = 1

    private val mStack = Stack<BaseFragment>()

    override fun initOperate() {
        setStatusBar()
    }

    override fun getLayoutId(): Int {
        return R.layout.app_activity_main
    }

    override fun initView() {
        initFragment()
    }

    override fun initData() {
    }

    override fun setListener() {
        bottomBar.setOnTabSelectListener {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏
            when (it) {
                R.id.tab_home -> {//首页
                    changeFragment(0)
                }
                R.id.tab_music -> {//音乐
                    changeFragment(1)
                }
                R.id.tab_play -> {//直播
                    changeFragment(2)
                }
                R.id.tab_mine -> {//相声
                    changeFragment(3)
                }
            }
        }
    }

    /** 初始化fragment*/
    private fun initFragment() {
        val router = Router.instance
        val musicService = router.getService(MusicService::class.simpleName)
        val liveService = router.getService(LiveService::class.simpleName)
        val crosstalkService = router.getService(CrosstalkService::class.simpleName)
        val homeService = router.getService(HomeService::class.simpleName)
        val bt = supportFragmentManager.beginTransaction()
        if (homeService != null) {
            mHomeFragment = (homeService as HomeService).getHomeFragment()
            bt.add(R.id.rootLay, mHomeFragment)
            mStack.add(mHomeFragment)
        }
        if (homeService != null) {
            mMusicFragment = (musicService as MusicService).getMusicFragment()
            bt.add(R.id.rootLay, mMusicFragment)
        }
        if (homeService != null) {
            mLiveFragment = (liveService as LiveService).getLiveFragment()
            bt.add(R.id.rootLay, mLiveFragment)
            mStack.add(mLiveFragment)
        }
        if (homeService != null) {
            mCrosstalkFragment = (crosstalkService as CrosstalkService).getCrosstalkFragment()
            bt.add(R.id.rootLay, mCrosstalkFragment)
            mStack.add(mCrosstalkFragment)
        }
        bt.commit()
    }

    /**
     * 切换fragment显示
     * @param position Int
     */
    private fun changeFragment(position: Int) {
        if (position < mStack.size) {
            val bt = supportFragmentManager.beginTransaction()
            mStack.forEach {
                bt.hide(it)
            }
            bt.show(mStack[position])
            bt.commit()
            //android6.0以后可以对状态栏文字颜色和图标进行修改
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility =
                        if (position == STATUSBAR_POSITION) {
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        } else {
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        }
            }
        }
    }

    /**
     * 返回到桌面
     */
    private fun backHome() {
        val home = Intent(Intent.ACTION_MAIN)
        home.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        home.addCategory(Intent.CATEGORY_HOME)
        startActivity(home)
    }

    override fun onBackPressed() {
        if (!mMusicFragment.onKeyBackPressed()) {
            backHome()
        }
    }

}
