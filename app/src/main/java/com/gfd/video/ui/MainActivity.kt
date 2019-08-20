package com.gfd.video.ui

import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.gfd.common.ui.activity.BaseActivity
import com.gfd.common.ui.fragment.BaseFragment
import com.gfd.provider.router.component.Router
import com.gfd.provider.router.service.CrosstalkService
import com.gfd.provider.router.service.HomeService
import com.gfd.provider.router.service.LiveService
import com.gfd.provider.router.service.MusicService
import com.gfd.video.R
import com.gfd.video.common.UpdateManager
import kotlinx.android.synthetic.main.app_activity_main.*
import java.util.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 15:22
 * @Email：878749089@qq.com
 * @description：app-主页面
 */
class MainActivity : BaseActivity() {

    private var mHomeFragment: BaseFragment? = null
    private var mMusicFragment: BaseFragment? = null
    private var mLiveFragment: BaseFragment? = null
    private var mCrosstalkFragment: BaseFragment? = null
    private var clickTime: Long = 0
    private val mStack = Stack<BaseFragment>()

    companion object {
        private const val STATE_BAR_POSITION = 1
    }

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
        UpdateManager.instance.checkVersion(this)
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
        val bt = supportFragmentManager.beginTransaction()
        val router = Router.instance
        val musicService = router.getService(MusicService::class.simpleName)
        val liveService = router.getService(LiveService::class.simpleName)
        val crosstalkService = router.getService(CrosstalkService::class.simpleName)
        val homeService = router.getService(HomeService::class.simpleName)
        if (homeService != null) {
            mHomeFragment = (homeService as HomeService).getHomeFragment()
            if (mHomeFragment != null) {
                bt.add(R.id.rootLay, mHomeFragment!!)
                mStack.add(mHomeFragment)
            }
        }
        if (homeService != null) {
            mMusicFragment = (musicService as MusicService).getMusicFragment()
            if (mMusicFragment != null) {
                bt.add(R.id.rootLay, mMusicFragment!!)
                mStack.add(mMusicFragment)
            }
        }
        if (homeService != null) {
            mLiveFragment = (liveService as LiveService).getLiveFragment()
            if (mLiveFragment != null) {
                bt.add(R.id.rootLay, mLiveFragment!!)
                mStack.add(mLiveFragment)
            }
        }
        if (homeService != null) {
            mCrosstalkFragment = (crosstalkService as CrosstalkService).getCrosstalkFragment()
            if (mCrosstalkFragment != null) {
                bt.add(R.id.rootLay, mCrosstalkFragment!!)
                mStack.add(mCrosstalkFragment)
            }
        }
        if (mStack.size != 0) {
            bt.commit()
        }
    }

    /**
     * 切换fragment显示
     * @param position Int
     */
    private fun changeFragment(position: Int) {
        if (position >= mStack.size) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            window.decorView.systemUiVisibility = if (position == STATE_BAR_POSITION) {
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            } else {
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
        val bt = supportFragmentManager.beginTransaction()
        mStack.forEach {
            bt.hide(it)
        }
        bt.show(mStack[position])
        bt.commit()
    }

    /**
     * 连续按下两次退出程序
     */
    private fun exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            clickTime = System.currentTimeMillis()
        } else {
            this.finish()
        }
    }

    override fun onBackPressed() {
        val isBackMusic = mMusicFragment?.onKeyBackPressed() ?: false
        val isBackLive = mLiveFragment?.onKeyBackPressed() ?: false
        if (!isBackMusic && !isBackLive) {
            exit()
        }
    }
}

