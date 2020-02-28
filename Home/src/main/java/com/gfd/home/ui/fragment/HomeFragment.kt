package com.gfd.home.ui.fragment

import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.ext.gridInit
import com.gfd.common.ext.player
import com.gfd.common.ui.fragment.BaseMvpFragment
import com.gfd.home.R
import com.gfd.home.adapter.VideoListAdapter
import com.gfd.home.common.Constant
import com.gfd.home.entity.BannerData
import com.gfd.home.entity.VideoItemData
import com.gfd.home.entity.VideoListData
import com.gfd.home.injection.component.DaggerVideoComponent
import com.gfd.home.injection.module.VideoModule
import com.gfd.home.mvp.VideoListContract
import com.gfd.home.mvp.presenter.VideoPresenter
import com.gfd.home.ui.activity.CategoryActivity
import com.gfd.home.ui.activity.MovieListActivity
import com.gfd.home.ui.activity.SearchActivity
import com.gfd.provider.router.RouterPath
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import com.kotlin.base.utils.AppPrefsUtils
import com.orhanobut.logger.Logger
import com.youth.banner.Banner
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.home_fragment_home.*
import org.jetbrains.anko.startActivity


/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 17:55
 * @Email：878749089@qq.com
 * @description：
 */
@Suppress("DEPRECATION")
class HomeFragment : BaseMvpFragment<VideoPresenter>(), VideoListContract.View {

    private val videoData = ArrayList<VideoItemData>()
    private lateinit var mVideoAdapter: VideoListAdapter
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var mBanner: Banner
    private lateinit var imageData: List<BannerData>
    private lateinit var mVideoData: List<VideoItemData>
    private lateinit var path: String
    private lateinit var homeCategory01: CircleImageView
    private lateinit var homeCategory02: CircleImageView
    private lateinit var homeCategory03: CircleImageView
    private lateinit var homeCategory04: CircleImageView

    companion object {
        /** GridView 列表的列数 */
        private const val GRID_COLUMNS = 3
    }

    override fun injectComponent() {
        DaggerVideoComponent.builder().activityComponent(mActivityComponent)
                .videoModule(VideoModule(this)).build().inject(this)

    }
    override fun getLayoutId(): Int = R.layout.home_fragment_home

    override fun initView() {
        //设置刷新
        swipeRefresh.setColorSchemeColors(resources.getColor(R.color.home_colorRefresh))
        swipeRefresh.setSize(SwipeRefreshLayout.DEFAULT)
        mVideoAdapter = VideoListAdapter(activity!!)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mVideoAdapter)
        mRecyclerView.gridInit(activity!!, GRID_COLUMNS, mLRecyclerViewAdapter)
        //添加Head View
        val headViewBanner = LayoutInflater.from(context).inflate(R.layout.home_head_banner, null, false)
        mBanner = headViewBanner.findViewById(R.id.mBanner)
        homeCategory01 = headViewBanner.findViewById(R.id.homeCategoty01)
        homeCategory02 = headViewBanner.findViewById(R.id.homeCategoty02)
        homeCategory03 = headViewBanner.findViewById(R.id.homeCategoty03)
        homeCategory04 = headViewBanner.findViewById(R.id.homeCategoty04)
        mLRecyclerViewAdapter.addHeaderView(headViewBanner)
    }

    override fun initData() {
        mPresenter.getVideoList(true)
    }

    override fun setListener() {
        //轮播图点击事件
        mBanner.setOnBannerListener {
            val imgData = imageData[it]
            path = RouterPath.Player.PATH_PLAYER
            toPlayer(imgData.link, imgData.imgUrl, imgData.name)
        }
        swipeRefresh.setOnRefreshListener {
            AppPrefsUtils.remove(Constant.KEY_JSON)
            mPresenter.getVideoList(false)
        }
        //item点击监听
        mLRecyclerViewAdapter.setOnItemClickListener { _, position ->
            val itemData = mVideoData[position]
            if (itemData.getItemType() == Constant.ITEM_TYPE_TITLE) {//点击标题
                activity?.startActivity<CategoryActivity>(Constant.CATEGORY to itemData.titleType)
            } else {//点击图片
                path = if (itemData.title == mVideoData[0].title) RouterPath.Player.PATH_PLAYER else RouterPath.Player.PATH_PLAYER_WEB
                toPlayer(itemData.videoLink, itemData.videoImg, itemData.videoName)
            }
        }
        //搜索
        tvSearch.setOnClickListener {
            activity?.startActivity<SearchActivity>()
        }

        //分类点击
        homeCategory01.setOnClickListener {
            //正在热映
            toMovieListActivity("正在热映",Constant.TYPE_MOVIE_01)
        }
        homeCategory02.setOnClickListener {
            //即将上映
            toMovieListActivity("即将上映",Constant.TYPE_MOVIE_02)
        }
        homeCategory03.setOnClickListener {
            //电影排行
            toMovieListActivity("电影排行",Constant.TYPE_MOVIE_03)
        }
        homeCategory04.setOnClickListener {
            //随便看看
            toMovieListActivity("随便看看",Constant.TYPE_MOVIE_04)
        }
    }

    /**
     * 跳转到电影列表页面
     * @param title String：电影类型title
     * @param type Int：电影类型tag
     */
    private fun toMovieListActivity(title:String,type:Int){
        activity?.startActivity<MovieListActivity>("title" to title,"movieType" to type)
    }

    override fun showVideoList(data: VideoListData) {
        if (swipeRefresh != null && swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = false
        videoData.addAll(data.videoList)
        //设置轮播图数据
        setBanner(data.bannerUrls)
        //设置列表数据
        mLRecyclerViewAdapter.setSpanSizeLookup { _, position ->
            val type = data.videoList[position].type
            if (type == Constant.ITEM_TYPE_TITLE) GRID_COLUMNS else 1
        }
        mVideoData = data.videoList
        mVideoAdapter.updateData(mVideoData)
        mLRecyclerViewAdapter.notifyDataSetChanged()
    }

    /**
     * 设置轮播图数据
     * @param data VideoListData
     */
    private fun setBanner(data: List<BannerData>) {
        imageData = data
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

    /**
     * 跳转到视频播放页面
     * @param videoUrl String：视频播放地址
     * @param videoImage String：视频海报
     * @param videoName String：视频标题
     */
    private fun toPlayer(videoUrl: String, videoImage: String, videoName: String) {
        Logger.e("跳转路径：path = $path")
        ARouter.getInstance().build(path)
                .withString(RouterPath.Player.KEY_PLAYER, videoUrl)
                .withString(RouterPath.Player.KEY_IMAGE, videoImage)
                .withString(RouterPath.Player.KEY_NAME, videoName)
                .navigation()
    }
}