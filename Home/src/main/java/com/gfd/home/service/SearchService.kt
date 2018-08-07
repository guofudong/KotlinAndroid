package com.gfd.home.service

import com.gfd.home.entity.SearchItemData

/**
 * @Author : 郭富东
 * @Date ：2018/8/7 - 15:58
 * @Email：878749089@qq.com
 * @descriptio：
 */
interface SearchService{

    fun search(keyWord:String,callback:SearchCallBack)

    interface SearchCallBack{
        fun onSearch(datas:List<SearchItemData>)
    }
}