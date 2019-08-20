package com.gfd.home.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.gfd.home.ui.fragment.CategoryFragment

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 15:28
 * @Email：878749089@qq.com
 * @description：首页-更多页面列表适配器
 */
class CategoryPagerAdapter(private val fragments: List<CategoryFragment>, private val titles: Array<String>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}