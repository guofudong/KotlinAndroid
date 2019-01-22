package com.gfd.home.ui.fragment

import android.content.Intent
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.ext.gridInit
import com.gfd.common.ext.player
import com.gfd.common.net.status.OnStatusLayoutClickListener
import com.gfd.common.ui.fragment.BaseMvpFragment
import com.gfd.home.R
import com.gfd.home.adapter.VideoListAdapter
import com.gfd.home.common.Concant
import com.gfd.home.entity.BinnerData
import com.gfd.home.entity.VideoItemData
import com.gfd.home.entity.VideoListData
import com.gfd.home.injection.component.DaggerVideoComponent
import com.gfd.home.injection.module.VideoModule
import com.gfd.home.mvp.VideoListContract
import com.gfd.home.mvp.presenter.VedioPresenter
import com.gfd.home.ui.activity.CategoryActivity
import com.gfd.home.ui.activity.SearchActivity
import com.gfd.provider.router.RouterPath
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.kotlin.base.utils.AppPrefsUtils
import com.orhanobut.logger.Logger
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.home_fragment_home.*


/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 17:55
 * @Email：878749089@qq.com
 * @descriptio：
 */
class HomeFragment : BaseMvpFragment<VedioPresenter>(), VideoListContract.View {

    /** GridView 列表的列数 */
    private val GRID_COLUMNS = 3
    /** 轮播图切换时间 */
    private val BANNER_TIME = 3 * 1000
    private val videoDatas = ArrayList<VideoItemData>()
    private lateinit var mVideoAdapter: VideoListAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var mBanner: Banner
    private lateinit var imageDatas: List<BinnerData>
    private lateinit var mVideoDatas: List<VideoItemData>
    private lateinit var path: String

    override fun injectComponent() {
        DaggerVideoComponent.builder().activityComponent(mActivityComponent)
                .videoModule(VideoModule(this)).build().inject(this)

    }

    override fun getLayoutId(): Int {
        return R.layout.home_fragment_home
    }

    override fun initView() {
        //设置刷新
        swipeRefresh.setColorSchemeColors(resources.getColor(R.color.home_colorRefresh))
        swipeRefresh.setSize(SwipeRefreshLayout.DEFAULT)
        mVideoAdapter = VideoListAdapter(activity)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mVideoAdapter)
        mRecyclerView.gridInit(activity!!, GRID_COLUMNS, mLRecyclerViewAdapter)
        //添加Head View
        val headView = LayoutInflater.from(context).inflate(R.layout.home_head_home, null, false)
        mBanner = headView.findViewById(R.id.mBanner)
        mLRecyclerViewAdapter.addHeaderView(headView)

    }

    override fun initData() {
        mPresenter.getVideoList(true)
    }

    override fun setListener() {
        //轮播图点击事件
        mBanner.setOnBannerListener {
            val imgData = imageDatas[it]
            path = RouterPath.Player.PATH_PLAYER
            toPlayer(imgData.link, imgData.imgUrl, imgData.name)
            Logger.e("banner ：${imgData.link}")
        }
        swipeRefresh.setOnRefreshListener {
            AppPrefsUtils.remove(Concant.KEY_JSON)
            mPresenter.getVideoList(false)
        }
        //item点击监听
        mLRecyclerViewAdapter.setOnItemClickListener { _, position ->
            val itemData = mVideoDatas[position]
            if (itemData.getItemType() == Concant.ITEM_TYPE_TITLE) {//点击标题
                val intent = Intent(activity, CategoryActivity::class.java)
                intent.putExtra(Concant.CATEGORY, itemData.titleType)
                startActivity(intent)
                Logger.e("TitleType : ${itemData.titleType}")
            } else {//点击图片
                if (itemData.title == mVideoDatas[0].title) {
                    path = RouterPath.Player.PATH_PLAYER
                } else {
                    path = RouterPath.Player.PATH_PLAYER_WEB
                }
                toPlayer(itemData.videoLink, itemData.videoImg, itemData.videoName)
                Logger.e("list ：${itemData.videoLink}")
            }
        }
        //搜索
        tvSearch.setOnClickListener {
            startActivity(Intent(activity, SearchActivity::class.java))
        }

        mStatusLayoutManager.setOnStatusLayoutClickListener(object : OnStatusLayoutClickListener {
            override fun onEmptyViewClick(view: View) {

            }

            override fun onErrorViewClick(view: View) {
                Logger.e("点击错误布局")
                mPresenter.getVideoList(true)
            }
        })
    }

    override fun showVideoList(data: VideoListData) {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
        videoDatas.addAll(data.videoList)
        //设置轮播图数据
        setBanner(data.bannerUrls)
        //设置列表数据
        mLRecyclerViewAdapter.setSpanSizeLookup { _, position ->
            val type = data.videoList[position].type
            if (type == Concant.ITEM_TYPE_TITLE) {
                GRID_COLUMNS
            } else {
                1
            }
        }
        mVideoDatas = data.videoList
        mVideoAdapter.updateData(mVideoDatas)
        mLRecyclerViewAdapter.notifyDataSetChanged()
        mStatusLayoutManager.showContent()

    }

    /**
     * 设置轮播图数据
     * @param data VideoListData
     */
    private fun setBanner(data: List<BinnerData>) {
        imageDatas = data
        val bannerImages = ArrayList<String>()
        val titles = ArrayList<String>()
        for (bannerUrl in data) {
            bannerImages.add(bannerUrl.imgUrl)
            titles.add(bannerUrl.name)
        }
        mBanner.player(titles, bannerImages)
    }


    override fun onStop() {
        super.onStop()
        //结束轮播
        mBanner.stopAutoPlay()
    }

    fun toPlayer(videoUrl: String, videoImage: String, videoName: String) {
        Logger.e("跳转路径：path = $path")
        ARouter.getInstance().build(path)
                .withString(RouterPath.Player.KEY_PLAYER, videoUrl)
                .withString(RouterPath.Player.KEY_IMAGE, videoImage)
                .withString(RouterPath.Player.KEY_NAME, videoName)
                .navigation()
    }

}