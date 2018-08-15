package com.gfd.music.widgets;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by song on 2017/2/14.22:32
 * Contact github.com/tohodog
 * 处理触摸监听 响应各种手势
 */

public class HandleTouchEvent {

    private GestureEvent gestureEvent;

    public HandleTouchEvent(GestureEvent touchEvent) {
        this.gestureEvent = touchEvent;
    }

    private int moveLen;
    private final float speedrate = 0.70f;//调节进度灵敏度 滑动0.xx屏幕可以到底

    private float downX, downY;
    private int w, h;
    private int leftX, rightX;
    private int type = -1;
    float level = 0;

    long tempTime;

    public boolean handleEvent(View view, MotionEvent event) {

        float x = event.getX();
        float y = event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                init();
                downX = x;
                downY = y;
                w = view.getWidth();
                h = view.getHeight();
                leftX = (int) (w * 0.25);
                rightX = (int) (w * 0.75);

                moveLen = ViewConfiguration.get(view.getContext()).getScaledTouchSlop() + 30;
                //Log.e("onTouchEvent", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                if (downY < moveLen)
                    return false;

                float deltaX = x - downX;
                float deltaY = y - downY;

                //判断触发
                if (type < 0) {
                    float absDeltaX = Math.abs(deltaX);
                    float absDeltaY = Math.abs(deltaY);
                    if (absDeltaX > moveLen) {
                        type = GestureEvent.TOUCH_FULL_X;
                        downX = x;
                        gestureEvent.onGestureBegin(type);
                    }
                    if (absDeltaY > moveLen && downX <= leftX) {
                        type = GestureEvent.TOUCH_LEFT_Y;
                        downY = y;
                        gestureEvent.onGestureBegin(type);
                    }
                    if (absDeltaY > moveLen && downX > rightX) {
                        type = GestureEvent.TOUCH_RIGHT_Y;
                        downY = y;
                        gestureEvent.onGestureBegin(type);
                    }
                }

                switch (type) {
                    case GestureEvent.TOUCH_FULL_X:
                        level = 1.0f * deltaX / (w * speedrate);
                        break;
                    case GestureEvent.TOUCH_LEFT_Y:
                        level = 1.0f * -deltaY / (h * speedrate);
                        break;
                    case GestureEvent.TOUCH_RIGHT_Y:
                        level = 1.0f * -deltaY / (h * speedrate);
                        break;

                }

                if (type > 0) {
                    if (level < -1)
                        level = -1;
                    if (level > 1)
                        level = 1;
                    gestureEvent.onGestureChange(type, level);
                }
                //Log.e("onTouchEvent", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (downY < moveLen)
                    return false;

                long l = System.currentTimeMillis();
                long delay = l - tempTime;
                tempTime = l;
                if (delay < 300)
                    type = GestureEvent.TOUCH_DOUBLE_C;

                if (type > 0)
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            gestureEvent.onGestureEnd(type, level);
                        }
                    });
        }
        return type > 0 & type != GestureEvent.TOUCH_DOUBLE_C;//双击不拦截事件
    }

    private void init() {
        downX = downY = 0;
        leftX = rightX = 0;
        h = w = 0;
        type = -1;
        level = 0;
    }

    public interface GestureEvent {

        int TOUCH_FULL_X = 1;//全屏左右滑动
        int TOUCH_LEFT_Y = 2;//左边上下滑动
        int TOUCH_RIGHT_Y = 3;//右边上下滑动
        int TOUCH_DOUBLE_C = 4;//双击
        //...

        void onGestureBegin(int type);//触发手势事件

        void onGestureChange(int type, float level);//改变level: -1 ~ 1

        void onGestureEnd(int type, float level);//手势事件结束
    }
}
