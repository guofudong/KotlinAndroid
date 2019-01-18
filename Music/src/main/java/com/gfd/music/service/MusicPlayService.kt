package com.gfd.music.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.gfd.music.api.Api
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.orhanobut.logger.Logger
import org.json.JSONObject

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 10:56
 * @Email：878749089@qq.com
 * @descriptio：
 */
class MusicPlayService : Service() {

    private lateinit var mPlayer: MusicPlayerEngine

    override fun onCreate() {
        super.onCreate()
        mPlayer = MusicPlayerEngine(this)
    }

    override fun onBind(intent: Intent): IBinder {
        return MusicServiceStub(this).asBinder()
    }

    fun playMusic(path: String) {
        mPlayer.setDataSource(path)
    }

    fun playMusicById(url: String) {
        mPlayer.setDataSource(url)
    }

    fun pause() {
        mPlayer.pause()
    }

    fun stop() {
        mPlayer.stop()
    }

    fun playNext() {
    }

    fun playPrev() {

    }

    fun seekTo(position: Int) {
        mPlayer.seekTo(position)
    }

    fun getDuration(): Int {
        return mPlayer.getDuration()
    }

    fun isPlaying(): Boolean {
        return mPlayer.isPlaying()
    }

}