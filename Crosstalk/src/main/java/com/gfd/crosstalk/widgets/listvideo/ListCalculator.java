package com.gfd.crosstalk.widgets.listvideo;

import android.graphics.Rect;
import android.os.Handler;
import android.view.View;

/**
 * list列表 item的生命周期控制(即将滚出屏幕 和 进入活动状态的item 监听)
 * item高度大于listview的一半以上最好
 * Created by song on 2017/6/1.
 * Contact github.com/tohodog
 */

public class ListCalculator {

    private boolean isScrollUp = true;//滚动方向
    private Getter getter;
    private CallBack callBack;

    public int VISIBILITY_PERCENTS = 80;
    private int currentActiveItem = -1;//当前活动的item


    public ListCalculator(Getter getter, CallBack callBack) {
        this.getter = getter;
        this.callBack = callBack;
    }


    /**
     * 滚动中
     */
    public void onScrolling(int status) {
        if (!checkUpDown() || (status == 0 & currentActiveItem >= 0))
            return;

        int firstVisiblePosition = getter.getFirstVisiblePosition();
        int lastVisiblePosition = getter.getLastVisiblePosition();


        int activeItem = currentActiveItem;
        //确保 活动item 在屏幕里了
        if (activeItem < firstVisiblePosition || activeItem > lastVisiblePosition) {
            activeItem = isScrollUp ? firstVisiblePosition : lastVisiblePosition;
        }
        //计算当前新的活动item应该是哪个
        View currentView = getter.getChildAt(activeItem - firstVisiblePosition);
        int currentP = getVisibilityPercents(currentView);
        if (isScrollUp) {//往上滚动

            if (lastVisiblePosition >= activeItem + 1) {//存在下一个item
                View nextView = getter.getChildAt(activeItem + 1 - firstVisiblePosition);
                int nextP = getVisibilityPercents(nextView);
                if (enoughPercentsForDeactivation(currentP, nextP)) {
                    activeItem = activeItem + 1;
                }
            }

        } else {//往下滚动

            if (firstVisiblePosition <= activeItem - 1) {//存在上一个item
                View preView = getter.getChildAt(activeItem - 1 - firstVisiblePosition);
                int preP = getVisibilityPercents(preView);
                if (enoughPercentsForDeactivation(currentP, preP)) {
                    activeItem = activeItem - 1;
                }
            }
        }

        handler.removeCallbacks(run);

        //不一样说明活动的item改变了
        if (activeItem != currentActiveItem) {

            View v1 = getter.getChildAt(currentActiveItem - firstVisiblePosition);
            if (v1 != null)
                callBack.deactivate(v1, currentActiveItem);
            View v2 = getter.getChildAt(activeItem - firstVisiblePosition);
            if (v2 != null) {
                callBack.activeOnScrolling(v2, activeItem);
                if (currentActiveItem < 0){
                    handler.postDelayed(run, 300);
                    //callBack.activeOnScrolled(v2, activeItem);
                }
            }
            currentActiveItem = activeItem;
            isChangeFlag = true;
        }
    }

    private boolean isChangeFlag;

    //根据两个item的显示百分比 判断是否销毁上一个item
    private boolean enoughPercentsForDeactivation(int visibilityPercents, int nextVisibilityPercents) {
        return (visibilityPercents < VISIBILITY_PERCENTS && nextVisibilityPercents >= VISIBILITY_PERCENTS) ||
                (visibilityPercents <= 20 && visibilityPercents > 0) ||//活动的item快要消失
                (visibilityPercents < 95 && nextVisibilityPercents == 100);//下一个item100%显示了 上一个活动item只需要隐藏一点就切换
    }

    /**
     * 停止滚动
     */
    public void onScrolled(int delayed) {
        if (isChangeFlag) {
            handler.removeCallbacks(run);
            handler.postDelayed(run, delayed);
        }
    }

    private Handler handler = new Handler();
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            isChangeFlag = false;
            View v = getter.getChildAt(currentActiveItem - getter.getFirstVisiblePosition());
            if (v != null)
                callBack.activeOnScrolled(v, currentActiveItem);
        }
    };


    //todo 如果外部手动点了播放 改变了活动item 需要设置这里的活动item
    public void setCurrentActiveItem(int currentActiveItem) {
        if (this.currentActiveItem != currentActiveItem) {
            int firstVisiblePosition = getter.getFirstVisiblePosition();
            View currentView = getter.getChildAt(this.currentActiveItem - firstVisiblePosition);
            if (currentView != null)
                callBack.deactivate(currentView, this.currentActiveItem);

            currentView = getter.getChildAt(currentActiveItem - firstVisiblePosition);
            if (currentView != null) {
                this.currentActiveItem = currentActiveItem;
                callBack.activeOnScrolled(currentView, currentActiveItem);
            }
        }
    }

    public int getCurrentActiveItem() {
        return currentActiveItem;
    }


    ///////////////以下为判断item显示百分比的逻辑代码///////////////////
    private int mOldTop;
    private int mOldFirstVisibleItem;

    //检测滑动方向
    private boolean checkUpDown() {
        View view = getter.getChildAt(0);
        if (view == null)
            return false;
        int top = view.getTop();

        int firstVisibleItem = getter.getFirstVisiblePosition();
        if (firstVisibleItem == mOldFirstVisibleItem) {
            if (top > mOldTop) {
                isScrollUp = false;
            } else if (top < mOldTop) {
                isScrollUp = true;
            }
        } else {
            isScrollUp = firstVisibleItem > mOldFirstVisibleItem;
        }

        mOldTop = top;
        mOldFirstVisibleItem = firstVisibleItem;
        return true;
    }

    //获取item的显示在屏幕上的百分比
    private int getVisibilityPercents(View view) {
        final Rect currentViewRect = new Rect();

        int percents = 100;

        int height = (view == null || view.getVisibility() != View.VISIBLE) ? 0 : view.getHeight();

        if (height == 0) {
            return 0;
        }

        view.getLocalVisibleRect(currentViewRect);

        if (viewIsPartiallyHiddenTop(currentViewRect)) {
            // view is partially hidden behind the top edge
            percents = (height - currentViewRect.top) * 100 / height;
        } else if (viewIsPartiallyHiddenBottom(currentViewRect, height)) {
            percents = currentViewRect.bottom * 100 / height;
        }

        return percents;
    }

    private boolean viewIsPartiallyHiddenBottom(Rect currentViewRect, int height) {
        return currentViewRect.bottom > 0 && currentViewRect.bottom < height;
    }

    private boolean viewIsPartiallyHiddenTop(Rect currentViewRect) {
        return currentViewRect.top > 0;
    }
}
