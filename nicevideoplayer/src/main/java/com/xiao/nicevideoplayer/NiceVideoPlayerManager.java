package com.xiao.nicevideoplayer;

import android.graphics.SurfaceTexture;

/**
 * Created by XiaoJianjun on 2017/5/5.
 * 视频播放器管理器.
 */
public class NiceVideoPlayerManager {

    private NiceVideoPlayer mVideoPlayer;

    private NiceVideoPlayerManager() {
    }

    private static NiceVideoPlayerManager sInstance;

    public static synchronized NiceVideoPlayerManager instance() {
        if (sInstance == null) {
            sInstance = new NiceVideoPlayerManager();
        }
        return sInstance;
    }

    public NiceVideoPlayer getCurrentNiceVideoPlayer() {
        return mVideoPlayer;
    }

    public void setCurrentNiceVideoPlayer(NiceVideoPlayer videoPlayer) {
        if (mVideoPlayer != videoPlayer) {
            releaseNiceVideoPlayer();
            mVideoPlayer = videoPlayer;
        }
    }
    public void setTextureView(SurfaceTexture surfaceTexture){
        if (mVideoPlayer != null) {
            mVideoPlayer.setTextureView(surfaceTexture);
        }
    }

    public void suspendNiceVideoPlayer() {
        if (mVideoPlayer != null && (mVideoPlayer.isPlaying() || mVideoPlayer.isBufferingPlaying())) {
            mVideoPlayer.pause();
        }
    }

    public void resumeNiceVideoPlayer() {
        if (mVideoPlayer != null && (mVideoPlayer.isPaused() || mVideoPlayer.isBufferingPaused())) {
            mVideoPlayer.restart();
        }
    }

    public void releaseNiceVideoPlayer() {
        if (mVideoPlayer != null) {
            mVideoPlayer.release();
            mVideoPlayer = null;
        }
    }

    public void pause(){
        if (mVideoPlayer != null) {
            mVideoPlayer.pause();
        }
    }

    public void continuePlayer(){
        if (mVideoPlayer != null) {
            mVideoPlayer.restart();
        }
    }

    public boolean onBackPressd() {
        if (mVideoPlayer != null) {
            if (mVideoPlayer.isFullScreen()) {
                return mVideoPlayer.exitFullScreen();
            } else if (mVideoPlayer.isTinyWindow()) {
                return mVideoPlayer.exitTinyWindow();
            }
        }
        return false;
    }
}
