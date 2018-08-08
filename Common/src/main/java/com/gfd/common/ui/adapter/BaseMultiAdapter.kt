package com.gfd.common.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @Author : 郭富东
 * @Date ：2018/8/4 - 10:13
 * @Email：878749089@qq.com
 * @descriptio：多种item类型的Recycleview的适配器的基类
 */
open abstract class BaseMultiAdapter<T : MultiItemEntity>(private val context: Context?) : RecyclerView.Adapter<BaseViewHolder>() {

    protected val mDatas = ArrayList<T>()
    /** 存储不同类型的item布局*/
    private val mLayouts = SparseIntArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemView = LayoutInflater.from(context).inflate(getItemLayoutId(viewType), parent, false)
        return BaseViewHolder(itemView)
    }

    /**
     * 根据item类型获取对应的布局
     * @param viewType Int
     * @return Int
     */
    private fun getItemLayoutId(viewType: Int): Int {
        return mLayouts.get(viewType)
    }

    override fun getItemCount(): Int {
        return mDatas.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBindItemholder(holder, position)
    }

    /**
     * 根据item的类型绑定对应的数据，通过entity的getItemType来获取类型
     * @param holder BaseViewHolder
     * @param position Int
     */
    abstract fun onBindItemholder(holder: BaseViewHolder, position: Int)

    override fun getItemViewType(position: Int): Int {
        return mDatas.get(position).getItemType()
    }

    /**
     * 设置item类型以及对应的布局
     * @param itemType Int
     * @param layoutId Int
     */
    protected fun addItemType(itemType: Int, layoutId: Int) {
        mLayouts.put(itemType, layoutId)
    }

    /**
     * 添加数据，局部刷新
     * @param datas List<T>
     */
    fun addAll(datas: List<T>) {
        val lastIndex = mDatas.size
        if (mDatas.addAll(datas)) {
            notifyItemRangeInserted(lastIndex, mDatas.size)
        }
    }

    /**
     * 更新数据
     * @param datas List<T>
     */
    fun updateData(datas: List<T>) {
        mDatas.clear()
        mDatas.addAll(datas)
        notifyDataSetChanged()
    }

    fun getDatas():List<T>{
        return mDatas
    }

}