package com.gfd.home.mvp.presenter

import com.gfd.home.entity.VideoItemData
import com.gfd.home.mvp.CategoryContract
import com.gfd.home.service.CategoryService
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 14:54
 * @Email：878749089@qq.com
 * @descriptio：
 */
class CategoryPresenter @Inject constructor() : CategoryContract.Presenter, CategoryService.GetCategoryVideoCallBack {

    @Inject
    lateinit var mView: CategoryContract.View

    private var isLoading = false

    @Inject
    lateinit var mService: CategoryService

    override fun getCategoryVideos(url: String, page: Int, state: Int, isLoading: Boolean) {
        this.isLoading = isLoading
        if (isLoading) {
            mView.showLoading()
        }
        mService.getCategoryVideos(url, page, state, this)
    }

    override fun onCategoryVideos(datas: List<VideoItemData>, state: Int) {
        mView.showVideos(datas, state)
        if(isLoading){
            mView.hideLoading()
        }
    }

}