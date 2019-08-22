package com.gfd.player.ui.fragment

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.WindowManager
import com.gfd.common.ext.gridInit
import com.gfd.common.ui.fragment.BaseMvpFragment
import com.gfd.player.R
import com.gfd.player.adapter.ProgramAdapter
import com.gfd.player.common.Conant
import com.gfd.player.entity.Live
import com.gfd.player.entity.TimeTableData
import com.gfd.player.ext.init
import com.gfd.player.ext.play
import com.gfd.player.ext.setUrl
import com.gfd.player.injection.component.DaggerLiveComponent
import com.gfd.player.injection.moudle.LiveModule
import com.gfd.player.mvp.contract.LiveContract
import com.gfd.player.mvp.presenter.LivePresenter
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter
import kotlinx.android.synthetic.main.player_fragment_live.*
import org.song.videoplayer.IVideoPlayer


/**
 * @Author : 郭富东
 * @Date ：2018/8/20 - 16:24
 * @Email：878749089@qq.com
 * @description：
 */
class LiveFragment : BaseMvpFragment<LivePresenter>(), LiveContract.View {

    companion object{
        /** GridView 列表的列数 */
        private const val GRID_COLUMNS = 3
    }
    private lateinit var mLeftAdapter: ProgramAdapter
    private lateinit var mLeftDatas: List<Live>
    private lateinit var mLRecyclerViewAdapter: LRecyclerViewAdapter
    private lateinit var videoUrl: String


    override fun isSetStateView(): Boolean  = false
    override fun getLayoutId(): Int {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        return R.layout.player_fragment_live
    }


    override fun injectComponent() {
        DaggerLiveComponent.builder()
                .activityComponent(mActivityComponent)
                .liveModule(LiveModule(this))
                .build()
                .inject(this)

    }


    override fun initView() {
        mLeftAdapter = ProgramAdapter(activity!!)
        mLRecyclerViewAdapter = LRecyclerViewAdapter(mLeftAdapter)
        mLeftList.gridInit(activity!!, GRID_COLUMNS, mLRecyclerViewAdapter)
        mPlayerVideoPlayer.init(activity!!)
    }

    override fun initData() {
        mPresenter.getLiveInfo(activity!!)
    }

    override fun setListener() {
        mLRecyclerViewAdapter.setOnItemClickListener { _, position ->
            if (mLeftAdapter.getCurrentSelect() != position) {
                val liveData = mLeftDatas[position]
                if (!liveData.isTitle) {
                    mLeftAdapter.setSelect(position)
                    mLRecyclerViewAdapter.notifyDataSetChanged()
                    videoUrl = liveData.live
                    mPlayerVideoPlayer.play(videoUrl)
                    activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏
                }
            }
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (hidden) {
            flag = mPlayerVideoPlayer.isPlaying
            mPlayerVideoPlayer.release()
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏
        }
    }

    override fun showLiveInfo(data: List<Live>) {
        mLRecyclerViewAdapter.setSpanSizeLookup { _, position ->
            val index = if (position == data.size) data.size - 1 else position
            val type = data[index].getItemType()
            if (type == Conant.ITEM_TYPE_TITLE) {
                GRID_COLUMNS
            } else {
                1
            }
        }
        mLeftDatas = data
        mLeftAdapter.updateData(data)
        videoUrl = mLeftDatas[1].live
        mPlayerVideoPlayer.setUrl(videoUrl)
    }

    override fun showTimeTable(data: List<TimeTableData>) {
    }

    override fun showVideo(url: String) {
    }


    override fun onKeyBackPressed(): Boolean {
        return mPlayerVideoPlayer.onBackPressed()
    }

    //=======================以下生命周期控制=======================
    override fun onResume() {
        super.onResume()
        if (flag)
            mPlayerVideoPlayer.play()
        handler.removeCallbacks(runnable)
        if (position > 0) {
            mPlayerVideoPlayer.seekTo(position)
            position = 0
        }
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏
    }

    private var flag: Boolean = false//记录退出时播放状态 回来的时候继续播放
    private var position: Int = 0//记录销毁时的进度 回来继续盖进度播放

    override fun onPause() {
        super.onPause()
        if (mPlayerVideoPlayer.isSystemFloatMode)
            return
        //暂停
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏
        flag = mPlayerVideoPlayer.isPlaying
        mPlayerVideoPlayer.pause()
    }


    override fun onStop() {
        super.onStop()
        if (mPlayerVideoPlayer.isSystemFloatMode)
            return
        //不马上销毁 延时15秒
        handler.postDelayed(runnable, (1000 * 15).toLong())
    }

    override fun onDestroy() {
        super.onDestroy()//销毁
        if (mPlayerVideoPlayer != null) {
            if (mPlayerVideoPlayer.isSystemFloatMode) {
                mPlayerVideoPlayer.quitWindowFloat()
            }
            mPlayerVideoPlayer.release()
        }
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏
    }


    private var handler = Handler()
    private var runnable: Runnable = Runnable {
        if (mPlayerVideoPlayer != null && mPlayerVideoPlayer.currentState != IVideoPlayer.STATE_AUTO_COMPLETE) {
            position = mPlayerVideoPlayer.position
            mPlayerVideoPlayer.release()
        }
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN) //显示状态栏
        if ((requestCode == 1) and (resultCode == Activity.RESULT_OK)) {
            this.videoUrl = data!!.data!!.toString()
            mPlayerVideoPlayer.play(videoUrl, "")
            val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.primaryClip = ClipData.newPlainText("text", videoUrl)
        }
    }

}