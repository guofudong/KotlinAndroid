package com.gfd.player.ext

import android.content.Context
import android.os.Build
import android.widget.LinearLayout
import com.gfd.player.widgets.VideoPlayer
import org.song.videoplayer.DemoQSVideoView
import org.song.videoplayer.IVideoPlayer
import org.song.videoplayer.PlayListener
import org.song.videoplayer.Util
import org.song.videoplayer.media.AndroidMedia

/**
 * @Author : 郭富东
 * @Date ：2018/8/22 - 16:29
 * @Email：878749089@qq.com
 * @descriptio：
 */
fun VideoPlayer.init(context: Context){
    val videoView = this
    this.layoutParams = LinearLayout.LayoutParams(-1, context.resources.displayMetrics.widthPixels * 9 / 16)
    //进入全屏的模式 0横屏 1竖屏 2传感器自动横竖屏 3根据视频比例自动确定横竖屏      -1什么都不做
    this.enterFullMode = 3
    //是否非全屏下也可以手势调节进度
    this.isWindowGesture = true
    //  demoVideoView.enterWindowFullscreen()
    this.setPlayListener(object : PlayListener {
        override fun onEvent(what: Int, vararg extra: Int?) {
            if ((what == DemoQSVideoView.EVENT_CONTROL_VIEW) and (Build.VERSION.SDK_INT >= 19) and !videoView.isWindowFloatMode)
                if (extra[0] == 0)
                //状态栏隐藏/显示
                    Util.CLEAR_FULL(context)
                else
                    Util.SET_FULL(context)
        }

        override fun onStatus(status: Int) {//播放状态
            if (status == IVideoPlayer.STATE_AUTO_COMPLETE)
                videoView.quitWindowFullscreen()//播放完成退出全屏
        }

        override//全屏/普通/浮窗
        fun onMode(mode: Int) {
        }

    })
}

fun VideoPlayer.play(url:String,videoName:String = ""){
   this.release()
   this.setDecodeMedia(AndroidMedia::class.java)
   this.setUp(url, videoName)
   this.play()
}

fun VideoPlayer.setUrl(url:String){
    this.release()
    this.setDecodeMedia(AndroidMedia::class.java)
    this.setUp(url, "")
}