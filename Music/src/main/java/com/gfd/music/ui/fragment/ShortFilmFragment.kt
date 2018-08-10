package com.gfd.music.ui.fragment

import com.gfd.common.ui.fragment.BaseMvpFragment
import com.gfd.music.R
import com.gfd.music.mvp.preesnter.ShortFilmPresenter

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:57
 * @Email：878749089@qq.com
 * @descriptio：短片Fragment
 */
class ShortFilmFragment:BaseMvpFragment<ShortFilmPresenter>(){

    override fun getLayoutId(): Int {
        return R.layout.fragment_short_film
    }
    override fun injectComponent() {

    }

    override fun initView() {
    }

    override fun initData() {
    }



}