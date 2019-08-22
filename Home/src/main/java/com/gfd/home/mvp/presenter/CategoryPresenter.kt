package com.gfd.home.mvp.presenter

import com.gfd.home.entity.VideoItemData
import com.gfd.home.mvp.CategoryContract
import com.gfd.home.service.CategoryService
import javax.inject.Inject

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 14:54
 * @Email：878749089@qq.com
 * @description：首页-更多页面-MVP-Presenter
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

    override fun onCategoryVideos(data: List<VideoItemData>, state: Int) {
        mView.showVideos(data, state)
        if (isLoading) {
            mView.showContent()
        }
    }

}