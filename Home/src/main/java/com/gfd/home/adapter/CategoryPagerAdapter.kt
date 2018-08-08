package com.gfd.home.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.gfd.home.ui.fragment.CategoryFragment

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 15:28
 * @Email：878749089@qq.com
 * @descriptio：
 */
class CategoryPagerAdapter(val fragments: List<CategoryFragment>, val titles: Array<String>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}