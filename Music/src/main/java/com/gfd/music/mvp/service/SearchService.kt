package com.gfd.music.mvp.service

import android.content.Context
import com.gfd.music.entity.SearchData
import com.gfd.music.entity.SongItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/23 - 16:44
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface SearchService {

    fun getHotSearch(callback: IGetSearchCallBack)
    fun getSearchHistory(context: Context, callback: IGetSearchCallBack)
    fun search(context: Context,keyword: String, callback: IGetSearchCallBack)
    fun deleteHistory(context: Context, callback: SearchService.IGetSearchCallBack)

    interface IGetSearchCallBack {
        fun onHotSearch(datas: List<String>)
        fun onSearchHistory(datas: List<String>)
        fun onSearchResult(html: String)
        fun onDeleteHistory()
    }

}