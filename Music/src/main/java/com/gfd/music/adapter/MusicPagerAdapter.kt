package com.gfd.music.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.gfd.common.ui.fragment.BaseFragment

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 11:01
 * @Email：878749089@qq.com
 * @description：
 */
class MusicPagerAdapter(fm: FragmentManager, private val titles: Array<String>, private val fragments: List<BaseFragment>)
    : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}