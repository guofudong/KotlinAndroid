package com.gfd.music.ui.fragment

import com.gfd.common.ui.fragment.BaseMvpFragment
import com.gfd.music.R
import com.gfd.music.mvp.preesnter.RadioPresenter

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 10:48
 * @Email：878749089@qq.com
 * @descriptio：电台Fragment
 */
class RadioFragment:BaseMvpFragment<RadioPresenter>(){

    override fun getLayoutId(): Int {
        return R.layout.fragment_radio
    }

    override fun injectComponent() {

    }

    override fun initView() {
    }

    override fun initData() {
    }


}