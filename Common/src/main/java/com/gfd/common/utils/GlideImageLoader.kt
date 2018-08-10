package com.gfd.common.utils

import android.content.Context
import android.widget.ImageView
import com.youth.banner.loader.ImageLoader

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 17:36
 * @Email：878749089@qq.com
 * @descriptio：
 */
class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        imageView?.let { context?.let { it1 -> (ImageLoader).loadUrlImage(it1, path as String, it) } }
    }

    //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
    override fun createImageView(context: Context): ImageView {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        return imageView
    }

}