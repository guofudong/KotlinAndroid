package com.gfd.common.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.gfd.common.R

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 13:39
 * @Email：878749089@qq.com
 * @descriptio：图片加载工具类 -- Glide实现
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
                .error(R.drawable.icon_default).into(
                        object : SimpleTarget<GlideDrawable>() {
                            override fun onResourceReady(resource: GlideDrawable?, glideAnimation: GlideAnimation<in GlideDrawable>?) {
                                imageView.setImageDrawable(resource)
                            }
                        }
                )

    }



}