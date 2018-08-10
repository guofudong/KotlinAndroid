package com.gfd.video

import com.gfd.common.ui.activity.BaseActivity
import com.gfd.common.ui.fragment.BaseFragment
import com.gfd.home.ui.fragment.HomeFragment
import com.gfd.music.ui.fragment.MusicFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 15:22
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MainActivity : BaseActivity() {

    private val mHomeFragment:HomeFragment by lazy { HomeFragment() }
    private val mMusicFragment: MusicFragment by lazy { MusicFragment() }

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
        bt.add(R.id.rootLay,mHomeFragment)
        bt.add(R.id.rootLay,mMusicFragment)
        mStack.add(mHomeFragment)
        mStack.add(mMusicFragment)
        bt.commit()
    }

    /**
     * 切换fragment显示
     * @param position Int
     */
    private fun changeFragment(position:Int){
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
            if (it == R.id.tab_home) {//首页
                changeFragment(0)
            } else if (it == R.id.tab_music) {//音乐
                changeFragment(1)
            } else if (it == R.id.tab_play) {//直播

            } else if (it == R.id.tab_mine) {//我的

            }

        }
    }

}

