package com.gfd.home.ui.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.text.TextUtils
import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.common.BaseConstant
import com.gfd.common.ext.gridInit
import com.gfd.common.ui.fragment.BaseMvpFragment
import com.gfd.common.utils.ToastUtils
import com.gfd.home.R
import com.gfd.home.adapter.CategoryVideoAdapter
import com.gfd.home.common.Constant
import com.gfd.home.entity.VideoItemData
import com.gfd.home.injection.component.DaggerCategoryComponent
import com.gfd.home.injection.module.CategoryModule
import com.gfd.home.mvp.CategoryContract
import com.gfd.home.mvp.presenter.CategoryPresenter
import com.gfd.provider.router.RouterPath
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.home_fragment_category.*

/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 15:24
 * @Email：878749089@qq.com
 * @description：首页-更多页面-Fragment
 */
@Suppress("DEPRECATION")
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryContract.View {

    private var category = 0
    private lateinit var videoUrl: String
    private lateinit var mVideoAdapter: CategoryVideoAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private var currentPage = DEFAULT_PAGER
    private val totalPager = 20
    private lateinit var path: String

    companion object {
        /** 默认加载的页数*/
        private const val DEFAULT_PAGER = 2
        /** GridView 列表的列数 */
        private const val GRID_COLUMNS = 3
        const val CATEGORY = "category"
        const val CATEGORY_NEW = 1
        const val CATEGORY_MOVIE = 2
        const val CATEGORY_DINASHI = 3
        const val CATEGORY_ZONGYI = 4
    }

    override fun injectComponent() {
        DaggerCategoryComponent.builder()
                .activityComponent(mActivityComponent)
                .categoryModule(CategoryModule(this))
                .build().inject(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.home_fragment_category
    }


    override fun initView() {
        //设置刷新
        categoryRefresh.setColorSchemeColors(resources.getColor(R.color.home_colorRefresh))
        categoryRefresh.setSize(SwipeRefreshLayout.DEFAULT)
        mVideoAdapter = CategoryVideoAdapter(activity!!)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mVideoAdapter)
        categoryRecycler.gridInit(activity!!, GRID_COLUMNS, mLRecyclerViewAdapter)
        categoryRecycler.setLoadMoreEnabled(true)

    }

    override fun initData() {
        category = arguments?.get(CATEGORY) as Int
        videoUrl = when (category) {
            CATEGORY_NEW -> {
                BaseConstant.URL_CHANNEL_NEW
            }
            CATEGORY_MOVIE -> BaseConstant.URL_CHANNEL_MOVIE
            CATEGORY_DINASHI -> BaseConstant.URL_CHANNEL_DIANSHI
            CATEGORY_ZONGYI -> BaseConstant.URL_CHANNEL_ZONGYI
            else -> ""
        }
        if (!TextUtils.isEmpty(videoUrl)) {
            mPresenter.getCategoryVideos(videoUrl, currentPage, Constant.STATE_REFRESH, false)
        } else {
            ToastUtils.instance.showToast("类型不匹配")
            activity?.finish()
        }
    }

    override fun setListener() {
        //设置加载更多
        categoryRecycler.setOnLoadMoreListener {
            currentPage++
            if (currentPage <= totalPager) {
                mPresenter.getCategoryVideos(videoUrl, currentPage, Constant.STATE_LOAD_MORE, false)
            } else {
                categoryRecycler.setNoMore(true)
            }
        }
        //设置下拉刷新
        categoryRefresh.setOnRefreshListener {
            currentPage = DEFAULT_PAGER
            mPresenter.getCategoryVideos(videoUrl, currentPage, Constant.STATE_REFRESH, false)
        }
        //item点击监听
        mLRecyclerViewAdapter.setOnItemClickListener { _, position ->
            val videoDatas = mVideoAdapter.getDatas()
            val itemData = videoDatas[position]
            path = if (category == CATEGORY_NEW) {
                RouterPath.Player.PATH_PLAYER
            } else {
                RouterPath.Player.PATH_PLAYER_WEB
            }
            toPlayer(itemData.videoLink, itemData.videoImg, itemData.videoName)
            Logger.e("list ：${itemData.videoLink}")

        }
    }

    override fun showVideos(data: List<VideoItemData>, state: Int) {
        if (state == Constant.STATE_LOAD_MORE) {//加载更多
            mVideoAdapter.addAll(data)
            categoryRecycler?.refreshComplete(data.size)
        } else if (state == Constant.STATE_REFRESH) {//下拉刷新
            mVideoAdapter.updateData(data)
            categoryRefresh?.isRefreshing = false
        }
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }


    private fun toPlayer(videoUrl: String, videoImage: String, videoName: String) {
        ARouter.getInstance().build(path)
                .withString(RouterPath.Player.KEY_PLAYER, videoUrl)
                .withString(RouterPath.Player.KEY_IMAGE, videoImage)
                .withString(RouterPath.Player.KEY_NAME, videoName)
                .navigation()
    }

}