package com.gfd.common.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 13:59
 * @Email：878749089@qq.com
 * @description：
 */
abstract class BaseAdapter<T>(private var context: Context) : RecyclerView.Adapter<BaseViewHolder>() {

    protected val mData = ArrayList<T>()

    private var listener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BaseViewHolder {
        val itemView = LayoutInflater.from(context).inflate(getItemLayoutId(), parent, false)
        return BaseViewHolder(itemView)
    }

    /**
     * 获取item布局
     * @return Int
     */
    abstract fun getItemLayoutId(): Int

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            if (listener != null) {
                listener!!.onClick(it, position)
            }

        }
        onBindView(holder, position)
    }

    /**
     * 设置item的内容
     * @param holder BaseViewHolder
     * @param position Int
     */
    abstract fun onBindView(holder: BaseViewHolder, position: Int)

    interface OnClickListener {
        fun onClick(view: View, position: Int)
    }

    fun seOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    /**
     * 添加数据，局部刷新
     * @param data List<T>
     */
    fun addAll(data: List<T>) {
        val lastIndex = mData.size
        if (mData.addAll(data)) {
            notifyItemRangeInserted(lastIndex, mData.size)
        }
    }


    /**
     * 更新数据
     * @param data List<T>
     */
    fun updateData(data: List<T>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun getData():List<T>{
        return mData
    }

}