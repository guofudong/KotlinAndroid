package com.gfd.crosstalk.widgets.listvideo;

import android.view.View;

public interface CallBack {

    //当前的item 滚动结束调用
    void activeOnScrolled(View activeView, int position);

    //当前的item 滚动中调用
    void activeOnScrolling(View activeView, int position);

    //销毁的item
    void deactivate(View currentView, int position);
}