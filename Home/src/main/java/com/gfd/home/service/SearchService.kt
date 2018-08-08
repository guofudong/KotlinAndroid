package com.gfd.home.service

import android.content.Context
import com.gfd.home.entity.SearchItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 15:58
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface SearchService {

    /**
     * 搜索
     * @param keyWord String
     * @param callback SearchCallBack
     */
    fun search(context: Context,keyWord: String, callback: SearchCallBack)

    /**
     * 获取搜索历史
     * @param callback SearchCallBack
     */
    fun getSearchHistory(context: Context,callback: SearchCallBack)

    /**
     * 删除历史搜索记录
     * @param context Context
     */
    fun deleteHistory(context: Context,callback: SearchCallBack)

    interface SearchCallBack {

        /** 搜索成功回调*/
        fun onSearch(datas: List<SearchItemData>)

        /** 获取历史搜索记录成功回调*/
        fun onHistory(history: List<String>)
    }
}