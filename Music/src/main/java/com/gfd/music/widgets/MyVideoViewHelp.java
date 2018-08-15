package com.gfd.music.widgets;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.gfd.music.R;

import org.song.videoplayer.Util;

/**
 * Created by song on 2017/2/13.
 * Contact github.com/tohodog
 * UI界面由子类决定
 * edit on 2017/4/8.
 * 分离出QSVideoView,本类作为辅助类.
 * <p>
 * *1.提供播放器常见的ui的控制逻辑(播放按钮 进度条 时间等)
 * *2.提供手势支持
 * <p>
 * 减轻开发者工作量
 * 子类提供的xml按规定提供控件的id 本类即可完成该控件的逻辑
 */

public abstract class MyVideoViewHelp extends QSVideoView implements HandleTouchEvent.GestureEvent, SeekBar.OnSeekBarChangeListener {

    public static final int EVENT_CONTROL_VIEW = 1001;//控制view显示隐藏事件 extra[0]：0显示 1隐藏
    public static final int EVENT_SEEKBAR_START = 1002;//进度条拖动事件开始
    public static final int EVENT_SEEKBAR_TOUCHING = 1003;//拖动中 extra[0]进度 extra[1]总进度
    public static final int EVENT_SEEKBAR_END = 1004;//进度条拖动事件结束
    protected final int progressMax = 1000;
    public boolean isWindowGesture = false;//是否非全屏下也可以手势调节进度
    protected ViewGroup controlContainer;//控制ui容器
    //提供辅助的控件
    protected ImageView startButton, startButton2;//播放按钮
    protected SeekBar seekBar;//拖动条
    protected TextView currentTimeTextView, totalTimeTextView;//播放时间/视频长度
    protected ImageView fullscreenButton;//全屏按钮
    protected ProgressBar progressBar;//第二进度条
    protected View backView, floatCloseView, floatBackView;//返回
    protected boolean isShowControlView;
    protected Handler mHandler;
    protected AudioManager audioManager;
    private HandleTouchEvent handleTouchEvent;
    private MyOnClickListener myOnClickListener;
    private Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(updateProgress, 500);
            setProgressAndText();
        }
    };
    private Runnable dismissControlViewTimerRunnable = new Runnable() {
        @Override
        public void run() {
            isShowControlView = false;
            dismissControlView(currentState, currentMode);
            handlePlayListener.onEvent(EVENT_CONTROL_VIEW, isShowControlView ? 0 : 1);
        }
    };
    //调节前的值 亮度退出全屏应原样
    private int tempPosition;
    private int tempBrightness;
    private int tempVolume;

    public MyVideoViewHelp(Context context) {
        this(context, null);
    }

    public MyVideoViewHelp(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyVideoViewHelp(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHelpView(context);
    }

    protected void initHelpView(Context context) {
        myOnClickListener = new MyOnClickListener();
        mHandler = new Handler(Looper.getMainLooper());
        handleTouchEvent = new HandleTouchEvent(this);
        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);

        controlContainer = (ViewGroup) View.inflate(context, getLayoutId(), null);
        videoView.addView(controlContainer, new LayoutParams(-1, -1));
        videoView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return handleTouchEvent.handleEvent(v, event);
            }
        });

        startButton = (ImageView) findViewById(org.song.videoplayer.R.id.help_start);
        startButton2 = (ImageView) findViewById(org.song.videoplayer.R.id.help_start2);
        fullscreenButton = (ImageView) findViewById(org.song.videoplayer.R.id.help_fullscreen);
        seekBar = (SeekBar) findViewById(org.song.videoplayer.R.id.help_seekbar);
        progressBar = (ProgressBar) findViewById(org.song.videoplayer.R.id.help_progress);
        currentTimeTextView = (TextView) findViewById(org.song.videoplayer.R.id.help_current);
        totalTimeTextView = (TextView) findViewById(org.song.videoplayer.R.id.help_total);
        backView = findViewById(org.song.videoplayer.R.id.help_back);
        floatCloseView = findViewById(org.song.videoplayer.R.id.help_float_close);
        floatBackView = findViewById(org.song.videoplayer.R.id.help_float_goback);

        if (seekBar != null) {
            seekBar.setOnSeekBarChangeListener(this);
            seekBar.setMax(progressMax);
        }
        if (progressBar != null)
            progressBar.setMax(progressMax);
        setClick(videoView, startButton, startButton2, fullscreenButton, backView, floatCloseView, floatBackView);

    }
    //-----------ui监听end-----------------

    //-----------ui监听start-----------------
    private void setClick(View... vs) {
        for (View v : vs) {
            if (v != null)
                v.setOnClickListener(myOnClickListener);
        }
    }

    protected void clickFull() {
        if (currentMode == MODE_WINDOW_FULLSCREEN) {
            quitWindowFullscreen();
        } else {
            enterWindowFullscreen();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (getDuration() > 1) {
            int time = seekBar.getProgress() * (getDuration() / progressMax);
            if (currentTimeTextView != null)
                currentTimeTextView.setText(Util.stringForTime(time));
        }
        handlePlayListener.onEvent(EVENT_SEEKBAR_TOUCHING, progress, progressMax);
        //Log.i(TAG, "onProgressChanged " + Util.stringForTime(time));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        cancelProgressTimer();
        cancelDismissControlViewTimer();
        handlePlayListener.onEvent(EVENT_SEEKBAR_START);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (getDuration() > 1) {
            int time = seekBar.getProgress() * (getDuration() / progressMax);
            seekTo(time);
        }
        startProgressTimer();
        if (currentState == STATE_PLAYING)
            startDismissControlViewTimer(1314);
        handlePlayListener.onEvent(EVENT_SEEKBAR_END);
    }

    //-----------设置数据start-----------------
    @Override//覆盖监听播放器状态
    protected void setUIWithStateAndMode(final int status, final int mode) {
        cancelDismissControlViewTimer();
        cancelProgressTimer();
        switch (status) {
            case STATE_NORMAL:
            case STATE_PREPARING:
                resetProgressAndTime();
                onBuffering(false);
                isShowControlView = false;
                break;
            case STATE_PLAYING:
                startDismissControlViewTimer();
            case STATE_PAUSE:
                startProgressTimer();
                break;
            case STATE_ERROR:
                onBuffering(false);
                isShowControlView = false;
                break;
            case STATE_AUTO_COMPLETE:
                setCompleProgressAndTime();
                onBuffering(false);
                break;
        }
        changeUiWithStateAndMode(status, mode);
        if ((status == STATE_PLAYING || status == STATE_PAUSE || status == STATE_AUTO_COMPLETE)
                & !isShowControlView)
            dismissControlView(status, mode);
        super.setUIWithStateAndMode(status, mode);

        //监听回调永远放在最后
        handlePlayListener.onEvent(EVENT_CONTROL_VIEW, isShowControlView ? 0 : 1);
    }
    //-----------设置数据end-----------------

    //缓冲进度
    @Override
    protected void setBufferProgress(float bufferProgress) {
        if (seekBar != null)
            seekBar.setSecondaryProgress((int) (bufferProgress * progressMax));
        if (progressBar != null)
            progressBar.setSecondaryProgress((int) (bufferProgress * progressMax));
    }

    //设置进度和时间
    protected void setProgressAndText() {
        int position = getPosition();
        int duration = getDuration();
        if (position < 0)
            position = 0;
        if (duration <= 0)
            duration = 1;
        int progress = (int) (((long) position * progressMax) / duration);
        if (progress < 0 || progress > progressMax)//防止溢出
            progress = progressMax;
        setProgressBar(progress, seekBar, progressBar);
        if (currentTimeTextView != null)
            currentTimeTextView.setText(Util.stringForTime(position));
        if (totalTimeTextView != null)
            if (duration > 1)
                totalTimeTextView.setText(Util.stringForTime(duration));
            else
                totalTimeTextView.setText("直播");
    }

    //初始化进度和时间
    protected void resetProgressAndTime() {
        setProgressBar(0, seekBar, progressBar);
        if (currentTimeTextView != null)
            currentTimeTextView.setText(Util.stringForTime(0));
        if (totalTimeTextView != null)
            totalTimeTextView.setText(Util.stringForTime(0));
    }
    //-----------定时任务更新进度end-----------------

    //播放完成进度和时间
    protected void setCompleProgressAndTime() {
        setProgressBar(progressMax, seekBar, progressBar);
        if (currentTimeTextView != null)
            currentTimeTextView.setText(Util.stringForTime(getDuration()));
    }

    private void setProgressBar(int pro, ProgressBar... ps) {
        for (ProgressBar p : ps)
            if (p != null)
                p.setProgress(pro);

    }

    //-----------定时任务更新进度start-----------------
    protected void startProgressTimer() {
        cancelProgressTimer();
        mHandler.post(updateProgress);
    }

    protected void cancelProgressTimer() {
        mHandler.removeCallbacks(updateProgress);
    }
    //-----------定时任务隐藏控制栏end-----------------

    //-----------定时任务隐藏控制栏start-----------------
    protected void startDismissControlViewTimer() {
        startDismissControlViewTimer(2500);
    }

    protected void startDismissControlViewTimer(int delayed) {
        cancelDismissControlViewTimer();
        mHandler.postDelayed(dismissControlViewTimerRunnable, delayed);
    }

    protected void cancelDismissControlViewTimer() {
        mHandler.removeCallbacks(dismissControlViewTimerRunnable);
    }

    /**
     * =========================================
     * -------子类需要实现的重要的方法------------
     * ========================================
     */
    protected abstract int getLayoutId();//id见ids.xml

    protected abstract void changeUiWithStateAndMode(int status, int mode);//根据状态设置ui显示/隐藏

    protected abstract void dismissControlView(int status, int mode);//播放时定时隐藏的控制ui

    protected abstract void onBuffering(boolean isBuffering);//缓冲

    //==============================================================================================
    //-------------------------------------以下为手势逻辑--------------------------------------------
    //==============================================================================================
    @Override
    public void onGestureBegin(int type) {
        if (!isWindowGesture & currentMode != MODE_WINDOW_FULLSCREEN)
            return;

        //进度
        if (type == HandleTouchEvent.GestureEvent.TOUCH_FULL_X & checkReady())
            tempPosition = getPosition();
        //亮度
        if (type == HandleTouchEvent.GestureEvent.TOUCH_LEFT_Y) {
            tempBrightness = (int) (Util.scanForActivity(getContext()).getWindow().getAttributes().screenBrightness * 255);
            if (tempBrightness < 0)
                try {//系统亮度 不能activity取
                    tempBrightness = Settings.System.getInt(getContext().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
                } catch (Settings.SettingNotFoundException e) {
                    e.printStackTrace();
                    tempBrightness = 0;
                }
        }
        //音量
        if (type == HandleTouchEvent.GestureEvent.TOUCH_RIGHT_Y)
            tempVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

    }

    @Override
    public void onGestureChange(int type, float level) {
        if (!isWindowGesture & currentMode != MODE_WINDOW_FULLSCREEN)
            return;
        //进度
        if (type == HandleTouchEvent.GestureEvent.TOUCH_FULL_X & checkReady()) {
            int duration = getDuration();
            if (duration <= 1)
                return;
            int delta = (int) (level * Math.abs(level) * duration);
            if (delta < -tempPosition)
                delta = -tempPosition;
            if (delta > duration - tempPosition)
                delta = duration - tempPosition;
            if (showProgressDialog(delta, tempPosition, duration)) {

            }
        }
        //亮度
        if (type == HandleTouchEvent.GestureEvent.TOUCH_LEFT_Y) {
            WindowManager.LayoutParams params = Util.scanForActivity(getContext()).getWindow().getAttributes();
            int delta = (int) (level * 255);
            int nowBrightness = tempBrightness + delta;
            if (nowBrightness < 0)
                nowBrightness = 0;
            if (nowBrightness > 255)
                nowBrightness = 255;
            float b = nowBrightness / 255.0f;
            if (showBrightnessDialog((int) (b * 100), 100)) {
                params.screenBrightness = b;
                Util.scanForActivity(getContext()).getWindow().setAttributes(params);
            }
        }
        //音量
        if (type == HandleTouchEvent.GestureEvent.TOUCH_RIGHT_Y) {
            int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            int deltaV = (int) (max * level);
            int nowVolume = tempVolume + deltaV;
            if (nowVolume < 0)
                nowVolume = 0;
            if (nowVolume > max)
                nowVolume = max;
            if (showVolumeDialog(nowVolume, max))
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, nowVolume, 0);
        }
    }

    @Override
    public void onGestureEnd(int type, float level) {
        //双击
        if (type == HandleTouchEvent.GestureEvent.TOUCH_DOUBLE_C)
            doubleClick();

        if (!isWindowGesture & currentMode != MODE_WINDOW_FULLSCREEN)
            return;
        //进度
        if (type == HandleTouchEvent.GestureEvent.TOUCH_FULL_X & checkReady()) {
            int duration = getDuration();
            if (duration <= 0)
                return;
            if (!dismissProgressDialog()) return;

            int delta = (int) (level * duration);
            tempPosition += delta;
            if (tempPosition > duration)
                tempPosition = duration;
            if (tempPosition < 0)
                tempPosition = 0;
            seekTo(tempPosition);
            tempPosition = 0;

        }
        //亮度
        if (type == HandleTouchEvent.GestureEvent.TOUCH_LEFT_Y) {
            dismissBrightnessDialog();
        }
        //音量
        if (type == HandleTouchEvent.GestureEvent.TOUCH_RIGHT_Y) {
            dismissVolumeDialog();
        }
    }

    //-----------各种手势ui实现start-----------
    //子类写了实现 就返回true
    protected abstract boolean showWifiDialog();//要弹出非wifi提示框覆盖return true即可

    protected abstract void doubleClick();

    protected abstract boolean showProgressDialog(int delay, int position, int duration);

    protected abstract boolean dismissProgressDialog();

    protected abstract boolean showVolumeDialog(int nowVolume, int maxVolume);

    protected abstract boolean dismissVolumeDialog();

    protected abstract boolean showBrightnessDialog(int nowBrightness, int maxBrightness);

    protected abstract boolean dismissBrightnessDialog();

    private class MyOnClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            int i = view.getId();
            //播放按钮
            if (i == R.id.help_start || i == org.song.videoplayer.R.id.help_start2) {
                clickPlay();
            }
            //全屏按钮
            if (i == org.song.videoplayer.R.id.help_fullscreen) {
                clickFull();
            }
            //退出按钮
            if (i == org.song.videoplayer.R.id.help_back) {
                if (currentMode != MODE_WINDOW_NORMAL)
                    quitWindowFullscreen();
                else
                    Util.scanForActivity(getContext()).finish();
            }
            //退出悬浮窗按钮
            if (i == org.song.videoplayer.R.id.help_float_close) {
                if (isSystemFloatMode())
                    release();
                quitWindowFloat();
            }
            //退出悬浮窗按钮
            if (i == org.song.videoplayer.R.id.help_float_goback) {
                if (isSystemFloatMode()) {
                    try {
                        Intent intent = new Intent(getContext(), Util.scanForActivity(getContext()).getClass());
                        getContext().getApplicationContext().startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                quitWindowFloat();
            }
            //点击空白处
            if (view == videoView) {
                if (currentState == STATE_NORMAL || currentState == STATE_ERROR)
                    clickPlay();
                else if (currentState == STATE_PLAYING ||
                        currentState == STATE_PAUSE ||
                        currentState == STATE_AUTO_COMPLETE) {
                    isShowControlView = !isShowControlView;
                    setUIWithStateAndMode(currentState, currentMode);
                }
            }
        }
    }
    //-----------各种调节弹窗end-----------

}
