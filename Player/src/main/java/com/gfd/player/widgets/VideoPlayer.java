package com.gfd.player.widgets;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gfd.player.R;

import org.song.videoplayer.QSVideoViewHelp;
import org.song.videoplayer.Util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author : 郭富东
 * @Date ：2018/8/23 - 10:02
 * @Email：878749089@qq.com
 * @descriptio：
 */
public class VideoPlayer extends QSVideoViewHelp {

    protected ImageView coverImageView;
    protected ViewGroup bottomContainer;
    protected ViewGroup topContainer;
    protected ViewGroup loadingContainer;
    protected ViewGroup errorContainer;
    protected ViewGroup bufferingContainer;
    protected TextView titleTextView;
    protected List<View> changeViews;
    public boolean isShowWifiDialog;
    protected PopupWindow mProgressDialog;
    protected ProgressBar mDialogProgressBar;
    protected TextView tv_current;
    protected TextView tv_duration;
    protected TextView tv_delta;
    protected ImageView mDialogIcon;
    protected PopupWindow mVolumeDialog;
    protected ProgressBar mDialogVolumeProgressBar;
    protected TextView mDialogVolumeTextView;
    protected ImageView mDialogVolumeImageView;
    protected PopupWindow mBrightnessDialog;
    protected ProgressBar mDialogBrightnessProgressBar;
    protected TextView mDialogBrightnessTextView;

    public VideoPlayer(Context context) {
        this(context, (AttributeSet)null);
    }

    public VideoPlayer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.isShowWifiDialog = true;
        this.initView();
        this.setUIWithStateAndMode(0, this.currentMode);
    }

    protected int getLayoutId() {
        return R.layout.player_layout_player_control;
    }

    protected void initView() {
        this.topContainer = this.findViewById(org.song.videoplayer.R.id.layout_top);
        this.bottomContainer = this.findViewById(org.song.videoplayer.R.id.layout_bottom);
        this.bufferingContainer = this.findViewById(org.song.videoplayer.R.id.buffering_container);
        this.loadingContainer = this.findViewById(org.song.videoplayer.R.id.loading_container);
        this.errorContainer = this.findViewById(org.song.videoplayer.R.id.error_container);
        this.coverImageView = this.findViewById(org.song.videoplayer.R.id.cover);
        this.titleTextView = this.findViewById(org.song.videoplayer.R.id.title);
        this.changeViews = new ArrayList();
        this.changeViews.add(this.topContainer);
        this.changeViews.add(this.bottomContainer);
        this.changeViews.add(this.loadingContainer);
        this.changeViews.add(this.errorContainer);
        this.changeViews.add(this.coverImageView);
        this.changeViews.add(this.startButton);
        this.changeViews.add(this.progressBar);
    }

    public void setUp(String url, Object... objects) {
        super.setUp(url, objects);
        if (objects != null && objects.length > 0) {
            this.titleTextView.setText(String.valueOf(objects[0]));
        }

    }

    protected void changeUiWithStateAndMode(int status, int mode) {
        switch(status) {
            case 0:
                this.showChangeViews(this.coverImageView, this.startButton);
                break;
            case 1:
                this.showChangeViews(this.loadingContainer);
                break;
            case 2:
            case 4:
            case 5:
                this.showChangeViews(this.startButton, mode >= 102 ? null : this.bottomContainer, mode == 101 ? this.topContainer : null);
            case 3:
            default:
                break;
            case 6:
                this.showChangeViews(this.errorContainer);
        }

        this.updateViewImage(status, mode);
        this.floatCloseView.setVisibility(mode >= 102 ? VISIBLE :INVISIBLE);
        this.floatBackView.setVisibility(mode == 102 ? VISIBLE : INVISIBLE);
    }

    protected void dismissControlView(int status, int mode) {
        this.bottomContainer.setVisibility(INVISIBLE);
        this.topContainer.setVisibility(INVISIBLE);
//        this.progressBar.setVisibility(VISIBLE);
        if (status != 5) {
            this.startButton.setVisibility(INVISIBLE);
        }

        if (mode >= 102) {
            this.floatCloseView.setVisibility(INVISIBLE);
        }

        if (mode == 102) {
            this.floatBackView.setVisibility(INVISIBLE);
        }

    }

    protected void onBuffering(boolean isBuffering) {
        this.bufferingContainer.setVisibility(isBuffering ? VISIBLE : INVISIBLE);
    }

    protected void showChangeViews(View... views) {
        Iterator var2 = this.changeViews.iterator();

        while(var2.hasNext()) {
            View v = (View)var2.next();
            if (v != null) {
                v.setVisibility(INVISIBLE);
            }
        }

        View[] var6 = views;
        int var7 = views.length;

        for(int var4 = 0; var4 < var7; ++var4) {
            View v = var6[var4];
            if (v != null) {
                v.setVisibility(VISIBLE);
            }
        }

    }

    protected void updateViewImage(int status, int mode) {
        this.startButton.setImageResource(status == 2 ? org.song.videoplayer.R.drawable.jc_click_pause_selector : org.song.videoplayer.R.drawable.jc_click_play_selector);
        this.fullscreenButton.setImageResource(mode == 101 ? org.song.videoplayer.R.drawable.jc_shrink : org.song.videoplayer.R.drawable.jc_enlarge);
    }

    public ImageView getCoverImageView() {
        return this.coverImageView;
    }

    protected boolean showWifiDialog() {
        if (!this.isShowWifiDialog) {
            return false;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            builder.setMessage(this.getResources().getString(org.song.videoplayer.R.string.tips_not_wifi));
            builder.setPositiveButton(this.getResources().getString(org.song.videoplayer.R.string.tips_not_wifi_confirm), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    VideoPlayer.this.prepareMediaPlayer();
                }
            });
            builder.setNegativeButton(this.getResources().getString(org.song.videoplayer.R.string.tips_not_wifi_cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
            return true;
        }
    }

    protected void doubleClick() {
        this.clickFull();
    }

    protected boolean showProgressDialog(int delta, int position, int duration) {
        if (this.mProgressDialog == null) {
            View localView = LayoutInflater.from(this.getContext()).inflate(org.song.videoplayer.R.layout.jc_dialog_progress, (ViewGroup)null);
            this.mDialogProgressBar = (ProgressBar)localView.findViewById(org.song.videoplayer.R.id.duration_progressbar);
            this.tv_current = (TextView)localView.findViewById(org.song.videoplayer.R.id.tv_current);
            this.tv_duration = (TextView)localView.findViewById(org.song.videoplayer.R.id.tv_duration);
            this.tv_delta = (TextView)localView.findViewById(org.song.videoplayer.R.id.tv_delta);
            this.mDialogIcon = (ImageView)localView.findViewById(org.song.videoplayer.R.id.duration_image_tip);
            this.mProgressDialog = this.getPopupWindow(localView);
        }

        if (!this.mProgressDialog.isShowing()) {
            this.mProgressDialog.showAtLocation(this, 17, 0, 0);
        }

        this.tv_delta.setText((delta > 0 ? "+" : "") + delta / 1000 + "秒");
        this.tv_current.setText(Util.stringForTime(position + delta) + "/");
        this.tv_duration.setText(Util.stringForTime(duration));
        this.mDialogProgressBar.setProgress((position + delta) * 100 / duration);
        if (delta > 0) {
            this.mDialogIcon.setBackgroundResource(org.song.videoplayer.R.drawable.jc_forward_icon);
        } else {
            this.mDialogIcon.setBackgroundResource(org.song.videoplayer.R.drawable.jc_backward_icon);
        }

        return true;
    }

    protected boolean dismissProgressDialog() {
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
        }

        return true;
    }

    protected boolean showVolumeDialog(int nowVolume, int maxVolume) {
        if (this.mVolumeDialog == null) {
            View localView = LayoutInflater.from(this.getContext()).inflate(org.song.videoplayer.R.layout.jc_dialog_volume, (ViewGroup)null);
            this.mDialogVolumeImageView = (ImageView)localView.findViewById(org.song.videoplayer.R.id.volume_image_tip);
            this.mDialogVolumeTextView = (TextView)localView.findViewById(org.song.videoplayer.R.id.tv_volume);
            this.mDialogVolumeProgressBar = (ProgressBar)localView.findViewById(org.song.videoplayer.R.id.volume_progressbar);
            this.mDialogVolumeProgressBar.setMax(maxVolume);
            this.mVolumeDialog = this.getPopupWindow(localView);
        }

        if (!this.mVolumeDialog.isShowing()) {
            this.mVolumeDialog.showAtLocation(this, 48, 0, Util.dp2px(this.getContext(), this.currentMode == 100 ? 25.0F : 50.0F));
        }

        this.mDialogVolumeTextView.setText(nowVolume + "");
        this.mDialogVolumeProgressBar.setProgress(nowVolume);
        return true;
    }

    protected boolean dismissVolumeDialog() {
        if (this.mVolumeDialog != null) {
            this.mVolumeDialog.dismiss();
        }

        return true;
    }

    protected boolean showBrightnessDialog(int brightnessPercent, int max) {
        if (this.mBrightnessDialog == null) {
            View localView = LayoutInflater.from(this.getContext()).inflate(org.song.videoplayer.R.layout.jc_dialog_brightness, (ViewGroup)null);
            this.mDialogBrightnessTextView = (TextView)localView.findViewById(org.song.videoplayer.R.id.tv_brightness);
            this.mDialogBrightnessProgressBar = (ProgressBar)localView.findViewById(org.song.videoplayer.R.id.brightness_progressbar);
            this.mDialogBrightnessProgressBar.setMax(max);
            this.mBrightnessDialog = this.getPopupWindow(localView);
        }

        if (!this.mBrightnessDialog.isShowing()) {
            this.mBrightnessDialog.showAtLocation(this, 48, 0, Util.dp2px(this.getContext(), this.currentMode == 100 ? 25.0F : 50.0F));
        }

        this.mDialogBrightnessTextView.setText(brightnessPercent + "");
        this.mDialogBrightnessProgressBar.setProgress(brightnessPercent);
        return true;
    }

    protected boolean dismissBrightnessDialog() {
        if (this.mBrightnessDialog != null) {
            this.mBrightnessDialog.dismiss();
        }

        return true;
    }

    private PopupWindow getPopupWindow(View popupView) {
        PopupWindow mPopupWindow = new PopupWindow(popupView, -2, -2, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        mPopupWindow.setAnimationStyle(org.song.videoplayer.R.style.jc_popup_toast_anim);
        return mPopupWindow;
    }
}
