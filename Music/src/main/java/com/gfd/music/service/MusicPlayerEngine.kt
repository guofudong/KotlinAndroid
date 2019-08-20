package com.gfd.music.service

import android.media.MediaPlayer
import android.os.PowerManager
import java.lang.ref.WeakReference

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 15:04
 * @Email：878749089@qq.com
 * @description：
 */
class MusicPlayerEngine(service: MusicPlayService) : MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    private val mService = WeakReference<MusicPlayService>(service)
    private val mPlayer = MediaPlayer()
    /** 是否初始化成功 */
    private var mIsInitialized = false
    /** 是否准备完成*/
    private var mIsPrepared = false

    init {
        //一直保持，确保后台播放时不被关闭
        mPlayer.setWakeMode(mService.get(), PowerManager.PARTIAL_WAKE_LOCK)
    }

    /**
     * 播放指定的音乐
     * @param url String
     */
    fun setDataSource(url: String) {
        try {
            if (mPlayer.isPlaying) {//如果正在播放中，停止当前的播放
                mPlayer.stop()
            }
            mIsPrepared = false
            mPlayer.reset()
            mPlayer.setDataSource(url)
            mPlayer.prepareAsync()
            mPlayer.setOnPreparedListener(this)
            mPlayer.setOnBufferingUpdateListener(this)
            mPlayer.setOnErrorListener(this)
            mPlayer.setOnCompletionListener(this)
        } catch (e: Exception) {
            mIsInitialized = false
        }
        mIsInitialized = true
    }

    fun start() {
        mPlayer.start()
    }

    fun stop() {
        mPlayer.reset()
        mIsInitialized = false
    }

    fun pause() {
        mPlayer.pause()
    }

    fun release() {
        mPlayer.release()
    }

    fun isPlaying(): Boolean {
        return mPlayer.isPlaying
    }

    fun getDuration(): Int {
        return if (mIsPrepared) {
            mPlayer.duration
        } else {
            0
        }
    }

    fun getPosition():Int{
        return mPlayer.currentPosition
    }

    fun seekTo(position:Int){
        mPlayer.seekTo(position)
    }

    fun setVolume(volume:Float){
        mPlayer.setVolume(volume,volume)
    }

    override fun onPrepared(mp: MediaPlayer) {
        mIsPrepared = true
        mPlayer.start()
    }

    override fun onBufferingUpdate(mp: MediaPlayer, percent: Int) {
    }

    override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
        return false
    }

    override fun onCompletion(mp: MediaPlayer) {
    }
}