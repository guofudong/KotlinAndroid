package com.gfd.home.ui.activity

import android.os.Bundle
import com.gfd.common.ui.activity.BaseActivity
import com.gfd.home.R
import com.gfd.home.adapter.CategoryPagerAdapter
import com.gfd.home.common.Concant
import com.gfd.home.ui.fragment.CategoryFragment
import kotlinx.android.synthetic.main.activity_category.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 14:53
 * @Email：878749089@qq.com
 * @descriptio：
 */
class CategoryActivity : BaseActivity() {

    private val categoryTitles = arrayOf("抢先看", "电视剧", "电影", "综艺")
    private val mFragments = ArrayList<CategoryFragment>()
    override fun getLayoutId(): Int {
        return R.layout.activity_category
    }

    override fun initView() {
        val currentPosition = when (intent.getIntExtra(Concant.CATEGORY, 0)) {
            Concant.CATEGORY_NEW -> 0
            Concant.CATEGORY_DINASHI -> 1
            Concant.CATEGORY_MOVIE -> 2
            Concant.CATEGORY_ZONGYI -> 3
            else -> 0
        }
        initFragment()
        videoPager.offscreenPageLimit = mFragments.size
        videoPager.adapter = CategoryPagerAdapter(mFragments, categoryTitles, supportFragmentManager)
        categoryTabs.setupWithViewPager(videoPager, false)
        videoPager.setCurrentItem(currentPosition, false)
    }

    private fun initFragment() {
        //抢先看
        val newFragment = CategoryFragment()
        val new = Bundle()
        new.putInt(CategoryFragment.CATEGORY, CategoryFragment.CATEGORY_NEW)
        newFragment.arguments = new
        mFragments.add(newFragment)
        //电视剧
        val dxFragment = CategoryFragment()
        val dx = Bundle()
        dx.putInt(CategoryFragment.CATEGORY, CategoryFragment.CATEGORY_DINASHI)
        dxFragment.arguments = dx
        mFragments.add(dxFragment)
        //电影
        val dyFragment = CategoryFragment()
        val dy = Bundle()
        dy.putInt(CategoryFragment.CATEGORY, CategoryFragment.CATEGORY_MOVIE)
        dyFragment.arguments = dy
        mFragments.add(dyFragment)
        //综艺
        val zywFragment = CategoryFragment()
        val zy = Bundle()
        zy.putInt(CategoryFragment.CATEGORY, CategoryFragment.CATEGORY_ZONGYI)
        zywFragment.arguments = zy
        mFragments.add(zywFragment)
    }

    override fun initData() {
    }


}