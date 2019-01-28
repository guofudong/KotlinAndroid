package com.gfd.player.ui.activity

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.ui.activity.BaseMvpActivity
import com.gfd.player.R
import com.gfd.player.entity.VideoItemData
import com.gfd.player.injection.component.DaggerPlayComponent
import com.gfd.player.injection.moudle.PlayMoudle
import com.gfd.player.mvp.contract.PlayContract
import com.gfd.player.mvp.presenter.PlayPresenter
import com.gfd.player.widgets.VideoPlayer
import com.gfd.provider.router.RouterPath
import kotlinx.android.synthetic.main.player_activity_player.*
import org.song.videoplayer.DemoQSVideoView
import org.song.videoplayer.IVideoPlayer
import org.song.videoplayer.PlayListener
import org.song.videoplayer.Util
import org.song.videoplayer.media.AndroidMedia

/**
 * @Author : 郭富东
 * @Date ：2018/8/4 - 16:37
 * @Email：878749089@qq.com
 * @descriptio：
 */
@Route(path = RouterPath.Player.PATH_PLAYER)
class PlayerActivity : BaseMvpActivity<PlayPresenter>(), PlayContract.View {

    @Autowired(name = RouterPath.Player.KEY_PLAYER)
    @JvmField
    var analyzeUrl: String? = null

    @Autowired(name = RouterPath.Player.KEY_IMAGE)
    @JvmField

    var videoImg: String? = null
    @Autowired(name = RouterPath.Player.KEY_NAME)
    @JvmField
    var videoName: String? = null


    lateinit var videoUrl: String

    private lateinit var url: String

    override fun getLayoutId(): Int {
        return R.layout.player_activity_player
    }


    override fun injectComponent() {
        DaggerPlayComponent.builder()
                .activityComponent(mActivityComponent)
                .playMoudle(PlayMoudle(this))
                .build()
                .inject(this)
        ARouter.getInstance().inject(this)

    }

    override fun initView() {
        if (Build.VERSION.SDK_INT >= 19) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        setVideoPlayer(homeVideoPlayer)
    }

    override fun initData() {
        if (analyzeUrl != null) {
            mPresenter.getVideoUrl(analyzeUrl!!)
        }

    }

    override fun playVideo(videoUrl: String) {
        this.videoUrl = videoUrl
        play(homeVideoPlayer, videoUrl)
    }

    override fun playWebVideo(datas: List<VideoItemData>) {

    }

    override fun showVideoPlot(plotText: String) {
        tvPlot.text = plotText
    }


    private fun setVideoPlayer(demoVideoView: VideoPlayer) {
        demoVideoView.layoutParams = LinearLayout.LayoutParams(-1, resources.displayMetrics.widthPixels * 9 / 16)
        //进入全屏的模式 0横屏 1竖屏 2传感器自动横竖屏 3根据视频比例自动确定横竖屏      -1什么都不做
        demoVideoView.enterFullMode = 3
        //是否非全屏下也可以手势调节进度
        demoVideoView.isWindowGesture = true
        //  demoVideoView.enterWindowFullscreen()
        demoVideoView.setPlayListener(object : PlayListener {
            override fun onEvent(what: Int, vararg extra: Int?) {
                if ((what == DemoQSVideoView.EVENT_CONTROL_VIEW) and (Build.VERSION.SDK_INT >= 19) and !demoVideoView.isWindowFloatMode())
                    if (extra[0] == 0)
                    //状态栏隐藏/显示
                        Util.CLEAR_FULL(this@PlayerActivity)
                    else
                        Util.SET_FULL(this@PlayerActivity)
            }

            override fun onStatus(status: Int) {//播放状态
                if (status == IVideoPlayer.STATE_AUTO_COMPLETE)
                    demoVideoView.quitWindowFullscreen()//播放完成退出全屏
            }

            override//全屏/普通/浮窗
            fun onMode(mode: Int) {
            }

        })
    }


    private fun play(demoVideoView: VideoPlayer, url: String) {
        demoVideoView.release()
        demoVideoView.setDecodeMedia(AndroidMedia::class.java)
        demoVideoView.setUp(url, videoName)
        demoVideoView.play()
        this.url = url
    }

    //返回键
    override fun onBackPressed() {
        //全屏和系统浮窗不finish
        if (homeVideoPlayer.onBackPressed()) {
            if (homeVideoPlayer.isSystemFloatMode)
            //系统浮窗返回上一界面
                moveTaskToBack(true)
            return
        }
        super.onBackPressed()
    }

//=======================以下生命周期控制=======================

    public override fun onResume() {
        super.onResume()
        if (flag)
            homeVideoPlayer.play()
        handler.removeCallbacks(runnable)
        if (position > 0) {
            homeVideoPlayer.seekTo(position)
            position = 0
        }
    }

    internal var flag: Boolean = false//记录退出时播放状态 回来的时候继续播放
    internal var position: Int = 0//记录销毁时的进度 回来继续盖进度播放

    public override fun onPause() {
        super.onPause()
        if (homeVideoPlayer.isSystemFloatMode)
            return
        //暂停
        flag = homeVideoPlayer.isPlaying
        homeVideoPlayer.pause()
    }


    public override fun onStop() {
        super.onStop()
        if (homeVideoPlayer.isSystemFloatMode)
            return
        //不马上销毁 延时15秒
        handler.postDelayed(runnable, (1000 * 15).toLong())
    }

    public override fun onDestroy() {
        super.onDestroy()//销毁
        if (homeVideoPlayer.isSystemFloatMode)
            homeVideoPlayer.quitWindowFloat()
        homeVideoPlayer.release()
    }


    internal var handler = Handler()
    internal var runnable: Runnable = Runnable {
        if (homeVideoPlayer.currentState != IVideoPlayer.STATE_AUTO_COMPLETE)
            position = homeVideoPlayer.position
        homeVideoPlayer.release()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == 1) and (resultCode == Activity.RESULT_OK)) {
            this.videoUrl = data!!.data!!.toString()
            Toast.makeText(this, videoUrl, Toast.LENGTH_LONG).show()
            play(homeVideoPlayer, videoUrl)
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.primaryClip = ClipData.newPlainText("text", videoUrl)
        }
    }


}