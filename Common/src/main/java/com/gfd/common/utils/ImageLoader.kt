package com.gfd.common.utils

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.gfd.common.R

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 13:39
 * @Email：878749089@qq.com
 * @description：图片加载工具类 -- Glide实现
 */
object ImageLoader {

    /**
     * 加载网络图片
     * @param context
     * @param url
     * @param imageView
     */
    fun loadUrlImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).placeholder(R.drawable.icon_default)
                .error(R.drawable.icon_default).into(imageView)

    }

    /**
     * 加载网络图片
     * @param activity
     * @param url
     * @param imageView
     */
    fun loadUrlImage(activity: Activity, url: String, imageView: ImageView) {
        if (!activity.isDestroyed) {
            Glide.with(activity).load(url).placeholder(R.drawable.icon_default)
                    .error(R.drawable.icon_default).into(imageView)
        }
    }


}