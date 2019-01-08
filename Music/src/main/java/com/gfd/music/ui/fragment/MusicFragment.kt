package com.gfd.music.ui.fragment

import android.content.Intent
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.gfd.common.ext.noScroll
import com.gfd.common.ui.fragment.BaseFragment
import com.gfd.music.R
import com.gfd.music.adapter.MusicPagerAdapter
import com.gfd.music.ui.activity.SearchActivity
import kotlinx.android.synthetic.main.music_fragment_music.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 9:54
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MusicFragment:BaseFragment(){

    private val mTabTitles = arrayOf("推荐","电台","短片")
    private val mFragments = ArrayList<BaseFragment>()

    override fun getLayoutId(): Int {
        return R.layout.music_fragment_music
    }

    override fun initView() {
        mFragments.add(RecommendFragment())
        mFragments.add(RadioFragment())
        mFragments.add(ShortFilmFragment())
        mViewPager.noScroll()
        mViewPager.adapter = MusicPagerAdapter(fragmentManager!!,mTabTitles,mFragments)
        mTabLayout.setupWithViewPager(mViewPager)
        for (i in 0..mTabLayout.tabCount) {
            val tab = mTabLayout.getTabAt(i)
            tab?.customView = getTabView(i)
        }

    }

    override fun initData() {
    }

    override fun setListener() {
        //将当前的tab文字放大
        mTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val textView = tab.customView?.findViewById(R.id.tab_item_textview) as TextView
                val indicator = tab.customView?.findViewById(R.id.view_music_indicator) as View
                textView.paint.isFakeBoldText = true
                indicator.visibility = View.VISIBLE
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val textView = tab.customView?.findViewById(R.id.tab_item_textview) as TextView
                val indicator = tab.customView?.findViewById(R.id.view_music_indicator) as View
                textView.paint.isFakeBoldText = false
                indicator.visibility = View.INVISIBLE
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
        //搜索按钮
        ivSearch.setOnClickListener {
            startActivity(Intent(activity,SearchActivity::class.java))
        }
    }


    /**
     * 获取自定义的tab View
     * @param position Int
     * @return View
     */
    private fun getTabView(position: Int): View {
        val view = LayoutInflater.from(activity).inflate(R.layout.music_layout_tab, null)
        val textView = view.findViewById(R.id.tab_item_textview) as TextView
        val indicator = view.findViewById(R.id.view_music_indicator) as View
        textView.text = mTabTitles[position]
        if (position == 0) {
            textView.paint.isFakeBoldText = true
            indicator.visibility = View.VISIBLE
        }else{
            indicator.visibility = View.INVISIBLE
        }
        return view
    }


}