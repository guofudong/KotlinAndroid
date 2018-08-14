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
 * @descriptio：
 */
open abstract class BaseAdapter<T>(private var context: Context) : RecyclerView.Adapter<BaseViewHolder>() {

    protected val mDatas = ArrayList<T>()

    private var listener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): BaseViewHolder {
        var itemView = LayoutInflater.from(context).inflate(getItemLayoutId(), parent, false)
        return BaseViewHolder(itemView)
    }

    /**
     * 获取item布局
     * @return Int
     */
    abstract fun getItemLayoutId(): Int

    override fun getItemCount(): Int {
        return mDatas.size
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
        fun onClick(view: View, data: Int)
    }

    fun seOnClickListener(listener: OnClickListener) {
        this.listener = listener
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