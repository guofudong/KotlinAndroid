package com.gfd.common.ui.adapter

import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.gfd.common.common.BaseApplication.Companion.context
import com.gfd.common.ext.loadImage

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 13:58
 * @Email：878749089@qq.com
 * @description：RecyclerView-ViewHolder基类
 */
@Suppress("UNCHECKED_CAST")
open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val views: SparseArray<View> = SparseArray()

    fun <T : View> getView(@IdRes viewId: Int): T {
        var view = views.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }

    /**
     * TextView设置文本
     * @param viewId Int
     * @param text String
     */
    fun setText(@IdRes viewId: Int, text: String): BaseViewHolder {
        getView<TextView>(viewId).text = text
        return this
    }

    /**
     * ImageView设置本地图片
     * @param viewId Int
     * @param resId Int
     * @return BaseViewHolder
     */
    fun setImageResource(@IdRes viewId: Int, @DrawableRes resId: Int): BaseViewHolder {
        getView<ImageView>(viewId).setImageResource(resId)
        return this
    }

    /**
     * ImageView设置本地图片
     * @param viewId Int
     * @param resId Int
     * @return BaseViewHolder
     */
    fun setBackgroundResource(@IdRes viewId: Int, @DrawableRes resId: Int): BaseViewHolder {
        getView<ImageView>(viewId).setBackgroundResource(resId)
        return this
    }


    /**
     * ImageView设置网络图片
     * @param viewId Int
     * @param imageUrl String
     * @return BaseViewHolder
     */
    fun setImageUrl(@IdRes viewId: Int, imageUrl: String): BaseViewHolder {
        getView<ImageView>(viewId).loadImage(context, imageUrl)
        return this
    }

    /**
     * ImageView设置网络图片
     * @param viewId Int
     * @param imageUrl String
     * @param error Int
     * @return BaseViewHolder
     */
    fun setImageUrl(@IdRes viewId: Int, imageUrl: String,error:Int): BaseViewHolder {
        getView<ImageView>(viewId).loadImage(context, imageUrl,error)
        return this
    }

    /**
     * 设置View是否可见
     * @param viewId Int
     * @param isVisibility Boolean
     * @return BaseViewHolder
     */
    fun setVisibility(@IdRes viewId: Int, isVisibility: Boolean): BaseViewHolder {
        getView<View>(viewId).visibility = if (isVisibility) View.VISIBLE else View.GONE
        return this
    }
}